<template>
  <div>
    <div>
      <h4 class="mt-3"><b>주택 리스트</b></h4>
      <hr />
    </div>
    <div v-if="houses && houses.length != 0">
      <base-button block type="primary" class="mb-3" @click="modals.modal1 = true"> 한 눈에 보기 </base-button>
      <modal :show.sync="modals.modal1">
        <h1>아파트 가격</h1>
        <chart-test :key="chartKey" />
      </modal>
    </div>

    <div v-if="houses && houses.length != 0" class="bv-example-row">
      <div>
        <house-item v-for="(house, i) in houses" :key="i" :house="house" :len="houses.length" />
      </div>

      <br />
      <b-pagination v-model="currentPage" :total-rows="houseCnt" :per-page="perPage" align="center" @input="clickpage" :total-visible="total" />

      <!-- <chart-test :key="chartKey" /> -->
    </div>
    <div v-else class="bv-example-row">
      <b-row>
        <b-col>
          <b-alert show dismissible variant="warning">
            <span class="alert-icon"><i class="ni ni-like-2"></i></span>
            <span class="alert-text"><strong> 주택 목록이 없습니다.</strong><br />다른 지역으로 검색해보세요!</span>
          </b-alert>
        </b-col>
      </b-row>
    </div>
    <!-- </div> -->
  </div>
</template>

<script>
import HouseItem from "@/components/house/HouseItem.vue";

import { mapActions, mapState, mapMutations } from "vuex";
import Modal from "@/ArgonComp/Modal.vue";
import ChartTest from "@/components/house/ChartTest.vue";
const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseItem,
    // ChartTest,
    Modal,
    ChartTest,
  },
  data() {
    return {
      total: 5,
      currentPage: 1,
      perPage: 5,
      chartKey: 0,
      modals: {
        modal1: false,
      },
    };
  },
  created() {
    this.CLEAR_HOUSEAVGS();
    this.chartKey += 1;
  },
  watch: {
    houses() {
      //setTimeout(() => console.log("timeset2"), 100000);
      this.chartKey += 1;
    },
  },
  computed: {
    ...mapState(houseStore, ["houses", "houseCnt", "dongCode"]),
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSEAVGS"]),
    ...mapActions(houseStore, ["searchHouseList"]),
    clickpage() {
      const search = {
        dongCode: this.dongCode,
        page: (this.currentPage - 1) * this.perPage,
      };
      if (search) this.searchHouseList(search);
    },
  },
};
</script>

<style scoped>
.box {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.box::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>
