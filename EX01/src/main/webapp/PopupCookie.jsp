<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	String chVal = request.getParameter("inactiveToday");

	if(chVal != null && chVal.equals("1")){
			Cookie cookie = new Cookie("PopupClose", "off");
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			out.println("쿠키 : 하루 동안 열지 않음");
	}

%>