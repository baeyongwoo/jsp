package ch08;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcController
 */
@WebServlet("/calcControl")
public class CalcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Calculator cal = new Calculator();
		cal.setN1(Integer.parseInt(request.getParameter("n1")));
		cal.setN2(Integer.parseInt(request.getParameter("n2")));
		cal.setOp(request.getParameter("op"));
		
//		int n1 = Integer.parseInt(request.getParameter("n1"));
//		int n2 = Integer.parseInt(request.getParameter("n2"));
		long result = 0;
		
		switch (request.getParameter("op")) {
		case "+" : result = cal.getN1()+cal.getN2(); break;
		case "-" : result = cal.getN1()+cal.getN2(); break;
		case "*" : result = cal.getN1()+cal.getN2(); break;
		case "/" : result = cal.getN1()+cal.getN2(); break;
		}
		
		request.setAttribute("result", result);
		//response.sendRedirect("calcResult.jsp");
		getServletContext().getRequestDispatcher("/calcResult.jsp").forward(request, response);
		
		
	}

}
