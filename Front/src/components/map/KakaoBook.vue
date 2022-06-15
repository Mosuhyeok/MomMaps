<template>
  <div>
    <b-row style="width: 100%; height: 70px; float: none; margin: auto; background-color: #b1d182">
      <b-col md="4">
        <b-button v-b-toggle.sidebar-backdrop class="btnCol" style="position: absolute; width: 60px; height: 70px; left: 20px">
          <i class="ni ni-align-left-2"></i>
        </b-button>
        <sidebar></sidebar>
      </b-col>
      <b-col md="4" class="mt-3">
        <h3><b>전국 서점/학교 검색</b></h3>
      </b-col>
    </b-row>
    <div class="mt-3">
      <b-row class="justify-content-md-center">
        <b-col md="2" class="mt-2">
          <h5 class="text-right"><b>지역 검색: </b></h5>
        </b-col>
        <b-col class="col-lg-8">
          <b-row class="mb-4 text-center" style="width: 100%">
            <b-col class="sm-3">
              <b-form-select v-model="sidoCode" :options="sidonames" @change="gugunList" :options-field="sidoCode"></b-form-select>
            </b-col>
            <b-col class="sm-3">
              <b-form-select v-model="gugunCode" :options="gugunnames" @change="dongList"></b-form-select>
            </b-col>
            <b-col class="sm-3">
              <b-form-select v-model="dongCode" :options="dongnames" @change="List"></b-form-select>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
      <!-- <input type="button" @click="searchBook" value="아동서점" style="width: 100px" />
      <input type="button" @click="searchSchool" value="학교" style="width: 100px" />
      <b-form-select v-model="school" :options="schools" @change="selectschool" style="width: 100px"> </b-form-select>
      <div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden"></div> -->
      <b-row class="justify-content-md-center">
        <b-col md="2" class="mt-2">
          <h5 class="text-right"><b>검색 대상: </b></h5>
        </b-col>
        <b-col class="col-lg-8 text-left">
          <base-button type="warning" @click="searchBook" value="서점" style="width: 100px">서점</base-button>
          <base-button type="warning" @click="searchSchool" value="학교" style="width: 100px">학교</base-button>
          <b-form-select id="school_select" v-model="school" :options="schools" @change="selectschool" style="width: 100px; display: none"> </b-form-select>
        </b-col>
      </b-row>
      <div>
        <div class="mt-4" id="map" style="width: 100%; height: 600px; position: relative; overflow: hidden"></div>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButton from "@/ArgonComp/BaseButton.vue";
