package StreamAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class Emp {
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
                return "Emp [eid=" + eid + ", fname=" + fname + ", lname=" + lname + ", job=" + job + ", sal=" + sal
                                + ", dno="
                                + dno + "]";
        }
}

class Dept {
        Integer dno;
        String dname;
        Integer lid;

        public Dept(Integer dno, String dname, Integer lid) {
                super();
                this.dno = dno;
                this.dname = dname;
                this.lid = lid;
        }

        @Override
        public String toString() {
                return "Dept [dno=" + dno + ", dname=" + dname + ", lid=" + lid + "]";
        }

}

class DataFromDB {
        public static Connection reqCon() {
                Connection con = null;
                try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_1", "root", "vsk@2004");
                } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                }
                return con;
        }

        public static List<Emp> getAllEmp() {
                List<Emp> eList = new ArrayList<Emp>();
                Emp e = null;
                try {
                        Connection con = DataFromDB.reqCon();
                        PreparedStatement ps = con.prepareStatement("SELECT * From Emp");
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                                e = new Emp(rs.getInt("eid"), rs.getString("fname"), rs.getString("lname"),
                                                rs.getString("job"),
                                                rs.getDouble("sal"), rs.getInt("dno"));
                                eList.add(e);
                        }
                } catch (SQLException e1) {
                        e1.printStackTrace();
                }
                return eList;
        }

        public static List<Dept> getAllDept() {
                List<Dept> dList = new ArrayList<Dept>();
                Dept d = null;
                Connection con = DataFromDB.reqCon();
                try {
                        PreparedStatement ps = con.prepareStatement("SELECT * From Dept");
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                                d = new Dept(rs.getInt("dno"), rs.getString("dname"), rs.getInt("lid"));
                                dList.add(d);
                        }
                } catch (SQLException e1) {
                        e1.printStackTrace();
                }
                return dList;
        }

        public class Demo2 {

                public static void main(String[] args) {
                        List<Emp> eList = DataFromDB.getAllEmp();
                        List<Dept> dList = DataFromDB.getAllDept();

                        // 1. WAP to display the data of emps who is working as salesman
                        System.out.println("-------------1-------------");
                        eList.stream()
                                        .filter((e) -> e.job.equals("Salesman"))
                                        .forEach(System.out::println);

                        // 2. WAP to display the data of emps if emp is working in dept 112
                        System.out.println("-------------2--------------");
                        eList.stream()
                                        .filter((e) -> e.dno == 112)
                                        .forEach(System.out::println);

                        // 3. WAP TO DISPLAY THE DATA OF EMps if the emp is not working as ceo
                        System.out.println("-------------3--------------");
                        eList.stream()
                                        .filter((e) -> !e.job.equals("ceo"))
                                        .forEach(System.out::println);

                        // 4. WAP to display the data of emps if the emp is getting salary more than
                        // 45000
                        System.out.println("-------------4-------------");
                        eList.stream()
                                        .filter((e) -> e.sal >= 45000)
                                        .forEach(System.out::println);

                        // 5.WAP to display the details of emp of the emp fname starts with s
                        System.out.println("-------------5--------------");
                        eList.stream()
                                        .filter((e) -> e.fname.startsWith("S"))
                                        .forEach(System.out::println);

                        // 6. WAP to display the details of emp if the emp job role starts with D
                        System.out.println("-------------6--------------");
                        eList.stream()
                                        .filter((e) -> e.job.startsWith("D"))
                                        .forEach(System.out::println);

                        // 7. WAP to display the details of emp if the emp is working as Salesman or
                        // manager
                        System.out.println("-------------7---------------");
                        eList.stream()
                                        .filter((e) -> e.job.equals("Salesman") || e.job.equals("Manager"))
                                        .forEach(System.out::println);

                        // 8.WAP to display the details og emp if the emp is getting salary more than
                        // 40000 nut less than 100000
                        System.out.println("-------------8---------------");
                        eList.stream()
                                        .filter((e) -> e.sal >= 40000 || e.sal <= 100000)
                                        .forEach(System.out::println);

                        // 9.WAP to display the details of emp if the emp last name ends with i or y
                        System.out.println("-------------9---------------");
                        eList.stream()
                                        .filter((e) -> e.lname.endsWith("i") || e.lname.endsWith("y"))
                                        .forEach(System.out::println);

                        // 10. WAP to display the details of emp if emp is working as salesman or
                        // manager in dept 110 or 111
                        System.out.println("-------------10-------------");
                        eList.stream()
                                        .filter((e) -> (e.job.equals("Salesman") || e.job.equals("Manager"))
                                                        && (e.dno == 110 || e.dno == 111))
                                        .forEach(System.out::println);

                        System.out.println("---------------11---------------");
                        // 11. WAP to display the details of the emp if the emp is getting sal more than
                        // 35000
                        eList.stream()
                                        .filter((e) -> e.sal > 35000)
                                        .forEach(System.out::println);

                        System.out.println("-----------12------------------");
                        // 12. WAP to display the details of the emp if the emp name starts with s and
                        // display only fname
                        Consumer<Emp> c = e -> System.out.println(e.fname);
                        eList.stream()
                                        .filter((e) -> e.fname.startsWith("S"))
                                        .forEach(c);

                        // OR
                        eList.stream()
                                        .filter((e) -> e.fname.startsWith("S"))
                                        .forEach(e -> System.out.println(e.fname));

                        // By Using Map Method
                        eList.stream()
                                        .filter((e) -> e.fname.startsWith("S"))
                                        .map(e -> e.fname.toUpperCase())
                                        .forEach(System.out::println);

                        // 13. WAP to display the names and sal of the emp
                        // if the emp is getting salary more than 50000
                        System.out.println("------------13---------");
                        eList.stream()
                                        .filter((e) -> e.sal > 50000)
                                        .map(e -> e.fname + " " + e.sal)
                                        .forEach(System.out::println);

                        // 14.WAP to display the name,job and dept no if the emp is working as developer
                        // or tester in dept 113.
                        System.out.println("---------14------------");
                        eList.stream()
                                        .filter((e) -> (e.dno == 113) && (e.job.equalsIgnoreCase("Developer"))
                                                        || (e.job.equalsIgnoreCase("Tester")))
                                        .map(e -> e.fname + " " + e.job + " " + e.dno)
                                        .forEach(System.out::println);

                        // 15. WAP to display the fullname of the emps
                        System.out.println("-----------15-----------");
                        eList.stream()
                                        .map(e -> e.fname + " " + e.lname)
                                        .forEach(System.out::println);

                        // 16. WAP to display the emp fullname in below for
                        // Siddarth Patil => Siddarth.P
                        System.out.println("----------16----------");
                        eList.stream()
                                        .map(e -> e.fname + "." + e.lname.charAt(0))
                                        .forEach(System.out::println);

                        // 17. WAP to display the first half of the fname
                        System.out.println("-----------17------------");
                        eList.stream()
                                        .map(e -> e.fname.substring(0, e.fname.length() / 2))
                                        .forEach(System.out::println);

                        // 18. WAP to display 1st name and lname if the lenght of 1st name exceeds 4
                        // characters
                        System.out.println("-----------18--------------");
                        eList.stream()
                                        .filter(e -> e.fname.length() > 5)
                                        .map(e -> e.fname + " " + e.lname)
                                        .forEach(System.out::println);

                        // 19.WAP to display all the job reoles from empData
                        System.out.println("------------19-----------");
                        eList.stream()
                                        .map(e -> e.job)
                                        .distinct()
                                        .forEach(System.out::println);

                        // 20. WAP to display the different dipartments available in empData
                        System.out.println("-----------20-----------");
                        eList.stream()
                                        .map(e -> e.dno)
                                        .distinct()
                                        .forEach(System.out::println);

                        // 21.WAP to display the fname,lname and salary if the employee is working as
                        // salesman or manager.
                        System.out.println("-----------21------------");
                        eList.stream()
                                        .filter((e) -> e.job.equalsIgnoreCase("salesman")
                                                        || e.job.equalsIgnoreCase("Manager"))
                                        .map(e -> e.fname + " " + e.lname + " " + e.sal)
                                        .forEach(System.out::println);

                        // 22. WAP to display the 1st 5 data in employee list
                        System.out.println("-----------22------------");
                        eList.stream()
                                        .limit(5)
                                        .forEach(System.out::println);

                        // 23. WAP to display 1st 4 emp fname
                        System.out.println("----------23--------------");
                        eList.stream()
                                        .limit(4)
                                        .forEach(System.out::println);

                        // 24. WAT to display the 4th emp data
                        System.out.println("----------24-------------");
                        eList.stream()
                                        .skip(3)
                                        .limit(1)
                                        .forEach(System.out::println);

                        // 25.WAT to display 1st 10 records
                        System.out.println("-----------25-------------");
                        eList.stream()
                                        .limit(10)
                                        .forEach(System.out::println);

                        // 26 WAT to display the 7th emp data
                        System.out.println("----------26-------------");
                        eList.stream()
                                        .skip(6)
                                        .limit(1)
                                        .forEach(System.out::println);

                        // 27.WAP to display the 8th and 9th emp data
                        System.out.println("----------27-------------");
                        eList.stream()
                                        .skip(7)
                                        .limit(2)
                                        .forEach(System.out::println);

                        // 28.WAT to display the first 3 emp data which has even id
                        System.out.println("-----------28--------------");
                        eList.stream()
                                        .filter((e) -> (e.eid) % 2 == 0)
                                        .limit(3)
                                        .forEach(System.out::println);

                        // 29 WAP to display the employee records based on salary minium to maximum
                        System.out.println("-----------29-----------");
                        eList.stream()
                                        .sorted((java.util.Comparator.comparing(e -> e.sal)))
                                        .forEach(System.out::println);

                        // 30.WAP to display the emp fname records in alphabetical order
                        System.out.println("-----------30------------");
                        eList.stream()
                                        .sorted(java.util.Comparator.comparing(e -> e.fname))
                                        .map(e -> e.fname)
                                        .forEach(System.out::println);

                        // 31.WAP to display the emp salaries in desc order
                        System.out.println("----------31-----------");
                        eList.stream()
                                        .sorted(java.util.Comparator.comparing((Emp e) -> e.sal).reversed())
                                        .map(e -> e.sal)
                                        .distinct()
                                        .forEach(System.out::println);

                        // 32.WAP to display last 4 records from emp list
                        System.out.println("----------32-------------");
                        eList.stream()
                                        .sorted(java.util.Comparator.comparing((Emp e) -> e.eid).reversed())
                                        .forEach(System.out::println);

                        // 33.WAP to display the 2nd max salary from emp list
                        System.out.println("-----------33----------");
                        eList.stream()
                                        .sorted(java.util.Comparator.comparing((Emp e) -> e.sal).reversed())
                                        .skip(1)
                                        .limit(1)
                                        .forEach(System.out::println);

                        // 34. WAP to display the 3rd min salary
                        System.out.println("----------34------------");
                        eList.stream()
                                        .sorted(java.util.Comparator.comparing((Emp e) -> e.sal))
                                        .map(e -> e.sal)
                                        .distinct()
                                        .skip(2)
                                        .limit(1)
                                        .forEach(System.out::println);

                        // 35. WAT to display the number of salesman in the list
                        System.out.println("-----------35-----------");
                        Long salesman_count = eList.stream()
                                        .filter(e -> e.job.equals("Salesman"))
                                        .count();
                        System.out.println(salesman_count);

                        // 36. WAP to display the number of emp whose fname starts with s or k
                        System.out.println("-----------36------------");
                        Long num_emp = eList.stream()
                                        .filter(e -> e.fname.startsWith("S") || e.fname.startsWith("K"))
                                        .count();
                        System.out.println(num_emp);

                        // 37. WAP to display the number of different job roles available in emp records
                        System.out.println("-------------37--------------");
                        Long diff_job = eList.stream()
                                        .map((e) -> e.job)
                                        .distinct()
                                        .count();
                        System.out.println(diff_job);

                        // 38. WAP to display the number of dipartment available in dept list
                        System.out.println("-----------38---------------");
                        Long num_diff = dList.stream()
                                        .count();
                        System.out.println(num_diff);

                        // 39. WAP to optain the list of salesman result must be in list format
                        System.out.println("-----------39------------");
                        List<Emp> emp_List1 = eList.stream()
                                        .filter((e) -> e.job.equals("Salesman"))
                                        .collect(Collectors.toList());
                        System.out.println(emp_List1);

                        // 40. WAP to display the emp fname,lname,sal,deptno
                        // if the emp is working as developer or dispatcher.
                        // Display the data in the form of set
                        System.out.println("-----------40------------");
                        Set<String> emp_List2 = eList.stream()
                                        .filter((e) -> e.job.equalsIgnoreCase("developer")
                                                        || e.job.equalsIgnoreCase("dispatcher"))
                                        .map((e) -> e.fname + " " + e.lname + " " + e.sal + " " + e.dno)
                                        .collect(Collectors.toSet());
                        System.out.println(emp_List2);

                        // 41.WAP to display the emp data in map format if the emp is
                        // woking in dept 113
                        System.out.println("----------41-----------");
                        Map<Integer, Emp> map = eList.stream()
                                        .filter((e) -> e.dno == 113)
                                        .collect(Collectors.toMap(e -> e.eid, e -> e));
                        System.out.println(map);

                        // 42.WAP to display the dept data in map format
                        System.out.println("-----------42----------");
                        Map<Integer, Dept> map1 = dList.stream()
                                        .collect(Collectors.toMap(d -> d.dno, d -> d));
                        System.out.println(map1);
                        
                        //43.WAP to display the emp data who is obtaining max salary from emp list
                        System.out.println("---------43------------");
                        Optional<Emp> max_sal=
                        eList.stream()
                        .collect(Collectors.maxBy(Comparator.comparing(e->e.sal)));
                        System.out.println(max_sal.orElse(null));
                        
                     
                         //44.WAP to display the emp data who is obtaining min salary from emp list
                        System.out.println("---------44------------");
                        Optional<Emp> min_sal=
                        		eList.stream()
                        		.collect(Collectors.minBy(Comparator.comparing(e->e.sal)));
                        System.out.println(min_sal.orElse(null));		
                        
                        //45.WAP to display the max salary in each dept
                        System.out.println("---------45----------");
                        Map<Integer,Optional<Emp>> max_emp_dno=
                        		eList.stream()
                        		.collect(Collectors.groupingBy(e->e.dno,Collectors.maxBy(Comparator.comparing(e->e.sal))));
                        max_emp_dno.forEach((dno,o)->System.out.println(dno+" "+o.orElse(null)));
                        
                        //46.WAP to display the min salary in each dept
                        System.out.println("---------46----------");
                        Map<Integer,Optional<Emp>> min_emp_dno=
                        		eList.stream()
                        		.collect(Collectors.groupingBy(e->e.dno,Collectors.minBy(Comparator.comparing(e->e.sal))));
                        min_emp_dno.forEach((dno,o)->System.out.println(dno+" "+o.orElse(null)));
                        
                        //47.WAP to display the number of emps in each dept
                        System.out.println("---------47----------");
                        Map<Integer, Long> count_dept=
                        		eList.stream()
                        		.collect(Collectors.groupingBy(e->e.dno,Collectors.counting()));
                        count_dept.forEach((dno,count)->System.out.println(dno+"-"+count));
                        
                        //48.WAP to display the number of emps in each job role
                        System.out.println("-----------48------------");
                        Map<Object, Long> map_job = 
                        		eList.stream()
                        		.collect(Collectors.groupingBy(e->e.job,Collectors.counting()));
                        map_job.forEach((job,count)->System.out.println(job+"-"+count));
                        
                        //49.WAP to display the max salary in each job role
                        System.out.println("-----------49-----------");
                        Map<Object, Optional<Emp>> job_emp=
                        		eList.stream()
                        		.collect(Collectors.groupingBy(e->e.dno,Collectors.maxBy(Comparator.comparing(e->e.sal))));
                        job_emp.forEach((ejob,o)->System.out.println(ejob+" "+o.orElse(null)));
                        		
                        //50.WAP to display the avg salary in each dept
                        System.out.println("-----------50------------");
                        Map<Integer,Double> avg_sal= 
                        		eList.stream()
                        		.collect(Collectors.groupingBy(e->e.dno,Collectors.averagingDouble(e->e.sal)));
                        avg_sal.forEach((dno,sal)->System.out.println(dno+" "+sal));
                        		
                        //51.WAP to display total salary in each dept
                        System.out.println("-----------51------------");
                        Map<Integer,Double> map_total=
                        eList.stream()
                        .collect(Collectors.groupingBy(e->e.dno,Collectors.summingDouble(e->e.sal)));
                        map_total.forEach((dno,sal)->System.out.println(dno+" :"+sal));
                        
                        //52.WAP to display the first emp from emp list
                        System.out.println("----------52---------");
//                        eList.stream()
//                        .collect(Collectors.go);
                        
                        //53.WAP to dispaly the 1st emp from each dept
                        System.out.println("--------53--------");
                        List<Integer> dnos=
                        eList.stream()
                        .map(e->e.dno)
                        .distinct()
                        .collect(Collectors.toList());
                        for(Integer dep:dnos) {
                        Optional<Emp> o =	eList.stream().filter(e->e.dno==dep).findFirst();
                        System.out.println(dep+" "+o.orElse(null));
                        }
                        
                        //54.WAP to check wheather any emp belong to 114 or not

                }

        }
}
