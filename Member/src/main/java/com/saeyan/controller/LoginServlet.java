package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/login.jsp";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")!= null) {
			url = "main.jsp";		
			}
		request.getRequestDispatcher(url).forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/login.jsp";
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		MemberDAO dao = MemberDAO.getInstatnce();
		int result = dao.userCheck(userid, pwd);
		dao.close();
		if(result == 1) {
//			MemberDTO dto = dao.getMember(userid);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", userid);
			request.setAttribute("message", "회원 가입에 성공했습니다.");
			url = "main.jsp";
		}else if(result ==0) {
			request.setAttribute("message", "비밀번호가 틀립니다.");
			
		}else if(result == -1) {
			request.setAttribute("message", "존재하지 않는 회원 입니다.");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
