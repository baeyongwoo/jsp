package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       MVCBoardDAO dao = new MVCBoardDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DAO 생성. MVCBoardDAO는 DBConnPool의 자식이므로 DBConnPool의 생성자를 통해서 DB연결.
		MVCBoardDAO dao = new MVCBoardDAO();
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int totalCount = dao.selectCount(map);
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		
		if(searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
			
		}
		
//		페이지 처리
		int pageSize = 10;
		int blockPage = 5;
		
		int pageNum = 1;
		String pageTemp = request.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);
		
		int start = (pageNum -1) * pageSize + 1;
		int end = pageNum * pageSize;
		
		map.put("start", start);
		map.put("end", end);
//		페이지 처리 끝
		List<MVCBoardDTO> boardList = dao.selectListPage(map);
		dao.close();
		
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/List.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
