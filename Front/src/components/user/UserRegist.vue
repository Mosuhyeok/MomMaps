<template>
  <div class="container">
    <br /><br /><br /><br />
    <h2>회원 가입</h2>
    <fieldset class="text-center">
      <label for="id">아이디</label>

      <input
        type="text"
        id="id"
        v-model="id"
        class="view"
        :state="nameState"
      /><br />
      <label for="password">비밀번호</label>
      <input
        type="password"
        id="password"
        v-model="password"
        class="view"
      /><br />
      <label for="name">이름</label>
      <input type="text" id="name" v-model="name" class="view" /><br />
      <label for="phone">연락처</label>
      <label for="email">이메일</label>
      <input type="email" id="email" v-model="email" class="view" /><br />
      <input type="text" id="phone" v-model="phone" class="view" /><br />
      <b-button class="btn" variant="outline-success" @click="emailSend"
        >인증번호 발급</b-button
      >
      <label for="value">인증번호 </label>
      <input type="text" id="value" v-model="value" class="view" /><br />

      <b-button class="btn" variant="outline-success" @click="regist"
        >등록</b-button
      >
    </fieldset>
  </div>
</template>
<script>
import axios from "axios";
// import { mapState } from "vuex";
export default {
  name: "UserList",
  data() {
    return {
      id: "",
      password: "",
      name: "",
      email: "",
      phone: "",
      value: "",
    };
  },
  computed: {
    nameState() {
      return this.id.length >= 5 ? true : false;
    },
  },
  methods: {
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
    regist2() {
      let user = {
        id: this.id,
        password: this.password,
        name: this.name,
        email: this.email,
        phone: this.phone,
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
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(API_URL);
          console.log(err);
        });
    },
  },
};
</script>
