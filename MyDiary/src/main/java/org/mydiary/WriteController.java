package org.mydiary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WriteController
 */
@WebServlet("/write.do")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/DiaryWrite.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String weather = request.getParameter("weather");
			
			DiaryboardDto dto = new DiaryboardDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setWeather(weather);
			
			DiaryboardDao dao = new DiaryboardDao();
			 
			
			if(dao.write(dto) ==1) {
				response.sendRedirect("/MyDiary/List.do"); //성공
			}else {
				response.sendRedirect("/MyDiary/write.do?flag=false");
			}
			
	}

}
