import Vue from "vue";
import Vuex from "vuex";
import router from "@/router";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

import houseStore from "./modules/houseStore";
import boardStore from "./modules/boardStore";
import BookStore from "./modules/BookStore";

export default new Vuex.Store({
  state: {
    token: null,
    users: [],
    user: {
      id: String,
      password: String,
      name: String,
      email: String,
      phone: String,
      sidoCode: String,
      gugunCode: String,
      dongCode: String,
      token: String,
    },
  },
  getters: {},
  mutations: {
    CREATE_USER: function (state, user) {
      state.users.push(user);
    },
    SET_USER: function (state, user) {
      state.user = user;
    },
    SET_LOGIN_USER: function (state, user) {
      state.loginUser = user;
    },
    LOGOUT: function (state) {
      state.token = null;
      state.user = null;
      alert("로그아웃이 완료 됐습니다.");
    },
    LOGIN: function (state, item) {
      console.log(item);
      console.log(item.item.data);
      state.token = item.item.data.token;
      state.user = item.item.data;
      console.log(state.user);
    },
  },
  actions: {
    logoutUser: function () {
      this.commit("LOGOUT");
    },
    loginUser: function (context, user) {
      const API_URL = `http://localhost:8080/login`;

      axios({
        url: API_URL,
        method: "post",
        data: user,
      })
        .then((item) => {
          this.commit("LOGIN", { item });
          alert("로그인이 완료되었습니다");
        })
        .catch(() => {
          alert("올바르지 않은 비밀번호 입니다");
        });
    },

    createUser: function ({ commit }, user) {
      const API_URL = "http://localhost:8080/user";
      axios({
        url: API_URL,
        method: "post",
        data: user,
      })
        .then(() => {
          // mutation
          commit("CREATE_USER", user);
          alert("등록되었습니다.");
          router.push("/");
        })
        .catch((err) => {
          console.log(API_URL);
          console.log(err);
        });
    },
    // 사용자 수정
    updateUser: function ({ state }, user) {
      const API_URL = `http://localhost:8080/user/${user.id}`;
      axios({
        url: API_URL,
        method: "put",
        data: user,
      })
        .then(() => {
          alert("수정 완료!");
          let index;
          for (let i = 0; i < state.users.length; i++) {
            if ((state.users[i].id = user.id)) {
              index = i;
            }
          }
          //this.$set(state.users, index, user);
          state.users[index] = user;
          router.push("/user");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 사용자 삭제
    deleteUser: function ({ state }, id) {
      const API_URL = `http://localhost:8080/user/${id}`;
      axios({
        url: API_URL,
        method: "delete",
      })
        .then(() => {
          alert("삭제 완료!");
          let index;
          for (let i = 0; i < state.users.length; i++) {
            if (state.users[i].id === id) {
              index = i;
            }
          }
          state.users.splice(index, 1);
          router.push("/user");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 사용자 상세보기
    setUser: function ({ commit }, id) {
      const API_URL = `http://localhost:8080/user/${id}`;
      axios({
        url: API_URL,
        method: "get",
      })
        .then((res) => {
          commit("SET_USER", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 사용자 로그인 정보
    setLoginUser: function ({ commit }, user) {
      const API_URL = `http://localhost:8080/user`;
      console.log(user.id);
      console.log(API_URL);
      axios({
        url: API_URL,
        method: "post",
        data: {
          id: user.id,
          pasword: user.password,
        },
      })
        .then((res) => {
          let resUser = res.data;
          if (resUser.id === user.id && resUser.password === user.password) {
            commit("SET_LOGIN_USER", res.data);
            console.log("로그인 완료");
            router.push("/");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
  modules: {
    houseStore,
    boardStore,
    BookStore,
  },
});
