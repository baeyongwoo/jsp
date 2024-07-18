package com.saeyan.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.common.DBConnPool;
import com.saeyan.dto.MemberDTO;

public class MemberDAO extends DBConnPool{
	//Singleton Patter 적용
	
	//private 생성자
	private MemberDAO(){};
	//private static instance 생성
	private static MemberDAO instance = new MemberDAO();
	//private stiatc instance를 리턴
	public static MemberDAO getInstatnce() { return instance;}
	//
	
	public Connection getConnection() throws Exception{
		// 커넥션 풀(DataSource) 얻기
        Context initCtx = new InitialContext();
        Context ctx = (Context)initCtx.lookup("java:comp/env");
        DataSource source = (DataSource)ctx.lookup("jdbc/myoracle");

        // 커넥션 풀을 통해 연결 얻기
        con = source.getConnection();
        return con;
	}
	
	//로그인처리
	public int userCheck(String userid, String pwd) {
		int result = 1;
		String sql = "SELECT pwd FROM member WHERE userid=? --";
		try {
			con = getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
					result=1;
				}else {
					result=0;
				}
			}else {
				result = -1; //id가 존재하지 않을경우
			} 
			
			
		} catch (Exception e) {
			System.err.println("userCheck Err");
			e.printStackTrace();
		}
		
		return result;
	}//login end
	
	//회원정보 검색
	public MemberDTO selectMember(String id) {
		MemberDTO dto = new MemberDTO();
		
		String sql = "SELECT * FROM member WHERE userid = ?";
		
		try {
			con = getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setName(rs.getString(1));
				dto.setUserid(rs.getString(2));
				dto.setPwd(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dto.setAdmin(rs.getInt(6));
			}
		} catch (Exception e) {
			System.err.println("selectMember Er!r");
			e.printStackTrace();
		}
		return dto;
	}
	//selectMember end;
	
	public void UpdateMember(MemberDTO userInfo) {
		String sql = "update member set pwd = ?, email = ?,phone=?, admin=? where userid = ?";
		
		try {
			con = getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userInfo.getPwd());
			psmt.setString(2, userInfo.getEmail());
			psmt.setString(3, userInfo.getPhone());
			psmt.setInt(4, userInfo.getAdmin());
			psmt.setString(5, userInfo.getUserid());
			
			psmt.executeQuery();
			
		} catch (Exception e) {
			System.err.println("update Err");
			e.printStackTrace();
		}
		
		
	}
	
	
}
