<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!
	String [] members = {"홍길동", "김길동", "김사랑", "박사랑"};
	int num1 = 10;
	
	int calc(int num2){
		return num1 + num2;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3><%=calc(10)%></h3>
<h3><% out.println(calc(10));%></h3>

<ul>
	<%for(String name : members) {%>
	<li><%=name %></li>
	<%} %>
</ul>
	


</body>
</html>