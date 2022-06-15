<template>
  <div>
    <div id="KakaoMap" >
        <div>
          <div id="map" style="width: 100%; height:700px;"></div>
        </div>
    </div>
  </div>
</template>

<script>
const houseStore = "houseStore";
import { mapState } from "vuex";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      keyword: "",
      infowindow: null,
      marker: null,
    };
  },
  mounted() {
    this.initMap();
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  watch: {
    houses(newvalue) {
      if (newvalue.length > 0) {
        //console.log(newvalue.lat);
        var map = this.map;
        var positions = [];
        for (var i = 0; i < newvalue.length; i++) {
          positions[i] = {
            title: newvalue[i].aptName,
            latlng: new window.kakao.maps.LatLng(newvalue[i].lat, newvalue[i].lng),
          };
          //console.log(positions[i]);
          var marker = new window.kakao.maps.Marker({
            map: map,
            position: positions[i].latlng,
            title: positions[i].title,
          });
          this.marker = marker;
        }
        var moveLatLng = new window.kakao.maps.LatLng(newvalue[0].lat, newvalue[0].lng);
        map.setCenter(moveLatLng);
        this.map = map;
      }
    },
  },
  methods: {
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
    },
    search() {
      //alert(this.keyword);
      // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
      var infowindow = new window.kakao.maps.InfoWindow({ zIndex: 1 });
      this.infowindow = infowindow;
      // 장소 검색 객체를 생성합니다
      var ps = new window.kakao.maps.services.Places();
      ps.keywordSearch(this.keyword, this.placesSearchCB);
    },
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      if (status === window.kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new window.kakao.maps.LatLngBounds();
        console.log(bounds);
        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
          bounds.extend(new window.kakao.maps.LatLng(data[i].y, data[i].x));
        }
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        this.map.setBounds(bounds);
      }
    },
    // 지도에 마커를 표시하는 함수입니다
    displayMarker(place) {
      var map = this.map;
      var infowindow = this.infowindow;
      // 마커를 생성하고 지도에 표시합니다
      var marker = new window.kakao.maps.Marker({
        map: map,
        position: new window.kakao.maps.LatLng(place.y, place.x),
      });
      //console.log(place.place_name);
      // 마커에 클릭이벤트를 등록합니다
      window.kakao.maps.event.addListener(marker, "click", function () {
        console.log(place.place_name);
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + "</div>");
        infowindow.open(map, marker);
      });
    },
  },
};
</script>

<style>
</style>
