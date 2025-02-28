package Ex0712;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResultController
 */
@WebServlet("/Ex0712/result.do")
public class ResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RegisterDTO dto = new RegisterDTO();
		dto.setFirstname(request.getParameter("firstname"));
		dto.setLastname(request.getParameter("lastname"));
		dto.setCountry(request.getParameter("country"));
		dto.setSubject(request.getParameter("subject"));
		
		
		request.setAttribute("userInfo", dto);
		//response.sendRedirect("calcResult.jsp");
		getServletContext().getRequestDispatcher("/Register/result.jsp").forward(request, response);
	}

}
