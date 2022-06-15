<template>
  <div>
    <div class="container">
      <b-row md="12">
        <b-col md="3"><b>건물 사진</b></b-col>
        <b-col md="3"><b>건물명</b></b-col>
        <b-col md="3"><b>상세 정보</b></b-col>
        <b-col md="3"><b>관심 해제</b></b-col>
      </b-row>
      <hr/>
      <div v-for="item in detail" :key="item">
        <div >
          <b-row md="12" style="display: flex; align-items: center">
            <b-col md="3">
              <b-img
                :src="getImg(item)"
                style="width: 100%; height: 100%;"
              />
            </b-col>
            <b-col md="3">
              <h5 class="title text-deepgreen">
                {{ item.apartmentName }}
              </h5>
            </b-col>
            <b-col md="3">
              <a> 거래 금액 : {{ item.dealAmount }} (만원) </a><br />
              <a>
                거래 날짜 : {{ item.dealYear }}.{{ item.dealMonth }}.{{
                  item.dealDay
                }} </a
              ><br />
              <a>층 수 : {{ item.floor }}</a
              ><br />
              <a>평 수 : {{ item.area }} m²</a>
            </b-col>
            <b-col md="3">
              <button class="btnC" @click="remove(item)">
                <icon
                  name="ni ni-favourite-28"
                  gradient="danger"
                  color="white"
                  size="sm"
                  shadow
                  rounded
                ></icon>
              </button>
            </b-col>
          </b-row>
        </div>
        <hr>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "MyInterest",

  data() {
    return {
      detail: [],
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
  },

  computed: {
    login() {
      return this.$store.state.token;
    },
    user() {
      return this.$store.state.user;
    },
  },

  created() {
    let url = "http://localhost:8080/interest/" + this.user.id;

    axios({
      url: url,
      method: "get",
    })
      .then((response) => {
        this.detail = [...this.detail, ...response.data];
      })
      .catch(() => {});
  },
  methods: {
    remove: function (item) {
      // detail 바탕으로 삭제 쿼리 날려야댐
      // 유저아이디 + 전체로 날려야될듯

      var data = {
        apartmentName: item.apartmentName,
        dealAmount: item.dealAmount,
        dealYear: item.dealYear,
        dealMonth: item.dealMonth,
        dealDay: item.dealDay,
        area: item.area,
        floor: item.floor,
        userId: this.$store.state.user.id,
      };

      axios({
        url: "http://localhost:8080/interest",
        method: "delete",
        data: data,
      })
        .then(() => {
          alert("관심지역이 삭제됐습니다");
          location.reload();
        })
        .catch(() => {
          console.log("관심지역 삭제에 오류가 발생했습니다");
        });
    },
    getImg(item) {
      console.log("하우스" + item.aptCode);
      // console.log(this.img[parseInt(this.house.aptCode) % 4]);
      return this.img[parseInt(item.aptCode) % this.img.length];
    },
  },
};
</script>

<style scoped>
.text-deepgreen {
  text: #2b463c;
}
.btnC {
  border: 0;
  outline: 0;
  background-color: rgba(0, 0, 0, 0);
}
.bordering {
  border-top-style: solid;
  border-bottom-style: solid;
  border: 0.5px solid lightgray;
}
</style>
