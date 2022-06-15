<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${pageContext.request.contextPath}">
</c:set>
<html>

	<head>
	
	<link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />	
	
	<script type="text/javascript" src="/js/login.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	e23caecb7d2433a3910a60cb34d4b660&libraries=services"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="/js/kakaomap.js"></script>
	<script type="text/javascript" src="/js/dealInfo.js"></script>
	
	
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="/css/styles.css" rel="stylesheet" />
	<!--  JQuery -->
	 <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	
	</head>
	<body>
	
	
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="/"><h3>행복한 우리 집</h3></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars ms-1"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
					<li class="nav-item"><a class="nav-link">공지 사항</a></li>
					<li class="nav-item"><a class="nav-link" href="#portfolio">오늘의
							뉴스</a></li>
					<li class="nav-item"><a class="nav-link" href="/post">정보 게시판</a></li>
					
					<c:if test="${!empty user}">
						<li class="nav-item"><a class="nav-link" href="/post/save">글쓰기</a></li>
					</c:if>
					
					
<%-- 					<c:if test="${!empty user}">
						<li class="nav-item"><a class="nav-link" href="#team">관심
								지역 설정</a></li>
						<li class="nav-item"><a class="nav-link"
							href="#contact">관심 지역 둘러보기</a></li>
					</c:if> --%>
					
					
					
					<li class="nav-item"><a class="nav-link"
						href="/deal/search">지역 검색</a></li>

					<c:if test="${empty user}">
						<li class="nav-item">
							<button class="btn-info btn-outline-light"
								onclick="location.href='/user/join'">회원가입</button>
						</li>
						<li class="nav-item">
							<button class="btn-info btn-outline-light"
								onclick="location.href='/user/login'">로그인</button>
						</li>
					</c:if>
					<c:if test="${!empty user}">
						<li class="nav-item">
							<button class="btn-info btn-outline-light"
								onclick="location.href='/user/myInfo'">마이페이지</button>
						</li>
						<li class="nav-item">
							<button class="btn-info btn-outline-light"
								onclick="location.href='/user/logout'">로그아웃
							</button>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	</body>
</html>