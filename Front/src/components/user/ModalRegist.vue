<template>
  <section class="section-profile-cover section-lg my-0" style="background-color:rgb(186,208,134)">
    <div class="pt-lg-md mb-5">
      <div class="row justify-content-center">
        <div class="col-lg-5">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <h2 class="mb-4">Register</h2>

              <b-form-group>
                <b-form-input
                  alternative
                  id="id"
                  type="text"
                  v-model="id"
                  :state="idState"
                  aria-describedby="input-live-help input-live-feedback"
                  placeholder="ID"
                  trim
                ></b-form-input>

                <!-- This will only be shown if the preceding input has an invalid state -->
                <b-form-invalid-feedback id="input-live-feedback">
                  아이디는 5글자 이상 입력해주세요.
                </b-form-invalid-feedback>

                <b-form-input
                  alternative
                  type="password"
                  id="password"
                  v-model="password"
                  placeholder="Password"
                  :state="passwordState"
                  aria-describedby="input-live-help input-live-feedbackpw"
                  trim
                ></b-form-input>

                <!-- This will only be shown if the preceding input has an invalid state -->
                <b-form-invalid-feedback id="input-live-feedbackpw">
                  패스워드는 8글자 이상 입력해주세요.
                </b-form-invalid-feedback>

                <b-form-input
                  alternative
                  type="text"
                  id="name"
                  v-model="name"
                  placeholder="Name"
                  :state="nameState"
                  aria-describedby="input-live-help input-live-feedbackname"
                  trim
                ></b-form-input>

                <!-- This will only be shown if the preceding input has an invalid state -->
                <b-form-invalid-feedback id="input-live-feedbackname">
                  이름은 2글자 이상 입력해주세요.
                </b-form-invalid-feedback>

                <b-form-input
                  alternative
                  type="text"
                  id="phone"
                  v-model="phone"
                  placeholder="Phone"
                  :state="phoneState"
                  trim
                ></b-form-input>

                <b-form-input
                  alternative
                  type="email"
                  id="email"
                  v-model="email"
                  placeholder="Email"
                  :state="emailState"
                  aria-describedby="input-live-help input-live-feedbackEmail"
                  trim
                  class="mt-4"
                ></b-form-input>
                <b-form-invalid-feedback id="input-live-feedbackEmail">
                  이메일 형식으로 입력해주세요
                </b-form-invalid-feedback>
                <base-button
                  class="btn col-md-5"
                  type="default"
                  @click="emailSend"
                  >인증번호 발급</base-button
                >
                <base-input
                  alternative
                  type="text"
                  id="value"
                  v-model="value"
                  class="mb-3 mt-3"
                  placeholder="인증번호"
                  addon-left-icon="ni ni-hat-3"
                >
                </base-input>
                <hr />
                <div>
                  <p><b>관심 지역을 선택해주세요.</b></p>
                  <b-row
                    class="mt-4 mb-4 text-center"
                    style="width: 500px; margin: auto"
                  >
                    <b-col class="sm-3">
                      <b-form-select
                        v-model="sidoCode"
                        :options="sidos"
                        @change="gugunList"
                      ></b-form-select>
                    </b-col>
                    <b-col class="sm-3">
                      <b-form-select
                        v-model="gugunCode"
                        :options="guguns"
                        @change="dongList"
                      ></b-form-select>
                    </b-col>
                    <b-col class="sm-3">
                      <b-form-select
                        v-model="dongCode"
                        :options="dongs"
                        @change="searchHouse"
                      ></b-form-select>
                    </b-col>
                  </b-row>
                </div>
                <div class="mb-5"></div>
                <hr />
                <div class="text-center">
                  <base-button
                    class="btn col-md-4"
                    type="default"
                    @click="regist"
                    >등록</base-button
                  >
                  <base-button
                    class="btn col-md-4"
                    type="secondary"
                    @click="reject"
                    >취소</base-button
                  >
                </div>
              </b-form-group>

              <!-- This will only be shown if the preceding input has an invalid state -->
            </template>
          </card>
          <br><br><br><br><br>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
// import { mapState } from "vuex";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
export default {
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
    idState() {
      return this.id.length >= 5 ? true : false;
    },
    passwordState() {
      return this.password.length >= 8 ? true : false;
    },
    nameState() {
      return this.name.length >= 2 ? true : false;
    },
    phoneState() {
      var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
      return regPhone.test(this.phone);
    },
    emailState() {
      return this.email_check(this.email);
    },
  },
  name: "UserList",
  data() {
    return {
      id: "",
      password: "",
      name: "",
      email: "",
      phone: "",
      value: "",
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  methods: {
    ...mapActions(houseStore, [
      "getGugun",
      "getSido",
      "getDong",
      "searchHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_GUGUN_LIST",
      "CLEAR_SIDO_LIST",
      "CLEAR_DONG_LIST",
    ]),

    email_check(email) {
      var regExp =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      return regExp.test(email);
    },

    reject() {
      window.location.href = "/";
    },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchHouse() {
      const search = {
        dongCode: this.dongCode,
        page: this.pageNum,
      };
      if (this.dongCode) this.searchHouseList(search);
    },
    emailSend() {
      const API_URL = "http://localhost:8080/mail/" + this.email;
      axios({
        url: API_URL,
        method: "post",
      })
        .then(() => {
          alert("이메일이 전송됐습니다");
        })
        .catch((err) => {
          console.log(API_URL);
          console.log(err);
        });
    },
    // 이메일 등록
    regist() {
      if (
        this.id === "" ||
        this.password === "" ||
        this.name === "" ||
        this.email === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let verify = {
        email: this.email,
        value: this.value,
      };

      const API_URL = "http://localhost:8080/mail/auth";
      axios({
        url: API_URL,
        method: "post",
        data: verify,
      })
        .then(() => {
          this.regist2();
        })
        .catch((err) => {
          alert("인증 번호가 일치하지 않습니다");
          console.log(err);
        });
    },

    // 회원가입
    regist2() {
      const user = {
        id: this.id,
        password: this.password,
        name: this.name,
        email: this.email,
        phone: this.phone,
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        dongCode: this.dongCode,
      };

      const API_URL = "http://localhost:8080/user";
      axios({
        url: API_URL,
        method: "post",
        data: user,
      })
        .then(() => {
          // mutation
          alert("등록되었습니다.");
          this.$router.push("/login");
        })
        .catch((err) => {
          console.log(API_URL);
          console.log(err);
        });
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
};
</script>
