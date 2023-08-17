package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Alpha;
import util.Color;
import util.VT100;

/* 
 * 작성자 : 심준
 * 작성일 : 2023/08/17
 * 과 목 : 데이터베이스 응용
 */

public class AlphaSortExample {

	public static void main(String[] args) throws SQLException, InterruptedException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		
		// 오라클 연결
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		
		// 테이블 초기화
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("delete from alpha");
		conn.commit();
		
		// 테이블 생성시 필요한 SQL문
		PreparedStatement insertAlpha = conn.prepareStatement("""		
						insert into alpha
						(line, col, fg, bg, ch)
						values
						(?, ?, ?, ?, ?)
					""");
		
		PreparedStatement selectAlpha = conn.prepareStatement("""		
						select count(*)
						  from alpha
						 where line = ?
						   and col = ?
					""");
		
		PreparedStatement updateAlpha = conn.prepareStatement("""		
						update alpha 
						   set fg = ?, 
						       bg = ?, 
						       ch = ? 
						 where line = ? 
						   and col = ?
					""");
		
		// 출력용 SQL 문
		PreparedStatement select1 = conn.prepareStatement("""		
				select *
				  from alpha
				order by line asc, col asc
			""");
		
		PreparedStatement select2 = conn.prepareStatement("""		
				select *
				  from alpha
				order by line asc, col desc
			""");
		
		PreparedStatement select3 = conn.prepareStatement("""		
				select *
				  from alpha
				order by col asc, line asc
			""");
		
		PreparedStatement select4 = conn.prepareStatement("""		
				select *
				  from alpha
				order by col desc, line desc
			""");
		
		VT100.clearScreen();
		
		int insertCount = 0;
		int updateCount = 0;
		while(true) {
			Alpha a = new Alpha();
			int line = a.getLine();
			int col = a.getColumn();
			String fg = a.getFg().toString();
			String bg = a.getBg().toString();
			String ch = a.getCh()+"";
			
			selectAlpha.setInt(1, line);
			selectAlpha.setInt(2, col);
			ResultSet rs1 = selectAlpha.executeQuery();
			rs1.next();
			int cnt = rs1.getInt(1);
			
			if(cnt == 0) { // 테이블에 line, col 이 없으면 insert
				insertAlpha.setInt(1, line);
				insertAlpha.setInt(2, col);
				insertAlpha.setString(3, fg);
				insertAlpha.setString(4, bg);
				insertAlpha.setString(5, ch);
				insertAlpha.executeUpdate();
				
				insertCount++;
				VT100.reset();
				VT100.cursorMove(1, 1);
				System.out.printf("insertCount = %4d", insertCount);
			} else { // 테이블에 line, col 이 있으면 update
				updateAlpha.setString(1, fg);
				updateAlpha.setString(2, bg);
				updateAlpha.setString(3, ch);
				updateAlpha.setInt(4, line);
				updateAlpha.setInt(5, col);
				updateAlpha.executeUpdate();
				
				updateCount++;
				VT100.reset();
				VT100.cursorMove(2, 1);
				System.out.printf("updateCount = %4d", updateCount);
			}
				
			ResultSet rs2 = stmt.executeQuery("select count(*) from alpha");
			rs2.next();
			int tableSize = rs2.getInt(1);
			if(tableSize == 800)
				break;
			
			rs1.close();
			rs2.close();
		}
		
		conn.commit();
		
		// 출력 1.
		ResultSet rsp1 = select1.executeQuery();			
		while(rsp1.next()) {
			
			int line = rsp1.getInt("line");
			int col = rsp1.getInt("col");
			Color fg = Color.valueOf(rsp1.getString("fg"));
			Color bg = Color.valueOf(rsp1.getString("bg"));
			char ch = rsp1.getString("ch").charAt(0);

			VT100.cursorMove(line+2, col);
			VT100.setFore(fg);
			VT100.setBack(bg);
			VT100.print(ch);
			
			Thread.sleep(10);
		}
		
		// 출력 2.
		ResultSet rsp2 = select2.executeQuery();			
		while(rsp2.next()) {
			
			int line = rsp2.getInt("line");
			int col = rsp2.getInt("col");
			Color fg = Color.valueOf(rsp2.getString("fg"));
			Color bg = Color.valueOf(rsp2.getString("bg"));
			char ch = rsp2.getString("ch").charAt(0);

			VT100.cursorMove(line+2, col+45);
			VT100.setFore(fg);
			VT100.setBack(bg);
			VT100.print(ch);
			
			Thread.sleep(10);
		}
		
		// 출력 3.
		ResultSet rsp3 = select3.executeQuery();			
		while(rsp3.next()) {
			
			int line = rsp3.getInt("line");
			int col = rsp3.getInt("col");
			Color fg = Color.valueOf(rsp3.getString("fg"));
			Color bg = Color.valueOf(rsp3.getString("bg"));
			char ch = rsp3.getString("ch").charAt(0);
				
			VT100.cursorMove(line+24, col);
			VT100.setFore(fg);
			VT100.setBack(bg);
			VT100.print(ch);
			
			Thread.sleep(10);
		}
		// 출력 4.
		ResultSet rsp4 = select4.executeQuery();			
		while(rsp4.next()) {
			
			int line = rsp4.getInt("line");
			int col = rsp4.getInt("col");
			Color fg = Color.valueOf(rsp4.getString("fg"));
			Color bg = Color.valueOf(rsp4.getString("bg"));
			char ch = rsp4.getString("ch").charAt(0);
				
			VT100.cursorMove(line+24, col+45);
			VT100.setFore(fg);
			VT100.setBack(bg);
			VT100.print(ch);
			
			Thread.sleep(10);
		}
				
		rsp1.close();
		rsp2.close();
		rsp3.close();
		rsp4.close();
		stmt.close();
		conn.close();
		
		VT100.reset();
		VT100.cursorMove(45, 1);
		System.out.println("Program End...");
	}
}
