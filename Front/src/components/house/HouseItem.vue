<template>
  <div @click="selectHouse" @mouseover="colorChange(true)" @mouseout="colorChange(false)" :class="{ 'mouse-over-bgcolor': isColor }">
    <div class="p-0 bordering">
      <b-row md="12" style="height: 150px">
        <b-col md="6" style="width: 100%; height: 100%">
          <!--건물 사진-->
          <!-- <img src="../../assets/houseImg/random_aptImg_01.jpg" style="padding: 10px; width:100%; height:100%"> -->
          <!-- <img :src="getRandomImg" style="padding: 10px; width:100%; height:100%"> -->
          <!-- <img :src="require(`@/assets/houseImg/${img[randomKey()]}`)" style="padding: 10px; width:100%; height:100%" /> -->
          <b-img :src="getImg()" style="padding: 10px; width: 100%; height: 100%" />
        </b-col>
        <!--건물 설명-->
        <b-col md="6" style="padding: 10px; padding-top: 20px">
          <h5 class="text-left">
            <b>{{ house.apartmentName }}</b>
          </h5>
          <h6 class="text-left">{{ house.dong }} {{ house.jibun }}</h6>
          <h6 class="text-left">{{ house.buildYear }}년 건축</h6>
        </b-col>
      </b-row>
    </div>
  </div>
</template>
<script>
import { mapMutations, mapActions, mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseItem",
  data() {
    return {
      isColor: false,
      img: [
        "http://drive.google.com/uc?export=view&id=1IWDPeODFIKYsKFq3A4N4fCtKys30rK62",
        "http://drive.google.com/uc?export=view&id=1nEZ_0fykJt6uN_IsSu_TdVWuaFotTL17",
        "http://drive.google.com/uc?export=view&id=12RAu4PRMZfgaI40wpXMCxxTowhEU4OBO",
        "http://drive.google.com/uc?export=view&id=1MKq2pYHQUFZCdeALrJZdZIRS-FO8ZZL2",
        "http://drive.google.com/uc?export=view&id=1ru6gJjP3ZBYU-5ruXhfhzvE4mSkq159m",
        "http://drive.google.com/uc?export=view&id=1SqXfcKpBqXFHP3p_yT3fHmJ27B-XoyN-",
        "http://drive.google.com/uc?export=view&id=1nEZ_0fykJt6uN_IsSu_TdVWuaFotTL17",
        "http://drive.google.com/uc?export=view&id=1JLKKrmsw1NNM_GcoStDZTC8ziCl8H-F6",
        "http://drive.google.com/uc?export=view&id=1BVX3bAIxZ455FVb3cQ5Z1B6u3COuflyT",
        "http://drive.google.com/uc?export=view&id=1n2W1rwLgx_37Tpk60jiARPBTk3Sc5Uw1",
        "http://drive.google.com/uc?export=view&id=1n_PuIBPqkYjlx4wU9U66QhFqfgApPlX6",
        "http://drive.google.com/uc?export=view&id=1ZrY4dAl7yOm2OIbXGfECPQm_GGL1lv27",
        "http://drive.google.com/uc?export=view&id=1KzFpgJtyrgopDww8YObJf87lffbi7epU",
        "http://drive.google.com/uc?export=view&id=1NDc6_cp3JSgsRLFo7_KTX-dLc_THGQGb",
        "http://drive.google.com/uc?export=view&id=1sCr8c9c6OjeqdMdVGFbDW3BlY-mSz9lp",
        "http://drive.google.com/uc?export=view&id=1500PTVa-PpMymqnQtr6ZFrp_wtc2X6CP",
      ],
    };
  },
  props: {
    house: Object,
    len: Number,
  },
  computed: {
    ...mapState(houseStore, ["houseDetails"]),
  },
  watch: {
    houseDetails(newv) {
      console.log("디테일변화: " + newv);
      //this.$router.push("/housedetail");
    },
  },
  methods: {
    ...mapMutations(houseStore, ["CREAL_HOUSE_DETAILS"]),
    ...mapActions(houseStore, ["getHouseDetail"]),
    selectHouse() {
      //this.CREAL_HOUSE_DETAILS();
      this.getHouseDetail(this.house);
      //this.$router.push("{ name: 'houseDetail' }");
      this.$router.push("/housedetail");
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    getImg() {
      console.log(parseInt(this.house.aptCode) % this.img.length);
      return this.img[parseInt(this.house.aptCode) % this.img.length];
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
  cursor: pointer;
}

.bordering {
  border-top-style: solid;
  border-bottom-style: solid;
  border: 0.5px solid lightgray;
}
</style>
