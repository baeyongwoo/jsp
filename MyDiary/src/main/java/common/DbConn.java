package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DbConn {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public DbConn() {
		try {
			//커넥션 풀 얻기
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource source = (DataSource)ctx.lookup("jdbc/mysideoracle");
			
			//커넥션 풀을 통해 연결 얻기
			con = source.getConnection();
			System.out.println("db연결");
		} catch (Exception e) {
			System.err.println("db커넥션 실패");
			e.printStackTrace();
		}
	}
	
	//일괄 연결 해제
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
