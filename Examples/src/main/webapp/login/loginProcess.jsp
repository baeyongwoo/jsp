<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login Sucess</title>
</head>
<script>
alert("성공");
</script>
<body>
	<div>
		ID : ${param.id}
		PW : ${param.pw}
	</div>
	<a href="login.jsp">로그인 페이지로</a>
</body>
</html>