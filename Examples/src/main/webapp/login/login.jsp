<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginPage</title>
</head>
<script>
	

</script>
<body>
	<div class="login_area">
		<div class="login_tile">
		Login
		</div>
		<div class="login_input_area">
		<form action="loginProcess.jsp" method="post">
			ID : <input type="text" name="id">
			PW : <input type="password" name="pw">
			<input type="submit" value="login">
		</form>
		<button>아이디 찾기</button>
		<button>비밀번호 찾기</button>
		</div>
	</div>
</body>
</html>