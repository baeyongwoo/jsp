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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO dao = MemberDAO.getInstatnce();
		String userid = (String) session.getAttribute("loginUser");
		System.out.println("update usd session!" + userid);
		if(userid==null) {
			response.sendRedirect("/login.do");
		}else {
			
			request.setAttribute("mVo", dao.selectMember(userid));
			dao.close();
			request.getRequestDispatcher("/member/memberUpdate.jsp").forward(request, response);
		}
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		dto.setName(request.getParameter("name"));
		dto.setUserid(request.getParameter("userid"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setEmail(request.getParameter("email"));
		dto.setPhone(request.getParameter("phone"));
		dto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		MemberDAO dao = MemberDAO.getInstatnce();
		dao.UpdateMember(dto);
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		response.sendRedirect("/login.do");
		
		
	}

}
