package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectExample {

	public static void main(String[] args) throws SQLException {
		
		//sqlplus scott/tiger
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String passwd = "tiger";
		System.out.println("[Oracle]");
		
		Connection conn =  DriverManager.getConnection(url, user, passwd);
		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from dept");
		
		
		while(rs.next()) {
			
			int deptno = rs.getInt("deptno");
			String dname = rs.getString("dname");
			String loc = rs.getString("loc");

			System.out.printf("(%d, %s, %s)\n", deptno, dname, loc);
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		System.out.println("Program End...");
	}

}
