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
	
	<c:if test="${empty user}">
		<script>location.href="/"</script>
	</c:if>
	
<article>
		<div class="container" role="main">
			<h2>글쓰기</h2>
			<form>
				<div class="mb-3">
					<label for="title">제목</label>
					<input type="text" class="form-control" name="title" id="title"  value = "${post.title}">
				</div>


				<div class="mb-3">
					<label for="reg_id">작성자</label>
					<input type="text" class="form-control" name="author" id="author" value = "${user.id}" readonly>
				</div>
				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" name="content" id="content" >${post.content}</textarea>
				</div>
			</form>
			<div >
				<input type="hidden" value = ${post.id} id = "id">
				<button type="button" class="btn btn-sm btn-primary" id="btn-update">수정</button>
				<button type="button" class="btn btn-sm btn-primary" id="btn-rollback">돌아가기</button>
			</div>
		</div>
	</article>
<%@ include file="./include/footer.jsp" %>
  </body>
  <script src="/js/post.js"></script>
</html>
