<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Our Site</title>
</head>
<script>
//tern
</script>
<body>
	<h2>회원가입</h2>
	<hr>
	<form action="/join" method="post" >
			id : <input type="text" name="id" size="10" placeholder="id"><br>
			pw : <input type="password" name="pw" size="10"> <br>
			name : <input type="text" name="name" size="10" placeholder="이름"><br> 
			birth : <input type="date" name="birth" size="10"> <br>
			gender : <input type="radio" name="gender" value="boy">남 
			<input type="radio" name="gender" value="girl">여 <br>
			취미 : <input type="checkbox" name="hobby" value="game">게임
			<input type="checkbox" name="hobby" value="sing">노래
			<input type="checkbox" name="hobby" value="code">코딩<br>
			<input type="submit" value="회원가입">
		</form>
	
</body>
</html>