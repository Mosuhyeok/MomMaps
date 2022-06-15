<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>마이페이지</title>

</head>
<body>
    <%@ include file="./include/header.jsp"%>


    <section class="vh-100">
        <div class="container">
            <div
                class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card shadow-2-strong" style="border-radius: 1rem">
                        <div class="card-body p-5 text-center">
                            <h3 class="mb-5">마이페이지</h3>
                                <table class="mx-5 w-100">
                                    <tr>
                                        <td>아이디</td>
                                        <td>
                                            <div class="form-outline mb-2">
                                                <input type="text" id="id" class="form-check-inline mx-5"
                                                    name="id" value="${user.id}" required readonly />
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>비밀번호</td>
                                        <td>
                                            <div class="form-outline mb-2">
                                                <input type="text" id="password"
                                                    class="form-check-inline mx-5" name="password"
                                                    value="${user.password}" required />
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>이름</td>
                                        <td>
                                            <div class="form-outline mb-2">
                                                <input type="text" id="name" class="form-check-inline mx-5"
                                                    name="name"
                                                    value="${user.name}" required />
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>주소</td>
                                        <td>
                                            <div class="form-outline mb-2">
                                                <input type="text" id="email"
                                                    class="form-check-inline mx-5" name="address"
                                                    value="${user.email}" required />
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>전화번호</td>
                                        <td>
                                            <div class="form-outline mb-2">
                                                <input type="text" id="phone" class="form-check-inline mx-5"
                                                    name="phone" value="${user.phone}" required />
                                            </div>
                                        </td>
                                    </tr>
                                </table>

                                <button class="btn btn-primary btn-lg btn-block mt-5" type="button">확인</button>
                                <button class="btn btn-primary btn-lg btn-block mt-5"  type="button" id="update">수정</button>
                                <button class="btn btn-danger btn-lg btn-block mt-5"  type="button" id="delete">탈퇴</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

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
    $("#delete").click(function(){
        $.ajax({
            url:"/user",
            type:"DELETE",
            contentType : "application/json;charset=utf-8",
            data : JSON.stringify(getFormData()),
            success:function(){
            	alert("회원 탈퇴가 완료 됐습니다");
            	location.href="/";
            },
            error : function() {
                alert("요청 실패");
            }
        })
    });
    $("#update").click(function(){
        $.ajax({
            url:"/user",
            type:"PUT",
            contentType : "application/json;charset=utf-8",
            data : JSON.stringify(getFormData()),
            success:function(){
                alert("회원 정보가 수정되었습니다.");
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