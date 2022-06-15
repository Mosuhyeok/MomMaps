<template>
  <div>
    <b-row style="width:100%; height:70px;float:none;margin:auto; background-color: #b1d182;">
      <b-col md="4">
        <b-button v-b-toggle.sidebar-backdrop class="btnCol" style="position: absolute; width: 60px; height:70px; left: 20px">
          <i class="ni ni-align-left-2"></i>
        </b-button>
        <sidebar></sidebar>
      </b-col>
      <b-col md="4" class="mt-3">
        <h3><b>주택 검색</b></h3>
      </b-col>
      <b-col md="4" class="mt-3">
        <house-search-bar class="btnCol" />
      </b-col>
    </b-row>
    
    <div>
      <b-row class="p-3">
        <b-col md="9">
          <kakao-map />
        </b-col>
        <b-col md="3">
          <div style="overflow-x: hidden; overflow-y: scroll; height:700px">
            <house-list />
          </div>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import KakaoMap from "@/components/map/KakaoMap.vue";
import HouseList from "@/components/house/HouseList.vue";
import Sidebar from "@/components/sidebar/sidebar.vue";
export default {
  components: { HouseSearchBar, KakaoMap, HouseList, Sidebar },
};
</script>

<style scoped>
/* div{
  margin:0;
} */
#wrapper {
  display: table;
}
#content {
  display: table-cell;
  vertical-align: middle;
}
.body {
  height: 100vh;
  background-size: cover;
}
.btnCol {
  border: none;
  background-color: rgba(0, 0, 0, 0);
  box-shadow: none;
}
#sp {
  display: block;
}

</style>
