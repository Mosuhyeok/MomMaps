<template>
  <div class="profile-page" style="background-color:rgb(186,208,134)">
    <section class="section-profile-cover"></section>

    <section class="section section-skew" style="background-color: white">
      <div class="container">
        <card shadow class="card-profile mt--300" no-body>
          <div class="px-4">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2 mb-5">
                <div class="card-profile-image">
                  <img
                    v-lazy="'/img/theme/kangho.jpg'"
                    class="rounded-circle"
                  />
                </div>
              </div>
              <div
                class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center"
              >
                <div class="card-profile-actions py-4 mt-lg-0">
                  <!-- <base-button type="info" size="sm" class="mr-4">Connect</base-button>
                                    <base-button type="default" size="sm" class="float-right">Message</base-button> -->
                </div>
              </div>
              <div class="col-lg-4 order-lg-1"></div>
            </div>
            <div class="text-center mt-5">
              <br /><br />
              <h3>{{ userInfo.name }} 님의 페이지 입니다.</h3>
              <div class="h6 font-weight-300">
                <i class="ni ni-badge"></i> {{ userInfo.id }}
              </div>
            </div>
            <div class="text-center mt-4">
              <div>
                <b-tabs content-class="mt-3" justified>
                  <b-tab title="내가 쓴 글" active>
                    <br />
                    <b-row>
                      <b-col md="4"><b>No.</b></b-col>
                      <b-col md="8"><b>글 제목</b></b-col>
                    </b-row>
                    <hr />
                    <my-write
                      v-for="article in articles"
                      :key="article.articleno"
                      v-bind="article"
                    >
                    </my-write>
                  </b-tab>
                  <b-tab title="찜한 목록"><br /><my-interest></my-interest></b-tab>
                  <b-tab title="내 정보 수정">
                    <b-form @submit.prevent="updateProfile">
                      <h6 class="heading-small text-muted mb-4">
                        User information
                      </h6>
                      <div class="pl-lg-4">
                        <b-row>
                          <b-col lg="6">
                            <base-input
                              type="text"
                              label="ID"
                              placeholder="ID"
                              v-model="user.id"
                              readonly
                            >
                            </base-input>
                          </b-col>
                          <b-col lg="6">
                            <base-input
                              type="password"
                              label="Password"
                              v-model="userInfo.password"
                              placeholder="Password"
                            >
                            </base-input>
                          </b-col>
                        </b-row>
                        <b-row>
                          <b-col lg="6">
                            <base-input
                              type="email"
                              label="Email"
                              placeholder="Email"
                              v-model="userInfo.email"
                            >
                            </base-input>
                          </b-col>
                          <b-col lg="6">
                            <base-input
                              type="text"
                              label="Phone Number"
                              placeholder="Phone Number"
                              v-model="userInfo.phone"
                            >
                            </base-input>
                          </b-col>
                        </b-row>
                      </div>
                      <b-button class="mt-3" @click="updateUser" type="default"
                        >수정</b-button
                      >
                      <base-button
                        class="mt-3"
                        @click="deleteUser"
                        type="danger"
                        >탈퇴</base-button
                      >
                      <hr class="my-4"
                    /></b-form>
                  </b-tab>
                  <!-- <b-tab title="Disabled" disabled><p>I'm a disabled tab!</p></b-tab> -->
                </b-tabs>
              </div>
            </div>
          </div>
        </card>
      </div>
      <br /><br /><br /><br /><br /><br /><br />
    </section>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import MyWrite from "./item/MyWrite.vue";
import MyInterest from "./item/MyInterest.vue";
export default {
  components: {
    MyWrite,
    MyInterest,
  },
  data() {
    return {
      userInfo: {},
      articles: [],
    };
  },
  name: "UserDetail",
  methods: {
    updateUser() {
      let data = {
        id: this.userInfo.id,
        password: this.userInfo.password,
        name: this.userInfo.name,
        email: this.userInfo.email,
        phone: this.userInfo.phone,
        role: "guest",
      };

      alert(data.password);
      const API_URL = "http://localhost:8080/user";
      axios({
        url: API_URL,
        method: "put",
        data: data,
      })
        .then(() => {
          alert("회원정보 변경이 완료되었습니다 !");
          location.href = "/";
        })
        .catch((err) => {
          console.log(err);
        });
    },

    deleteUser() {
      this.$store.dispatch("deleteUser", this.user.id);
    },
  },
  computed: {
    ...mapState(["user"]),
  },
  created() {
    const API_URL = "http://localhost:8080/user/" + this.user.id;
    axios({
      url: API_URL,
      method: "get",
    })
      .then((response) => {
        this.userInfo = response.data;
        this.userInfo.password = "";
      })
      .catch((err) => {
        console.log(err);
      });

    const API_URL2 = "http://localhost:8080/post/myinfo/" + this.user.id;

    axios({
      url: API_URL2,
      method: "get",
    })
      .then((response) => {
        console.log(response.data[0]);
        this.articles = [...this.articles, ...response.data];
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
