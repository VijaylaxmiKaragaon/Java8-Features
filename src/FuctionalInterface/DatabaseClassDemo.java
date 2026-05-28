package FuctionalInterface;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DatabaseClassDemo {

	public static void main(String[] args) {
		List<Emp> empData=DataFromDB.getAllemps();
		
	//1.WAP to display the names of emp if the emp fname starts with s.
	System.out.println("--------1---------");
	Predicate<Emp> p1=(e)->e.fname.startsWith("S");
	for(Emp e:empData) {
		if(p1.test(e)) {
			System.out.println(e.fname);
		}
	}
    
	//2. WAP to dispaly the names of emp if emp sal more than 40000
	System.out.println("---------2--------");
	Predicate<Emp> p2=(e)->e.sal>(40000);
	for(Emp e:empData) {
		if(p2.test(e)) {
			System.out.println(e.fname+"  -  "+e.sal);
		}
	}
	
	//3.WAP to display the names,dno of the emp
	    //if the emp is working with dept 113
	System.out.println("---------3--------");
	Predicate<Emp> p3=(e)->e.dno == 113;
	for(Emp e:empData) {
		if(p3.test(e)) {
			System.out.println(e.dno+" "+e.fname+" "+e.lname);
		}
	}
	
	//4.WAP to display the emp fname and lname if the emp fname length exceeds 4
	System.out.println("----------4---------");
	Predicate<Emp> p4=(e)->e.fname.length()>4;
	for(Emp e:empData) {
		if(p4.test(e)) {
			System.out.println(e.fname+"  "+e.lname);
		}
	}
	
	//5.WAP to display the details (entire emp obj) if the emp is not working ac CEO
	System.out.println("---------5----------");
	Predicate<Emp> p5=(e)->e.job != "ceo";
	for(Emp e:empData) {
		if(p5.test(e)) {
			System.out.println(e.fname+"  "+e.lname);
		}
	}
	
	System.out.println("------------CONSUMER INTERFACE-------------------");
	
	Consumer<Emp> c=(e)->System.out.println(e);
	c.accept(new Emp(1,"Raju","Patil","HR",20000.00,10));
	
	Consumer<String> c1=(a)->System.out.print(a);
	c1.accept("Vijay");
	
	System.out.println("-------------SUPPLIER INTERFACE--------------");
	
	Supplier<Emp> s=()->empData.get(0);
	Emp emp_first_data=s.get();
	System.out.println(emp_first_data);
	
	Supplier<String> s1=()->"Vijay has huge girl fan following";
	System.out.println(s1.get());
	
	System.out.println("-----------Funtion Functional interface-----------");
	
	Function<String,Integer> f=(name)->name.length();
	Integer leng=f.apply("Anikethan");
	System.out.println(leng);
	}

}

class Emp{
	Integer eid;
	String fname;
	String lname;
	String job;
	Double sal;
	Integer dno;
	
	public Emp(Integer eid,String fname,String lname,String job,Double sal,Integer dno) {
		this.eid=eid;
		this.fname=fname;
		this.lname=lname;
		this.job=job;
		this.sal=sal;
		this.dno=dno;
	}

	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", fname=" + fname + ", lname=" + lname + ", job=" + job + ", sal=" + sal + ", dno="
				+ dno + "]";
	}
	
	
}

class DataFromDB{
	public static List<Emp> getAllemps(){
		List<Emp> eList=new ArrayList<Emp>();
		Emp e=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company_1","root","vsk@2004");
			PreparedStatement ps=con.prepareStatement("SELECT * FROM EMP");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				e=new Emp(rs.getInt("eid"),rs.getString("fname"),rs.getString("lname"),rs.getString("job"),rs.getDouble("sal"),rs.getInt("dno"));
				eList.add(e);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return eList;
	}
}