import { mapActions, mapState, mapMutations } from "vuex";
const BookStore = "BookStore";
const houseStore = "houseStore";
//const index = "index";
export default {
  components: { BaseButton },
  name: "KakaoBook",
  data() {
    return {
      school: "",
      schools: ["초등학교", "중학교", "고등학교"],
      searchsido: null,
      searchgugun: null,
      searchdong: null,
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      map: null,
      ps: null,
      infowindow: null,
      markers: [],
      keyword: null,
      schooldata: [],
      parschool: [],
    };
  },
  created() {
    console.log("user시도: " + this.user.sidoCode);
    this.CLEAR_SIDO_LIST();
    this.CLEAR_SIDO_NAME();
    this.CLEAR_GUGUN_NAME();
    this.CLEAR_DONG_NAME();
    this.getSido();
  },
  mounted() {
    this.initMap();
    // for (var i = 0; i < this.sidos.length; i++) {
    //   if (this.sidos[i].value == this.user.sidoCode) {
    //     var sidocode = this.sidos[i].text;
    //     break;
    //   }
    // }
    // console.log("user시도셋팅: " + sidocode);
    // this.searchplace(sidocode);
    // this.sidoCode = sidocode;
    // console.log("시도:" + this.sidoCode);
  },
  computed: {
    ...mapState(["user"]),
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "gugunnames", "dongnames", "sidonames"]),
    ...mapState(BookStore, ["booklists", "schoollists"]),
  },
  watch: {
    booklists(newv) {
      //var moveLatLon = new window.kakao.maps.LatLng(newv[0].lat, newv[0].lng);
      // 지도 중심을 부드럽게 이동시킵니다
      //this.map.panTo(moveLatLon);
      //this.map.setLevel(14);
      this.removeMarker();
      this.Allmarker(newv);
    },
    schoollists(newv) {
      this.schooldata = newv;
      //this.Schoolmarker(newv);
    },
    sidonames() {
      this.sidoCode = this.user.sidoCode;
    },
    sidos(newv) {
      for (var i = 0; i < newv.length; i++) {
        if (newv[i].value == this.user.sidoCode) {
          var sidocode = newv[i].text;
          break;
        }
      }
      //console.log("user시도셋팅: " + sidocode);
      this.searchplace(sidocode);
      this.sidoCode = sidocode;
      console.log("시도:" + this.sidoCode);
    },
  },
  methods: {
    ...mapActions(houseStore, ["getGugun", "getSido", "getDong"]),
    ...mapActions(BookStore, ["getBookList", "getSchoolList"]),
    ...mapMutations(houseStore, ["CLEAR_GUGUN_LIST", "CLEAR_SIDO_LIST", "CLEAR_DONG_LIST", "CLEAR_SIDO_NAME", "CLEAR_GUGUN_NAME", "CLEAR_DONG_NAME"]),
    selectschool() {
      this.removeMarker();
      this.parschool = [];
      for (var i = 0; i < this.schooldata.length; i++) {
        if (this.schooldata[i].level == this.school) {
          //console.log(this.schooldata[i].name);
          this.parschool.push(this.schooldata[i]);
        }
      }
      this.Schoolmarker(this.parschool);
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    gugunList() {
      this.searchsido = null;
      this.searchsido = this.sidoCode;
      this.searchplace(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_NAME();
      this.gugunCode = null;
      for (var i = 0; i < this.sidos.length; i++) {
        //console.log(this.sidos[i].text);
        if (this.sidos[i].text == this.sidoCode) {
          var sidocode = this.sidos[i].value;
          break;
        }
      }
      console.log(sidocode);
      if (sidocode) this.getGugun(sidocode);
    },
    dongList() {
      this.searchgugun = this.searchsido + " " + this.gugunCode;
      this.searchplace(this.searchgugun);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      for (var i = 0; i < this.guguns.length; i++) {
        //console.log(this.sidos[i].text);
        if (this.guguns[i].text == this.gugunCode) {
          var guguncode = this.guguns[i].value;
        }
      }
      console.log(guguncode);
      if (guguncode) this.getDong(guguncode);
    },
    List() {
      this.searchdong = this.searchgugun + " " + this.dongCode;
      this.searchplace(this.searchdong);
    },
    searchBook() {
      this.getBookList();
    },
    searchSchool() {
      document.getElementById("school_select").style.display = "inline";
      this.getSchoolList();
    },
    searchplace(keyword) {
      // 키워드로 장소를 검색합니다
      console.log("키워드: " + keyword);
      var geocoder = new window.kakao.maps.services.Geocoder();
      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(keyword, (result, status) => {
        // 정상적으로 검색이 완료됐으면
        if (status === window.kakao.maps.services.Status.OK) {
          console.log("검색성공");
          var coords = new window.kakao.maps.LatLng(result[0].y, result[0].x);

          // 지도 중심을 부드럽게 이동시킵니다
          this.map.panTo(coords);
          //this.map.setLevel(7);
        }
      });
    },
    Schoolmarker(newv) {
      //console.log(newv[0].name);
      //console.log("마커생성");
      // var imageSrc = "https://cdn.icon-icons.com/icons2/317/PNG/512/map-marker-icon_34392.png", // 마커이미지의 주소입니다
      //   imageSize = new window.kakao.maps.Size(34, 39), // 마커이미지의 크기입니다
      //   imageOption = { offset: new window.kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      //console.log("book: " + imageSrc);
      const positions = [];
      for (var i = 0; i < newv.length; i++) {
        positions.push({ title: newv[i].name, latlng: new window.kakao.maps.LatLng(newv[i].lat, newv[i].lng), level: newv[i].level, per: newv[i].personal, lat: newv[i].lat, lng: newv[i].lng });
      }

      for (i = 0; i < positions.length; i++) {
        //console.log("반복문실행");
        //var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
        // 마커를 생성합니다
        var marker = new window.kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          clickable: true,
          //image: markerImage,
        });
        marker.setMap(this.map);
        this.markers.push(marker);
        //console.log("포지션세팅" + positions[i].title);
        // 마커에 클릭이벤트를 등록합니다
        ((marker, position) => {
          window.kakao.maps.event.addListener(marker, "click", () => {
            if (this.infowindow != null) this.infowindow.close();
            var iwContent =
                '<div style="padding:5px;">' +
                "학교 명 : " +
                "<a href=https://map.kakao.com/link/roadview/" +
                position.lat +
                "," +
                position.lng +
                " target='_blank'>" +
                position.title +
                "</a></div><br/>" + // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                '<div style="padding:5px;">' +
                "운영 : " +
                position.per +
                "</div><br/>" +
                '<div style="padding:5px;">' +
                "구분 : " +
                position.level +
                "</div><br/>",
              iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
            console.log("인포윈도우 : " + iwContent);
            // 인포윈도우를 생성합니다
            var infowindow = new window.kakao.maps.InfoWindow({
              content: iwContent,
              removable: iwRemoveable,
            });
            this.infowindow = infowindow;
            // 마커 위에 인포윈도우를 표시합니다
            infowindow.open(this.map, marker);
          });
        })(marker, positions[i]);
      }
    },

    Allmarker(newv) {
      // var imageSrc = "https://cdn.icon-icons.com/icons2/317/PNG/512/map-marker-icon_34392.png", // 마커이미지의 주소입니다
      //   imageSize = new window.kakao.maps.Size(34, 39), // 마커이미지의 크기입니다
      //   imageOption = { offset: new window.kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      //console.log("book: " + imageSrc);
      const positions = [];
      for (var i = 0; i < newv.length; i++) {
        positions.push({ title: newv[i].storename, latlng: new window.kakao.maps.LatLng(newv[i].lat, newv[i].lng), address: newv[i].address, rest: newv[i].rest, lat: newv[i].lat, lng: newv[i].lng });
      }
      for (i = 0; i < positions.length; i++) {
        //var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
        // 마커를 생성합니다
        var marker = new window.kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          clickable: true,
          //image: markerImage,
        });
        marker.setMap(this.map);

        // 마커에 클릭이벤트를 등록합니다
        ((marker, position) => {
          window.kakao.maps.event.addListener(marker, "click", () => {
            if (this.infowindow != null) this.infowindow.close();
            if (position.rest.length == 0) position.rest = "없음";
            var iwContent =
                '<div style="padding:5px;">' +
                "서점 명 : " +
                "<a href=https://map.kakao.com/link/roadview/" +
                position.lat +
                "," +
                position.lng +
                " target='_blank'>" +
                position.title +
                "</a></div><br/>" + // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                '<div style="padding:5px;">' +
                "주소 : " +
                position.address +
                "</div><br/>" +
                '<div style="padding:5px;">' +
                "휴무일 : " +
                position.rest +
                "</div><br/>",
              iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
            console.log("인포윈도우 : " + iwContent);
            // 인포윈도우를 생성합니다
            var infowindow = new window.kakao.maps.InfoWindow({
              content: iwContent,
              removable: iwRemoveable,
            });
            this.infowindow = infowindow;
            // 마커 위에 인포윈도우를 표시합니다
            infowindow.open(this.map, marker);
          });
        })(marker, positions[i]);
      }
    },
    initMap() {
      //alert("init");
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new window.kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };
      var map = new window.kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new window.kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      map.addControl(mapTypeControl, window.kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new window.kakao.maps.ZoomControl();
      map.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT);

      this.map = map;

      // // 장소 검색 객체를 생성합니다
      // var ps = new window.kakao.maps.services.Places();
      // this.ps = ps;
      // // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
      // var infowindow = new window.kakao.maps.InfoWindow({ zIndex: 1 });
      // this.infowindow = infowindow;
    },
  },
};
</script>

<style scoped>
.btnCol {
  border: none;
  background-color: rgba(0, 0, 0, 0);
  box-shadow: none;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
</style>
