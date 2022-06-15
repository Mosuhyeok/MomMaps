<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>행복한 우리집</title>


</head>
<body id="page-top">
	<%@ include file="./include/header.jsp"%>
	<script type="text/javascript">
		let msg = "${msg}";
		if(msg) {
			alert(msg);
		}
	
	</script>

	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-heading text-uppercase">Happy House</div>
			<div class="masthead-subheading">행복한 우리 집</div>
		</div>
	</header>
	<div class="container-drop p-5 bg-dark">
		<select class="drop-child" name="sido" id="sido"></select> <select
			class="drop-child" name="gugun" id="gugun">
			<option value="">시/구/군</option>
		</select> <select class="drop-child" name="dong" id="dong">
			<option value="">동</option>
		</select>
	</div>
	</div>
	<!-- Services-->
	<section class="page-section" id="services">
		<div class="container">
			<div id="map" style="width: 100%; height: 600px"></div>

		</div>
	</section>
	<!-- Portfolio Grid-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">오늘의 뉴스</h2>
				<h3 class="section-subheading text-muted">서울 아파트 매매 전세 다른 양상..
					상승.. 아시아 경제</h3>
				<div class="ul-list">
					<li><a href="#">서울 입주 2년차 아파트 전셋값 1억 4천만원</a> 한겨레</li>
					<li><a href="#">12.16 거래한파..매수자가 사라졌다</a> 아시아경제</li>
					<li><a href="#">재건축 대안 또는 증축 리모델링</a> 동아일보</li>
					<li><a href="#">고가기준 9억, 서울 아파트 중간값 됐다.</a> 동아일보</li>
				</div>
			</div>
		</div>
	</section>
	<!-- About-->
	<section class="page-section" id="about">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">주변 탐방</h2>
			</div>
		</div>
	</section>
	<!-- Team-->
	<section class="page-section bg-light" id="team">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">관심 지역 설정</h2>
			</div>
		</div>
	</section>
	<!-- Contact-->
	<section class="page-section" id="contact">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">관심 지역 둘러보기</h2>
			</div>
		</div>
	</section>
	<%@ include file="./include/footer.jsp" %>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

	<script type="text/javascript">
		//로그인 상태일 경우 보여줄 엘리먼트 구분하기
		checkLoginState();
	</script>
</body>
</html>
