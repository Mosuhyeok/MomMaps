<template>
  <b-row class="mb-4" style="width: 100%">
    <b-col class="sm-3">
      <b-form-select v-model="sidoName" :options="sidonames" @change="gugunList"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="gugunName" :options="gugunnames" @change="dongList"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="dongName" :options="dongnames" @change="searchList"></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";
export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
      sidoCode: null,
      guguCode: null,
      dongCode: null,
      pageNum: 0,
      chk: true,
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_HOUSES();
    //this.CLEAR_HOUSEAVGS();
    this.getSido();
    console.log("시도수정1" + this.sidos[0].value);
  },
  mounted() {},
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houseAvgs", "sidonames", "gugunnames", "dongnames"]),
    ...mapState(["user"]),
  },
  watch: {
    sidos() {
      console.log("시도수정" + this.sidos[0].value);
      console.log("");
      if (this.chk) {
        console.log("시도수정" + this.sidos[0].text);
        for (var i = 0; i < this.sidos.length; i++) {
          if (this.sidos[i].value == this.user.sidoCode) {
            var sidoname = this.sidos[i].text;
            break;
          }
        }
        this.sidoName = sidoname;
        console.log("시도수정" + this.sidoName);
        this.getGugun(this.user.sidoCode);
      }
    },
    guguns() {
      console.log("구군수정" + this.guguns[0].value);
      if (this.chk) {
        console.log("구군수정" + this.guguns[0].text);
        for (var i = 0; i < this.guguns.length; i++) {
          if (this.guguns[i].value == this.user.gugunCode) {
            var gugunname = this.guguns[i].text;
            break;
          }
        }
        this.gugunName = gugunname;
        console.log("구군수정" + this.gugunName);
        console.log(this.user.gugunCode);
        this.getDong(this.user.gugunCode);
      }
    },
    dongs() {
      console.log("동수정" + this.dongs[0].value);
      if (this.chk) {
        console.log("동수정" + this.dongs[0].text);
        for (var i = 0; i < this.dongs.length; i++) {
          if (this.dongs[i].value == this.user.dongCode) {
            var dongname = this.dongs[i].text;
            this.dongCode = this.dongs[i].value;
            break;
          }
        }
        this.dongName = dongname;
        console.log("동수정" + this.dongName);
        this.searchHouse();
        this.chk = false;
      }
    },
    houseAvgs() {
      const search = {
        dongCode: this.dongCode,
        page: this.pageNum,
      };
      this.searchHouseList(search);
    },
  },
  methods: {
    ...mapActions(houseStore, ["getGugun", "getSido", "getDong", "searchHouseList", "getHouseAvg"]),
    ...mapMutations(houseStore, ["CLEAR_GUGUN_LIST", "CLEAR_SIDO_LIST", "CLEAR_DONG_LIST", "CLEAR_HOUSES", "CLEAR_HOUSEAVGS"]),
    gugunList() {
      console.log("현재시도" + this.sidoName);
      this.CLEAR_GUGUN_LIST();
      for (var i = 0; i < this.sidos.length; i++) {
        if (this.sidoName == this.sidos[i].text) {
          this.sidoCode = this.sidos[i].value;
          this.sidoName = this.sidos[i].text;
          break;
        }
      }
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log("현재구군" + this.gugunName);
      this.CLEAR_DONG_LIST();
      for (var i = 0; i < this.guguns.length; i++) {
        if (this.gugunName == this.guguns[i].text) {
          this.gugunCode = this.guguns[i].value;
          this.gugunName = this.guguns[i].text;
          break;
        }
      }
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchList() {
      console.log("현재동" + this.dongName);
      for (var i = 0; i < this.dongs.length; i++) {
        if (this.dongName == this.dongs[i].text) {
          this.dongCode = this.dongs[i].value;
          this.dongName = this.dongs[i].text;
          break;
        }
      }
      this.searchHouse();
    },
    searchHouse() {
      if (this.dongCode) {
        this.getHouseAvg(this.dongCode);
        //setTimeout(() => console.log("timeset"), 3000);
        //this.searchHouseList(search);
      }
    },
  },
};
</script>

<style></style>
