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
        <h3><b>주변 상가 검색</b></h3>
      </b-col>
    </b-row>
    <test-bar></test-bar>
  </div>
</template>

<script>
import TestBar from "@/components/house/TestBar.vue";
import Sidebar from "@/components/sidebar/sidebar.vue";
export default {
  components: {
    TestBar,
    Sidebar,
  },
};
</script>

<style>
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
