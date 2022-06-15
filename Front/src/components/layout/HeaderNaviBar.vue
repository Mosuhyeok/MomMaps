<template>
  <!-- Navigation-->
  <div id="space">
    <nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top" id="mainNav">
      <b-container>
        <a href="/">
          <img src="@/assets/logos/logo_transparent.png" style="width: 80px" />
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu <i class="fas fa-bars ms-1"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav text-uppercase ml-auto py-4 py-lg-0">
            <router-link to="/announcement" class="nav-link">공지사항</router-link>
            <li class="nav-item">
              <router-link to="/board" class="nav-link">정보 게시판</router-link>
            </li>

            <li class="nav-item">
              <router-link class="nav-link" :to="{ name: 'house' }">지역 검색</router-link>
            </li>

            <!-- <li class="nav-item">
              <router-link class="nav-link" :to="{ name: 'test' }">테스트</router-link>
            </li>
            <li class="nav-item">
              <a href="https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=%2C%2C523953%2C1084098&rt1=대전역&rt2=서울대학교&rtIds=%2C&rtTypes=%2C">주소이동</a>
            </li> -->

            <b-navbar-nav v-if="login && user">
              <b-nav-item class="align-self-center disableP">
                <i class="ni ni-circle-08"></i>
                {{ user.id }}님 환영합니다.</b-nav-item
              >

              <b-nav-item-dropdown class="align-self-center" right>
                <b-dropdown-item>
                  <router-link to="/detail" class="nav-link">마이페이지</router-link>
                </b-dropdown-item>
                <b-dropdown-item>
                  <a class="nav-link" @click="test">로그아웃</a>
                </b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>

            <!-- <li class="nav-item" v-if="login">
              <router-link to="/detail" class="nav-link">마이페이지</router-link>
            </li>
            <li class="nav-item" v-if="login">
              <a class="nav-link" @click="test">로그아웃</a>
            </li> -->

            <li class="nav-item" v-if="login == null">
              <router-link :to="{ name: 'Regist' }" class="nav-link">회원가입</router-link>
            </li>
            <li class="nav-item" v-if="login == null">
              <router-link to="/login" class="nav-link">로그인</router-link>
            </li>
          </ul>
        </div>
      </b-container>
    </nav>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  computed: {
    login() {
      return this.$store.state.token;
    },
    user() {
      return this.$store.state.user;
    },
  },
  methods: {
    ...mapActions(["logoutUser"]),
    test: function () {
      this.logoutUser();
    },
  },
};
</script>

<style>
#space {
  height: 100px;
}
.disableP {
  pointer-events: none;
}
</style>
