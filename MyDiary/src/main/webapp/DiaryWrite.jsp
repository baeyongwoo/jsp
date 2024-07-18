<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	body { margin:20px;}
	
	.uploadResult {
		width: 100%;
		background-color : gray;
	}
	
	.uploadResult ul {
		display:flex;
		flex-flow: row;
		justify-content : center;
		align-items : center;
	}
	
	.uploadResult ul li {
		list-style : none;
		padding : 10px;
	}
	
	.uploadResult ul li img {
		width : 20px;
	}
</style>
</head>
<body>
	
	<header>
	<a href="/MyDiary/List.do" class="btn btn-dark">돌아가기</a>
	<div class="text-center">
	<h2>글작성</h2>
	</div>
	
</header>

<script>
function validateForm(form) {  // 필수 항목 입력 확인
	/* return false; 전송안되게 하는 법 */
    if (form.weather.value == "") {
        alert("날씨를 입력하세요.");
        form.name.focus();
        return false;
    }
	
	console.log("길이", );
    if (form.title.value == "" || form.title.value.length >=20) {
        alert("제목을 입력하거나 20글자 이하로 작성해주세요.");
        form.title.focus();
        return false;
    }
    if (form.content.value == "") {
        alert("내용을 입력하세요.");
        form.content.focus();
        return false;
    }
    
}



</script>

<div class="container">
	<div class="row">
		<div class="col-xs-12 w-70 p-3">

	<form action="/MyDiary/write.do" method="post" onsubmit="return validateForm(this);">
		<select name="weather" id="check" required="required">
			<option value= >--날씨선택--</option>
			<option value="맑음">맑음</option>
			<option value="구름">구름</option>
			<option value="비">비</option>
			<option value="눈">눈</option>
		</select>
		
		<input type="text" name="title" id="title" placeholder="제목" required="required" class="form-control"><br>

		<textarea rows="10" cols="50" name="content" required="required" class="form-control"></textarea>
		<div class="text-center">
		<input type="submit" value="작성완료" class="btn btn-dark" id="submitBtn">
		<input type="reset" value="다시작성" class="btn btn-warning">
	</div>
	</form>
</div>
</div>
</div>

	
	
	

</body>

</html>