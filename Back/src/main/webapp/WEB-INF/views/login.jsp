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


<title>로그인</title>
</head>
<body>
	<%@ include file="./include/header.jsp"%>
	<script type="text/javascript">
		let msg = "${msg}";
		if(msg) {
			alert(msg);
		}
	
	</script>
	

	<nav class="navbar navbar-expand-lg w-40 py-5 px-5">
		<a class="navbar-brand" href="index.jsp"><h3>행복한 우리 집</h3></a>
		<div class="container-fluid justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link text-white" href="#">공지사항</a>
				</li>
				<li class="nav-item"><a class="nav-link text-white" href="#">오늘의
						뉴스</a></li>
			</ul>
		</div>
	</nav>

	<section class="vh-100">
		<div class="container h-80">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card shadow-2-strong" style="border-radius: 1rem">
						<div class="card-body p-5 text-center">
							<h3 class="mb-5">로그인</h3>
							<form id="login-form" action="/user/login" method="post">
								<input type="hidden" name="action" value="login">
								<div class="form-outline mb-4">
									<input type="text" id="id" class="form-control form-control-lg"
										name="id" placeholder="아이디" required />
								</div>

								<div class="form-outline mb-4">
									<input type="password" id="password"
										class="form-control form-control-lg" name="password"
										placeholder="비밀번호" required />
								</div>

								<input type="submit" class="btn btn-primary btn-lg btn-block" value="로그인"/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

<%@ include file="./include/footer.jsp" %>

	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>
