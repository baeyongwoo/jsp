<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
        *{
            margin: 0px;
            padding: 0px;
        }

        header{
            width: 100%;
            height: 200px;
            background-color: black;
            color: white;

        }
        section{
            float: left;
            width: 80%;
            height: 500px;
            background-color: rgb(180, 179, 179);
        }
        aside{
            float: left;
            width: 20%;
            height: 500px;
            background-color: rgba(39, 38, 38, 0.527);
        }
        
        /* 코드가 위에서 아래로 진행 할 때 float를 따라가려는 경향이 있어 원하는 레이아웃이 안나올 수 있다. 그 때 clear:both; 하면 된다 */

   </style>
<body>
 <header>글로벌아이티</header>
    <%@include file="nav.jsp" %>
	<section></section>
    <aside></aside>
    <%@include file="footer.jsp" %>
    <!-- <footer></footer> -->
</body>
</html>