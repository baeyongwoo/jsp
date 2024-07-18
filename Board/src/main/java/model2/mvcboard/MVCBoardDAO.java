package model2.mvcboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;
import common.JDBConnect;
import lombok.extern.log4j.Log4j;

@Log4j

public class MVCBoardDAO extends DBConnPool{
	//기본생성자. 생성자가 호출되면 부모 클래스의 생성자 DBConnPool()가 호출된다.
	public MVCBoardDAO() {}

	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM mvcboard";


		if (map.get("searchWord") != null)
		{
			query += " WHERE " + map.get("searchField")
			+ " LIKE '%" + map.get("searchWord") + "%' ";
		}


		try {
			stmt = con.createStatement();
			rs  = stmt.executeQuery(query);
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Error for ListCount");
			e.printStackTrace();
		}
		return totalCount;
	}// 게시글 총 갯수 합



	public List<MVCBoardDTO> selectListPage(Map<String, Object> map){
		List<MVCBoardDTO> board = new ArrayList<MVCBoardDTO>();

		String query = " "
				+ "SELECT * FROM ( "
				+ "    SELECT Tb.*, ROWNUM rNum FROM ( "
				+ "        SELECT * FROM mvcboard ";

		if (map.get("searchWord") != null)
		{
			query += " WHERE " + map.get("searchField")
			+ " LIKE '%" + map.get("searchWord") + "%' ";
		}

		query += "        ORDER BY idx DESC "
				+ "    ) Tb "
				+ " ) "
				+ " WHERE rNum BETWEEN ? AND ?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();


			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));

				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("Select List Error");
			e.printStackTrace();
		}
		return board;
	}//list 메서드 끝

	public int inesrtWrite(MVCBoardDTO dto) {
	
		int result = 0;
		try {
			String query = "INSERT INTO mvcboard ("
					+ "idx, name, title, content, ofile, sfile, pass)"
					+ "VALUES("
					+ "seq_board_num.NEXTVAL,?,?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());

			result = psmt.executeUpdate(); // 리턴값은 영향을 받은 행의 수


		} catch (Exception e) {
			System.out.println("Error for insert data");
			e.printStackTrace();
		}
		return result;
	} //insert end


	public void downCountPlus(String idx) {
		String sql = "update mvcboard set downcount= downcount+1 where idx = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {

		}
	}//다운로드 횟수 증가

	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "SELECT * FROM mvcboard WHERE idx=? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();

			if(rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}


		} catch (Exception e) {
			System.err.println("detail view Err");
			e.printStackTrace();
		}
		return dto;
	}//selectView end

	public void visitCountPlus(String idx) {
		String sql = "UPDATE mvcboard SET "
				+ " visitcount = visitcount+1 "
				+ "WHERE idx = ? " ;
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeQuery();
		} catch (Exception e) {
			System.err.println("update visitCount Err");
			e.printStackTrace();
		}
	}//visitcount end
	
	
	  public boolean confirmPassword(String pass, String idx) {
	        boolean isCorr = true;
	        try {
	            String sql = "SELECT COUNT(*) FROM mvcboard WHERE pass=? AND idx=?";
	            psmt = con.prepareStatement(sql);
	            psmt.setString(1, pass);
	            psmt.setString(2, idx);
	            rs = psmt.executeQuery();
	            rs.next();
	            if (rs.getInt(1) == 0) {
	                isCorr = false;
	            }
	        }
	        catch (Exception e) {
	            isCorr = false;
	            e.printStackTrace();
	        }
	        return isCorr;
	    }// check pwd end
	  
	  
	    public int deletePost(String idx) {
	        int result = 0;
	        try {
	            String query = "DELETE FROM mvcboard WHERE idx=?";
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, idx);
	            result = psmt.executeUpdate();
	        }
	        catch (Exception e) {
	            System.out.println("게시물 삭제 중 예외 발생");
	            e.printStackTrace();
	        }
	        return result;
	    }// delete end;
	    
	    


}//end class
