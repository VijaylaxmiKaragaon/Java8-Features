package StreamAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Emp{
	Integer eid;
	String fname;
	String lname;
	String job;
	Double sal;
	Integer dno;
	public Emp(Integer eid, String fname, String lname, String job, Double sal, Integer dno) {
		super();
		this.eid = eid;
		this.fname = fname;
		this.lname = lname;
		this.job = job;
		this.sal = sal;
		this.dno = dno;
	}
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", fname=" + fname + ", lname=" + lname + ", job=" + job + ", sal=" + sal + ", dno="
				+ dno + "]";
	}	
}

class Dept{
	Integer dno;
	String dname;
	Integer lid;
	public Dept(Integer dno, String dname, Integer lid) {
		super();
		this.dno = dno;
		this.dname = dname;
		this.lid = lid;
	}
}

class DataFromDB {
	public static Connection reqCon() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company_1","root","vsk@2004");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;	
	}
	
	public static List<Emp> getAllEmp(){
		List<Emp> eList=new ArrayList<Emp>();
		Emp e=null;
		try {
			Connection con=DataFromDB.reqCon();
			PreparedStatement ps=con.prepareStatement("SELECT * From Emp");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				e=new Emp(rs.getInt("eid"),rs.getString("fname"),rs.getString("lname"),rs.getString("job"),rs.getDouble("sal"),rs.getInt("dno"));
				eList.add(e);
			}
		}
		catch (SQLException e1) {
				e1.printStackTrace();
			}
		return eList;
	}
	
	public static List<Dept> getAllDept(){
		List<Dept> dList=new ArrayList<Dept>();
		Dept d=null;
		Connection con=DataFromDB.reqCon();
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * From Dept");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				d=new Dept(rs.getInt("dno"),rs.getString("dname"),rs.getInt("lid"));
				dList.add(d);
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	return dList;
}
	
public class Demo2 {

	public static void main(String[] args) {
		List<Emp> eList = DataFromDB.getAllEmp();
		List<Dept> dList = DataFromDB.getAllDept();
		
		//1. WAP to display the data of emps who is working as salesman
		System.out.println("-------------1-------------");
		eList.stream()
		.filter((e)->e.job.equals("Salesman"))
		.forEach(System.out::println);
		
		
	}

}
}
