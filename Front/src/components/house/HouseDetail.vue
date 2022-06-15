<template>
  <div>
    <card shadow class="shadow-lg--hover mt-2">
      <b-row>
        <b-col md="8">
          <div class="d-flex px-3">
            <div class="pl-4 text-left">
              <h5 class="title text-deepgreen">{{ detail.aptName }}</h5>
              <p style="line-height: 50%">
                거래 금액 : {{ detail.dealAmount }} (만원)
              </p>
              <p style="line-height: 50%">
                거래 날짜 : {{ detail.dealYear }}.{{ detail.dealMonth }}.{{
                  detail.dealDay
                }}
              </p>
              <p style="line-height: 50%">층 수 : {{ detail.floor }}</p>
              <p style="line-height: 50%">평 수 : {{ detail.area }} m²</p>
            </div>
          </div>
        </b-col>
        <b-col md="4">
          <div class="pl-5">
            <button
              class="btnC"
              v-if="login && duplicated"
              @click="
                regist(
                  detail.aptCode,
                  detail.aptName,
                  detail.dealAmount,
                  detail.dealYear,
                  detail.dealMonth,
                  detail.dealDay,
                  detail.area,
                  detail.floor
                )
              "
            >
              <icon
                name="ni ni-favourite-28"
                gradient="light"
                color="white"
                size="sm"
                shadow
                rounded
              ></icon
              ><br />
            </button>
            <button class="btnC" v-if="login && !duplicated" @click="remove()">
              <icon
                name="ni ni-favourite-28"
                gradient="danger"
                color="white"
                size="sm"
                shadow
                rounded
              ></icon>
            </button>
          </div>
        </b-col>
      </b-row>
    </card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  name: "HouseDetail",
  data() {
    return {
      userId: "",
    };
  },
  props: {
    detail: Object,
    arr: Array,
  },
  computed: {
    login() {
      return this.$store.state.token;
    },
    user() {
      return this.$store.state.user;
    },
    // 이미 찜 한 목록
    duplicated() {
      for (var i = 0; i < this.arr.length; i++) {
        if (
          this.arr[i].apartmentName == this.detail.aptName &&
          this.arr[i].area == this.detail.area &&
          this.arr[i].floor == this.detail.floor &&
          this.arr[i].dealAmount == this.detail.dealAmount &&
          this.arr[i].dealDay == this.detail.dealDay
        ) {
          return false;
        }
      }
      return true;
    },
  },
  created() {},
  methods: {
    remove: function () {
      // detail 바탕으로 삭제 쿼리 날려야댐
      // 유저아이디 + 전체로 날려야될듯

      var data = {
        apartmentName: this.detail.aptName,
        dealAmount: this.detail.dealAmount,
        dealYear: this.detail.dealYear,
        dealMonth: this.detail.dealMonth,
        dealDay: this.detail.dealDay,
        area: this.detail.area,
        floor: this.detail.floor,
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
        .catch((error) => {
          console.log(error);
        });
    },
    regist: function (
      aptCode,
      apartmentName,
      dealAmount,
      dealYear,
      dealMonth,
      dealDay,
      area,
      floor
    ) {
      let data = {
        aptCode: aptCode,
        apartmentName: apartmentName,
        dealAmount: dealAmount,
        dealYear: dealYear,
        dealMonth: dealMonth,
        dealDay: dealDay,
        area: area,
        floor: floor,
        userId: this.user.id,
      };

      axios({
        url: "http://localhost:8080/interest",
        method: "post",
        data: data,
      })
        .then(() => {
          alert("관심지역이 등록됐습니다");
          location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
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
</style>
