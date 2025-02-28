<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSTL, EL</h2>
	<h3>Set, Out</h3>
	<c:set var="product1" value="<h2>애플 아이폰</h2>"></c:set>
	<c:set var="product2" value="삼성갤럭시노트"></c:set>
	<c:set var="intArray" value="${[1,2,3,4,5] }"></c:set>
	
	<p>
	product1(jstl):
	<c:out value="${product1}" default="Not registered" escapeXml="true" />
	</p>
	<p>
	product(e1):${product1}
	</p>
	<p>
	intArray[2]: ${intArray[2]} 
	</p>
	
	<h3>forEach</h3>
	<ul>
		<c:forEach var="num" varStatus="i" items="${intArray}">
			<li>${i.index} : ${num}</li>
		</c:forEach>
	</ul>
	
	
	<h3>if</h3>
	<c:set var="checkout" value = "true" />
	<c:if test= "${checkout }">
		<p>주문 제품 : ${product2} </p>
	</c:if>
	<c:if test="${!checkout}">
		<p>주문제품이 아님</p>
	</c:if>
	
	<c:if test="${!empty product2 }">
		<p><b>${product2} 이미 추가됨 !.</b></p>
	</c:if>
	
	
	<h3>choose</h3>
	<c:choose>
		<c:when test="${checkout }">
			<p>주문 제품 : ${product2} </p>
		</c:when>
	<c:otherwise>
		<p>주문 제품이 아님!</p>
	</c:otherwise>

	</c:choose>
	
	
	<h3>forTokens</h3>
	<c:forTokens var="city" items="Seoul|Tokyo|New York|Toronto" delims="|" varStatus="i">
		<c:if test="${i.first}">도시 목록 : </c:if>
		${city}
		<c:if test="${!i.last}">,</c:if>
	
	</c:forTokens>
	
</body>
</html>