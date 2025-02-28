package Example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 * Servlet implementation class Join
 */
@WebServlet("/join")
public class Joins extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Joins() {
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
		//아래 두줄은 getParameter 전에 사용해야 적용됨
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		
//		String id = (String)request.getParameter("id");
//		String pw = (String)request.getParameter("pw");
//		String name = (String)request.getParameter("name");
//		String birth = (String)request.getParameter("birth");
//		String gender = (String)request.getParameter("gender");
//		String [] hobby = (String[])request.getParameterValues("hobby");
		
		
		Map<String, String> userInfo = new HashMap<>() {{
			put("id" , (String)request.getParameter("id"));
			put("pw" , (String)request.getParameter("pw"));
			put("name" , (String)request.getParameter("name"));
			put("birth" , (String)request.getParameter("birth"));
			put("gender" , (String)request.getParameter("gender"));
		}};
		
		String hobby2 = String.join(", ", request.getParameterValues("hobby"));
		userInfo.put("hobby", hobby2);
		
	
		
		PrintWriter out = response.getWriter();
		out.append("<html><body><h2>회원 정보</h2><hr>")
//		.append("아이디 : " + id + 
//				"<br>이름 : " + name +
//				"<br>생년월일 : " + birth + 
//				"<br>성별 : " + gender +
//				"<br>취미 : " +  Arrays.toString(hobby) + 
//				"</body></html>");
		
		.append("아이디 : " + userInfo.get("id") + 
				"<br>이름 : " + userInfo.get("name") +
				"<br>생년월일 : " + userInfo.get("birth") + 
				"<br>성별 : " + userInfo.get("gender") +
				"<br>취미 : " );
//		for(String hobbylist : hobby) {
//			out.append(hobbylist + " ");
//		}
		out.append(userInfo.get("hobby"));
		out.append("</body></html>");
				
		
		
	}

}
