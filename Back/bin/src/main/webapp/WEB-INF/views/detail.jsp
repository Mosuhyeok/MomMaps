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

    <p>글 번호 : ${postCommentDto.postDto.id} </p>
    <p>글쓴이 : ${postCommentDto.postDto.author} </p>
    <p>제목 : ${postCommentDto.postDto.title} </p>
    <p>내용 : ${postCommentDto.postDto.content} </p>

    <c:if test="${postCommentDto.postDto.author eq user.id}">
    <input type="hidden" value = ${postCommentDto.postDto.id} id="id">
      <button class="btn btn-primary btn-lg btn-block mt-5"  type="button" id="btn-update-page">수정</button>
          <button class="btn btn-danger btn-lg btn-block mt-5"  type="button" id="btn-delete">삭제</button>
    </c:if>
    
    <c:if test="${not empty postCommentDto.commentDtos}">
        <h2>댓글 리스트</h2>
        <div class="input-group input-group-sm" role="group" style="text-align: left">
            <table class="table table-striped table-bordered" border="1"
                width="800px" align="left">
                <c:forEach var="comment" items="${postCommentDto.commentDtos}">
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td>닉네임 : ${comment.author}</td>
                    </tr>
                    <tr>
                        <td>댓글 내용 : ${comment.content}</td>
                    </tr>
                </c:forEach>
            </table>
          </div>
    </c:if> 


    <c:if test="${!empty user}">
    <div class="card-body">
        <form class="form-horizontal">
            <div class="row">
                <div class="form-group col-sm-8">
                    <input type = "hidden" id ="post_id" value = ${postCommentDto.postDto.id}>
                    <input class="form-control input-sm" id="content" type="text" placeholder="댓글 입력..."> 
                </div> 
                <div class="form-group col-sm-2">
                    <input type="hidden" id="author" value= ${user.id}>
                        <button type="button" class="btn btn-primary btn-sm btn-block replyAddBtn" id="btn-comment-save">댓글 작성</button> 
                </div> 
            </div> 
        </form> 
    </div>
    </c:if>
    




<%@ include file="./include/footer.jsp" %>
  </body>
    <script src="/js/post.js"></script>
    <script src="/js/comment.js"></script>
</html>

