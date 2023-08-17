package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpSelectExample {
	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String passwd = "tiger";
		
		Connection conn = DriverManager.getConnection(url, user, passwd);
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from emp");
		
		while(rs.next()) {
			int empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			String gender = rs.getString("gender");
			String job = rs.getString("job");
			int mgr = rs.getInt("mgr");
			String hiredate = rs.getString("hiredate").trim().split(" ")[0];
			int sal = rs.getInt("sal");
			int comm = rs.getInt("comm");
			int deptno = rs.getInt("deptno");
			
			System.out.printf("(%4d,%4s,%4s,%4s,%6d,%12s,%6d,%4d,%4d)\n", empno, ename, gender, job, mgr, hiredate, sal, comm, deptno);
		
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}
}
