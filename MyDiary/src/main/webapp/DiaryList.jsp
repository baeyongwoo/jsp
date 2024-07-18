<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>
*{
text-decoration: none;
}

body {
	margin: 50px;
}
</style>
</head>
<body>

	<a href="/company/lobby"><button class="btn btn-dark">로비로가기</button></a>

	<div class="text-center">
		<h1>MyDiary</h1>
	</div>

	<hr />


	<table class="table table-dark table-hover text-center">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>글내용</th>
			<th>날씨</th>
			<th>쓴날짜</th>
		</tr>
		<c:forEach items="${boardList}" var="bl">
			<tr>
				<td><c:out value="${bl.no}" /></td>
				<td><a href="#"><c:out value="${bl.title}" /></a>
				</td>
				<td><c:out value="${bl.content}" /></td>
				<td><c:out value="${bl.weather}" /></td>
				<td><c:out value="${bl.postdate}" /></td>
			</tr>
		</c:forEach>

	</table>


	<div class="row text-center justify-content-center">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${btnMaker.prev}">
					<li class="page-item"><a class="page-link"
						href="/notice/noticeList?pageNum=${btnMaker.startPage - 1}">
							Previous</a></li>
				</c:if>

				<c:forEach begin="${btnMaker.startPage}" end="${btnMaker.endPage}"
					var="pageNum">
					<li
						class="page-item ${btnMaker.cri.pageNum == pageNum ? 'active' : ''}">
						<a class="page-link" href="/notice/noticeList?pageNum=${pageNum}">${pageNum}</a>
					</li>
				</c:forEach>

				<c:if test="${btnMaker.next}">
					<li class="page-item"><a class="page-link"
						href="/notice/noticeList?pageNum=${btnMaker.endPage + 1}">Next</a></li>
				</c:if>


			</ul>
		</nav>

		<div>
				<button class="btn btn-dark" onclick="location.href='/MyDiary/write.do'"> 글쓰러가기</button>

		</div>

	</div>

</body>


</html>