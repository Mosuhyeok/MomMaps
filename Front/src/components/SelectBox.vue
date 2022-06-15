<template>
  <div>
    <div class="container-drop p-5 bg-dark">
      <select class="drop-child" name="sido" id="sido"></select>
      <select class="drop-child" name="gugun" id="gugun">
        <option value="">시/구/군</option>
      </select>

      <select class="drop-child" name="dong" id="dong">
        <option value="">동</option>
      </select>
    </div>
    <base-input label="Single select">
      <el-select v-model="selects.simple" filterable
                placeholder="Simple select">
        <el-option v-for="option in selectOptions"
                  :key="option.label"
                  :label="option.label"
                  :value="option.value">
        </el-option>
      </el-select>
    </base-input>
  </div>
</template>
<script>
// var address;
// import $ from "jquery";
// export default {};
// //전국 도시 정보 가져오기
// let aptUrl = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
// let regcode = "*00000000";
// let selectDong = "nothing";
// // 전국 특별/광역시, 도
// // https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000
// $.ajax({
//   url: aptUrl,
//   type: "GET",
//   data: {
//     regcode_pattern: regcode,
//   },
//   dataType: "json",
//   success: function (response) {
//     let code = ``;
//     $.each(response.regcodes, function (i, regcode) {
//       code += `
//               <option value="${regcode.code}">${regcode.name}</option>
//               `;
//     });
//     $("#sido")
//       .empty()
//       .append('<option value="">도/광역시</option>')
//       .append(code);
//   },
//   error: function (xhr, status, msg) {
//     console.log("상태값 : " + status + " Http에러메시지 : " + msg);
//   },
// });

// $(document).on("change", "#sido", function () {
//   regcode = $(this).val().substr(0, 2) + "*00000";
//   address = $("#sido option:selected").text();
//   console.log(address);
//   addressSearch(address);
//   $.ajax({
//     url: aptUrl,
//     type: "GET",
//     data: {
//       regcode_pattern: regcode,
//       is_ignore_zero: true,
//     },
//     dataType: "json",
//     success: function (response) {
//       for (let i = 0; i < response.regcodes.length; i++) {
//         if (i != response.regcodes.length - 1) {
//           if (
//             response.regcodes[i].name.split(" ")[1] ==
//               response.regcodes[i + 1].name.split(" ")[1] &&
//             response.regcodes[i].name.split(" ").length !=
//               response.regcodes[i + 1].name.split(" ").length
//           ) {
//             response.regcodes.splice(i, 1);
//             i--;
//           }
//         }
//       }
//       let code = ``;
//       let name = "";
//       $.each(response.regcodes, function (i, regcode) {
//         if (regcode.name.split(" ").length == 2)
//           name = regcode.name.split(" ")[1];
//         else
//           name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
//         code += `
//               <option value="${regcode.code}">${name}</option>
//               `;
//       });
//       $("#gugun")
//         .empty()
//         .append('<option value="">시/구/군</option>')
//         .append(code);
//       $("#dong").empty().append('<option value="">동</option>').append(code);
//     },
//     error: function (xhr, status, msg) {
//       console.log("상태값 : " + status + " Http에러메시지 : " + msg);
//     },
//   });
// });

// $(document).on("change", "#gugun", function () {
//   regcode = $(this).val().substr(0, 5) + "*";
//   address = address.split(" ")[0] + " " + $("#gugun option:selected").text();
//   console.log(address);
//   addressSearch(address);
//   selectDong = "nothing";
//   $.ajax({
//     url: aptUrl,
//     type: "GET",
//     data: {
//       regcode_pattern: regcode,
//       is_ignore_zero: true,
//     },
//     dataType: "json",
//     success: function (response) {
//       let code = ``;
//       let idx = 2;
//       $.each(response.regcodes, function (i, regcode) {
//         if (response.regcodes[i].name.split(" ").length != 3) idx = 3;
//         code += `
//               <option value="${regcode.code}">${
//           regcode.name.split(" ")[idx]
//         }</option>
//               `;
//       });
//       $("#dong").empty().append('<option value="">동</option>').append(code);
//     },
//     error: function (xhr, status, msg) {
//       console.log("상태값 : " + status + " Http에러메시지 : " + msg);
//     },
//   });
// });

// $(document).on("change", "#dong", function () {
//   regcode = $(this).val().substr(0, 4) + "*";
//   console.log(address);
//   selectDong = $("#dong option:selected").text();
//   address =
//     address.split(" ")[0] + " " + address.split(" ")[1] + " " + selectDong;
//   console.log(address);
//   addressSearch(address);
//   callAptFromDb(selectDong);
// });

// function addressSearch(address, info) {
//   geocoder.addressSearch(address, function (result, status) {
//     // 정상적으로 검색이 완료됐으면
//     if (status === kakao.maps.services.Status.OK) {
//       var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

//       // 결과값으로 받은 위치를 마커로 표시합니다
//       var marker = new kakao.maps.Marker({
//         map: map,
//         position: coords,
//         clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
//       });
//       markers.push(marker);

//       // 마커에 클릭이벤트를 등록합니다
//       kakao.maps.event.addListener(marker, "click", function () {
//         // 마커 위에 인포윈도우를 표시합니다
//         console.log("아파트명 : " + $(info).find("아파트").text());
//         window.location.replace("apartment_dong.html");
//       });

//       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
//       map.setCenter(coords);
//     }
//   });
// }
//
//
</script>
