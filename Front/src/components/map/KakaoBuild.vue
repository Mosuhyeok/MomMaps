<template>
  <div class="map_wrap">
    <div id="map" style="width: 100%; height: 700px; position: relative; overflow: hidden"></div>
    <ul id="category">
      <li id="BK9" data-order="0" @click="onClickCategory(`BK9`, ``)">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1" @click="onClickCategory(`MT1`, ``)">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2" @click="onClickCategory(`PM9`, ``)">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3" @click="onClickCategory(`OL7`, ``)">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4" @click="onClickCategory(`CE7`, ``)">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li id="CS2" data-order="5" @click="onClickCategory(`CS2`, ``)">
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";
export default {
  name: "KakaoBuild",
  data() {
    return {
      id: "",
      map: null,
      keyword: "",
      infowindow: null,
      currCategory: "",
      ps: null,
      markers: [],
      contentNode: null,
      placeOverlay: null,
      x: 0.0,
      y: 0.0,
    };
  },
  computed: {
    ...mapState(houseStore, ["houseDetails", "chart"]),
  },
  mounted() {
    this.initMap();
    //this.getResult();
  },
  watch: {
    houseDetails(newv) {
      this.y = newv[0].lat;
      this.x = newv[0].lng;
      this.initMap();
      console.log("빌더데이터변화 y :" + this.y);
      console.log("빌더데이터변화 x :" + this.x);
      this.getResult();
      // 마커가 표시될 위치입니다
      var markerPosition = new window.kakao.maps.LatLng(newv[0].lat, newv[0].lng);

      // 마커를 생성합니다
      var marker = new window.kakao.maps.Marker({
        position: markerPosition,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);
    },
  },
  methods: {
    ...mapActions(houseStore, ["getNearby"]),
    async getResult() {
      let query = [
        { place: "학원", code: "AC5" },
        { place: "병원", code: "HP8" },
        { place: "학교", code: "SC4" },
        { place: "마트", code: "MT1" },
        { place: "편의점", code: "CS2" },
      ];
      //let code = ["AC5", "HP8", "SC4", "MT1", "CS2"];
      const ret = [];
      console.log("y:" + this.y);
      console.log("x:" + this.x);
      const result = await Promise.all(
        query.map((keyword) => {
          const url = `https://dapi.kakao.com/v2/local/search/keyword.json?query=${keyword.place}&y=${this.y}&x=${this.x}&radius=500&category_group_code=${keyword.code}`;
          return axios({
            method: "get",
            url,
            headers: {
              Authorization: "KakaoAK 3d9656397d4920506f4a169a6b03d2c3",
            },
          });
        })
      );

      result.forEach((response, index) => ret.push({ place: query[index], cnt: response.data.meta.total_count }));
      console.log("겟니어진입 : " + ret);
      this.getNearby(ret);
    },

    initMap() {
      //alert("init");
      var placeOverlay = new window.kakao.maps.CustomOverlay({ zIndex: 1 }),
        contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers = [], // 마커를 담을 배열입니다
        currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다
      //console.log(markers);
      //console.log(currCategory);
      this.markers = markers;
      this.currCategory = currCategory;
      this.placeOverlay = placeOverlay;
      this.contentNode = contentNode;

      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new window.kakao.maps.LatLng(this.houseDetails[0].lat, this.houseDetails[0].lng), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };
      var map = new window.kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

      // 마커가 표시될 위치입니다
      var markerPosition = new window.kakao.maps.LatLng(this.houseDetails[0].lat, this.houseDetails[0].lng);

      // 마커를 생성합니다
      var marker = new window.kakao.maps.Marker({
        position: markerPosition,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);

      // 장소 검색 객체를 생성합니다
      var ps = new window.kakao.maps.services.Places(map);
      this.ps = ps;
      // 지도에 idle 이벤트를 등록합니다

      this.map = map;
      window.kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
      //this.map = map;
      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(this.contentNode, "mousedown", window.kakao.maps.event.preventMap);
      this.addEventHandle(this.contentNode, "touchstart", window.kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      //this.addCategoryClickEvent();
    },
    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },

    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      var currCategory = this.currCategory;
      if (!currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      var ps = this.ps;
      ps.categorySearch(currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
      this.ps = ps;
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      console.log(pagination);
      if (status === window.kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === window.kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === window.kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document.getElementById(this.currCategory).getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker(new window.kakao.maps.LatLng(places[i].y, places[i].x), order);

        //마커와 검색결과 항목을 클릭 했을 때
        //장소정보를 표출하도록 클릭 이벤트를 등록합니다
        ((marker, place) => {
          window.kakao.maps.event.addListener(marker, "click", () => {
            this.displayPlaceInfo(place);
            this.test(place);
          });
        })(marker, places[i]);
      }
    },
    test(place) {
      console.log(place);
    },
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, order) {
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new window.kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new window.kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new window.kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new window.kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new window.kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo(place) {
      var content = '<div class="placeinfo">' + '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + "</span>";
      }

      content += '    <span class="tel">' + place.phone + "</span>" + "</div>" + '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new window.kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },
    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent() {
      var category = document.getElementById("category"),
        children = category.children;

      for (var i = 0; i < children.length; i++) {
        children[i].onclick = this.onClickCategory;
      }
    },
    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(cid, cname) {
      //var id = event.target.id,
      var id = cid,
        className = cname;
      //id = "BK9";
      this.placeOverlay.setMap(null);
      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        this.currCategory = id;
        this.changeCategoryClass(this);
        this.searchPlaces();
      }
    },
    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      var category = document.getElementById("category"),
        children = category.children,
        i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }

      if (el) {
        el.className = "on";
      }
    },
  },
};
</script>

<style>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png) no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
