package org.mydiary;


import java.util.ArrayList;
import java.util.List;

import common.DbConn;

public class DiaryboardDao extends DbConn{
	public DiaryboardDao() {
	}
	
	
	//일기 리스트 전체 보여주는 메서드
	public List<DiaryboardDto> selectAll() {
		List<DiaryboardDto> board = new ArrayList<DiaryboardDto>();
		
		String query = "select * from diary order by no desc";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while(rs.next()) {
				DiaryboardDto dto = new DiaryboardDto();
				dto.setNo(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setWeather(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				
				board.add(dto);
			}
			
			
		} catch (Exception e) {
			System.err.println("selectAll Error");
			e.printStackTrace();
			
		}
		
		
		return board;
	} //selectAll end
	
	public int write(DiaryboardDto dto) {
		
		int result = 0;
		String query = "insert into diary (no, title, content, weather, postdate)"
				+ "values(seq_next.nextval, ?,?,?, sysdate)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWeather());
			
			
			result = psmt.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			System.err.println("insert into db Err");
			e.printStackTrace();
		}
		return result;
	}
	
	public int totalSelectAll() {
		int total=0;
		
		String query = "SELECT count(*) FROM diary";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			System.err.println("total Select Err");
			e.printStackTrace();
		}
		
		
		return total;
	}

	
	
	
	

}
