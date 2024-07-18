<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>a{text-decoration:none;}</style>
</head>
<body>

	<div class="container">
	
    <h2>수업 게시판 - 목록 보기(List)</h2>

    <!-- 검색 폼 -->
    <form method="get">  
    <table  width="100%">
    <tr>
        <td align="center">
        	<div class="input-group mb-3" style="width:50%" >
            	<select name="searchField" class="form-control" style="flex:1;">
                	<option value="title">제목</option>
                	<option value="name">작성자</option>
            	</select>
            	<input type="text" name="searchWord" value="${map.searchWord}" class="form-control" style="flex:4"/>
            	<input class="btn btn-primary btn-sm" type="submit" value="검색" />
        	</div>
        </td>
    </tr>
    </table>
    </form>

    <!-- 목록 테이블 -->
    <table class="table" border="1" width="90%">
        <tr align="center">
            <th width="10%">번호</th>
            <th width="*">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="15%">작성일</th>
            <th width="8%">첨부</th>
        </tr>
<c:choose>    
    <c:when test="${ empty boardList }">  <!-- 게시물이 없을 때 -->
        <tr>
            <td colspan="6" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
    </c:when>
    <c:otherwise>  <!-- 게시물이 있을 때 -->
        <c:forEach items="${ boardList }" var="row" varStatus="loop">    
        <tr align="center">
            <td>  <!-- 번호 -->
                ${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}   
            </td>
            <td align="left">  <!-- 제목(링크) -->
                <a href="/view.do?idx=${ row.idx }">${ row.title }</a> 
            </td> 
            <td>${ row.name }</td>  <!-- 작성자 -->
            <td>${ row.visitcount }</td>  <!-- 조회수 -->
            <td>${ row.postdate }</td>  <!-- 작성일 -->
            <td>  <!-- 첨부 파일 -->
            <c:if test="${ not empty row.ofile }">
                <a href="/download.do?ofile=${ row.ofile }&sfile=${ row.sfile }&idx=${ row.idx }">[Down]</a>
            </c:if>
            </td>
        </tr>
        </c:forEach>        
    </c:otherwise>    
</c:choose>
    </table>

    <!-- 하단 메뉴(바로가기, 글쓰기) -->
    <table  width="100%">
        <tr align="center">
            <td>
                ${ map.pagingImg }
            </td>
            <td width="100">
            <button type="button" class="btn btn-primary btn-sm" onclick="location.href='/write.do';">글쓰기</button></td>
        </tr>
    </table>
    </div>
</body>
</html>