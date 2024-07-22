package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberDTO;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("session : " + session);
		if(session.getAttribute("loginUser")!= null) {
			System.out.println("sessiondl null이 아닐경우");
			request.getRequestDispatcher("/login.do").forward(request, response);
		}else {
			request.getRequestDispatcher("/member/join.jsp").forward(request, response);
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberDTO userInfo = new MemberDTO();
		userInfo.setName(request.getParameter("name"));
		userInfo.setUserid(request.getParameter("userid"));
		userInfo.setPwd(request.getParameter("pwd"));
		userInfo.setEmail(request.getParameter("email"));
		userInfo.setPhone(request.getParameter("phone"));
		userInfo.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		MemberDAO dao = MemberDAO.getInstatnce();
		if(dao.JoinMember(userInfo) != -1) {
			response.sendRedirect("/login.do");
		}
		
		
		
		
	}

}
