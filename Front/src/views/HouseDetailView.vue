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
        <h3><b>주택 상세 정보</b></h3>
      </b-col>
    </b-row>

    <div>
      <b-row class="p-3">
        <b-col md="9">
          <kakao-build></kakao-build>
        </b-col>
        <b-col md="3">
          <div>
            <h4 class="mt-3"><b>거래 내역</b></h4>
            <hr />
          </div>
          <!-- <h4>테이블</h4> -->

          <div>
            <b-button v-b-modal.modal-lg variant="primary">주택 주변 분석 차트</b-button>
            <b-modal id="modal-lg" size="lg">
              <h2 class="text-center mt-2"><b>아파트 가격</b></h2>
              <hr />
              <b-row>
                <b-col md="6">
                  <h5 class="text-center"><b>주변 건물</b></h5>
                  <result-chart />
                </b-col>
                <b-col md="6">
                  <h5 class="text-center"><b>거래 금액 추이</b></h5>
                  <price-chart />
                </b-col>
              </b-row>
            </b-modal>
          </div>
          <div>
            <div style="overflow-x: hidden; overflow-y: ; height: 500px">
              <house-detail v-for="(detail, i) in houseDetails" :key="i" :detail="detail" :arr="arr"></house-detail>
            </div>
          </div>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import HouseDetail from "@/components/house/HouseDetail.vue";
import { mapMutations, mapState } from "vuex";
import axios from "axios";
import KakaoBuild from "@/components/map/KakaoBuild.vue";
import PriceChart from "@/components/house/PriceChart.vue";
import Sidebar from "@/components/sidebar/sidebar.vue";
import ResultChart from "@/components/house/ResultChart.vue";

const houseStore = "houseStore";
export default {
  components: {
    HouseDetail,
    KakaoBuild,
    PriceChart,
    ResultChart,
    Sidebar,
    // Modal,
    // Action
  },
  computed: {
    ...mapState(houseStore, ["houseDetails"]),
    login() {
      return this.$store.state.token;
    },
    user() {
      return this.$store.state.user;
    },
  },
  data() {
    return {
      arr: [],
      resultKey: 0,
      priceKey: 0,
      modals: {
        modal1: false,
      },
    };
  },
  watch: {
    HouseDetails() {
      console.log("빌더데이터변화");
      //console.log("new: " + newv[0]);
      this.resultKey += 1;
      this.priceKey += 1;
      this.CREAT_SET_CHART();
    },
    // chart() {
    //   //this.resultKey += 1;
    //   this.priceKey += 1;
    // },
  },
  created() {
    console.log("처음들어올때 디테일정보 :" + this.houseDetails[0]);

    this.userId = this.$store.state.user.id;
    var url = "http://localhost:8080/interest/" + this.userId;

    axios({
      url: url,
      method: "get",
    })
      .then((response) => {
        this.arr = [];
        this.arr = [...this.arr, ...response.data];
      })
      .catch((error) => {
        console.log(error);
      });

    this.resultKey += 1;
    this.priceKey += 1;
  },
  methods: {
    ...mapMutations(houseStore, ["CREAT_SET_CHART"]),
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
