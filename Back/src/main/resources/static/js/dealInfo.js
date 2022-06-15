//동별 아파트 실거래가 조회
var url =
	"http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"; /*URL*/
//var queryParams = '?' + encodeURIComponent('ServiceKey') + '='+'일반 인증키(Encoding)'; /*Encoding Service Key*/
var queryParams =
	"?" +
	encodeURIComponent("ServiceKey") +
	"=" +
	"wfzSJyo1jwQ2uqwGf5HM3B1dXxugMtaZDRAIEubGGewU%2FntYJONb%2BHgg0TOsQhE%2Fa%2FgVnzHnuv1XOxknjnuKkQ%3D%3D"; /*Encoding Service Key*/
queryParams +=
	"&" +
	encodeURIComponent("LAWD_CD") +
	"=" +
	encodeURIComponent("11110"); /**/
queryParams +=
	"&" +
	encodeURIComponent("DEAL_YMD") +
	"=" +
	encodeURIComponent("201512"); /**/

var xhr = new XMLHttpRequest();
xhr.open("GET", url + queryParams);
xhr.onreadystatechange = function() {
	if (this.readyState == 4) {
		console.log("Status: " + this.status);
		console.log(
			"nHeaders: " + JSON.stringify(this.getAllResponseHeaders())
		);
		console.log("nBody: " + this.responseText);
	}
};
xhr.send("");

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

function makeList(data) {
	var aptlist = ``;
	setMarkers(null);
	$(data)
		.find("item")
		.each(function() {
			let current = $(this).find("법정동").text().trim();
			if (selectDong == "nothing" || current == selectDong) {
				aptlist += `<tr>
				<td>${$(this).find("아파트").text()}</td>
				<td>${$(this).find("법정동").text()}</td>
				<td>${$(this).find("전용면적").text()}</td>
				<td>${$(this).find("거래금액").text()}</td>
				</tr>`;
				address = current + " " + $(this).find("지번").text().trim();
				addressSearch(address, this);
			}
		});
}

$(document).ready(function() {
	// server에서 넘어온 data
	$.ajax({
		url: url,
		type: "GET",
		dataType: "xml",
		data: {
			//ServiceKey: '일반인증키(Decoding)', /*Decoding Service Key*/
			ServiceKey:
				"qhnFzA27rxg0Z/Pa04245VBwOtGjhLaBkJAY696yskE+QSVAZRv5WmXdlYAaYi73WPpY5rRFrCEGPfAd3xtnkg==" /*Decoding Service Key*/,
			LAWD_CD: "11110",
			DEAL_YMD: "201512",
		},
		success: function(response) {
			makeList(response, true);
		},
		error: function(xhr, status, msg) {
			console.log("상태값 : " + status + " Http에러메시지 : " + msg);
		},
	});
});
function callAptFromDb(dong) {
	$.ajax({
		url: "/deal/getAptList",
		type: "post",
		dataType: "json",
		data: { "dong" : dong },
		success: function(response) {
			makeListByJson(response);
		},
		error: function(xhr, status, msg) {
			console.log("상태값 : " + status + " Http에러메시지 : " + msg);
		},
	});
}


function makeListByJson(data) {
	var aptlist = ``;
	
	for(var i=0; i<data.length; i++) {
		aptlist += `<tr>
				<td>${data[i].aptName}</td>
				<td>${data[i].dongName}</td>
				<td>${data[i].area}</td>
				<td>${data[i].dealAmount}</td>
				</tr>`;
		//address = current + " " + data[i].jibun.trim();
	}
	$("#aptinfo").empty().append(aptlist);
	$("tr:first").css("background", "darkgray").css("color", "white");
	$("tr:even").css("background", "lightgray");
}
