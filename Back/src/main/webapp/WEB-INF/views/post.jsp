<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="" />
    <meta name="author" content="" />

  </head>
  <body>
  <%@ include file="./include/header.jsp" %>
	<br><br><br><br>
	<br><br><br><br>

	<c:if test="${!empty list}">
	
	
	<table class = "table table-hover table-striped text-center" style="border: 1px solid">
		<thead>
			<tr>
				<th> 번호</th>
				<th> 제목</th>
				<th> 글쓴이</th>
			</tr>
		</thead>
		
	<c:forEach var="post" items = "${list}">
		<tbody>
			<tr>
				<td>${post.id}</td>
				<td><a href="/post/${post.id}">${post.title}</a></td>
				<td>${post.author}</td>
			</tr>
		</tbody>	
	</c:forEach>
	</table>	
	</c:if>

	
	<c:if test="${empty list}">
		<h1> 등록된 게시글이 없습니다</h1>
	</c:if>
	
	
<%@ include file="./include/footer.jsp" %>
  </body>
</html>
