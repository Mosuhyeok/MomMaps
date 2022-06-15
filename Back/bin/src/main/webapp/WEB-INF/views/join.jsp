<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>회원가입</title>
  </head>
  <body>
    <%@ include file="./include/header.jsp" %>

	<br><br><br>
    <div class="container mt-5 px-5 mb-lg-5">
      <h3>회원가입</h3>

        <div class="mb-3 mt-3">
          <label for="id" class="form-label">아이디 : </label>
          <input
            type="text"
            class="form-control w-50"
            id="id"
            name="id"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">비밀번호 : </label>
          <input
            type="password"
            class="form-control w-50"
            id="password"
            placeholder="영문 숫자 포함 6자리 이상"
            name="password"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="name" class="form-label">이름 : </label>
          <input
            type="text"
            class="form-control w-50"
            id="name"
            placeholder="User Name"
            name="name"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="address" class="form-label">이메일 : </label>
          <input
            type="text"
            class="form-control w-50"
            id="email"
            placeholder="email"
            name="email"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="phone" class="form-label">전화번호 : </label>
          <input
            type="tel"
            class="form-control w-50"
            id="phone"
            placeholder="010-xxxx-xxxx"
            name="phone"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <button class="btn btn-primary" id="regist">등록</button>
    </div>

<%@ include file="./include/footer.jsp" %>
  </body>
<script>
function getFormData(){
    let user={};
    user.id=$("#id").val();
    user.password=$("#password").val();
    user.name=$("#name").val();
    user.email=$("#email").val();
    user.phone=$("#phone").val();
    return user;
}

$(function(){
    $("#regist").click(function(){
        $.ajax({
            url:"/user",
            type:"POST",
            contentType : "application/json;charset=utf-8",
            data : JSON.stringify(getFormData()),
            success:function(){
            	alert("회원 가입이 완료 됐습니다");
            	location.href="/";
            },
            error : function() {
                alert("요청 실패");
            }
        })
    });
});
</script>
</html>