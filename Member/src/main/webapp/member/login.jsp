<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="script/member.js"></script>
<body>
	<h2>로그인</h2>
	<form action="login.do" method="post" name="frm">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" value="${userid}"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="로그인" onclick="return loginCheck()">
					<input type="button" value="회원가입" onclick="location.href='join.do'">
				</td>
			
			</tr>
			<tr>
				<td colspan="2">${message}</td>
			</tr>
			
		</table>
	
	</form>
</body>
</html>