<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<script type="text/javascript">
    function validateForm(form) {  // 필수 항목 입력 확인
    	/* return false; 전송안되게 하는 법 */
        if (form.name.value == "") {
            alert("작성자를 입력하세요.");
            form.name.focus();
            return false;
        }
        if (form.title.value == "") {
            alert("제목을 입력하세요.");
            form.title.focus();
            return false;
        }
        if (form.content.value == "") {
            alert("내용을 입력하세요.");
            form.content.focus();
            return false;
        }
        if (form.pass.value == "") {
            alert("비밀번호를 입력하세요.");
            form.pass.focus();
            return false;
        }
    }
    
    function validation(){
    	console.log("파일첨부 함수실행");
    	let fileName = document.getElementById('ofile');
    	let ext = fileName.files[0].name.split('.')[1]
    	//let ext = fileName.value.split('.')[1] //파일 확장자 체크
    	if(ext==="exe"){
    		alert("실행파일은 업로드 할 수 없습니다.");
    		fileName = "";
    		return false;
    	}
    	
    	console.log();
    	
    	let fileSize = fileName.files[0].size;
    	
    	if(fileSize > 5*1024*1024){
    		alert("파일크기는 5Mbyte를 초과할 수 없습니다.");
    		return false;
    	}
    	
    }
</script>
</head>
<h2>파일 첨부형 게시판 - 글쓰기(Write)</h2>
<form name="writeFrm" method="post"  enctype="multipart/form-data"
      action="/write.do" onsubmit="return validateForm(this);">
      <!-- this : form 태그 자기자신 -->
       
<table border="1" width="90%">
    <tr>
        <td>작성자</td>
        <td>
            <input type="text" name="name" style="width:150px;" />
        </td>
    </tr>
    <tr>
        <td>제목</td>
        <td>
            <input type="text" name="title" style="width:90%;" />
        </td>
    </tr>
    <tr>
        <td>내용</td>
        <td>
            <textarea name="content"  rows="20" style="width:90%;"></textarea>
        </td>
    </tr>
    <tr>
        <td>첨부 파일</td>
        <td>
            <input type="file" name="ofile" id="ofile" onchange="validation();"/>
        </td>
    </tr>
    <tr>
        <td>비밀번호</td>
        <td>
            <input type="password" name="pass" style="width:100px;" />
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <button type="submit">작성 완료</button>
            <button type="reset">RESET</button>
            <button type="button" onclick="location.href='/list.do';">
                목록 바로가기
            </button>
        </td>
    </tr>
</table>    
</form>
</body>
</html>