<template>
  <section class="section-profile-cover section-lg my-0" style="background-color:rgb(186,208,134)">
    <div class="container pt-lg-md">
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
              <h2 class="mb-5">LOGIN</h2>

              <form role="form" :state="nameState">
                <base-input
                  alternative
                  class="mb-3"
                  placeholder="ID"
                  type="text"
                  id="id"
                  v-model="id"
                  addon-left-icon="ni ni-circle-08"
                >
                </base-input>
                <base-input
                  alternative
                  type="password"
                  id="password"
                  v-model="password"
                  placeholder="Password"
                  addon-left-icon="ni ni-lock-circle-open"
                >
                </base-input>
                <div class="text-center">
                  <base-button type="primary" class="col-md-4" @click="login"
                    >Sign In</base-button
                  >
                </div>
              </form>
            </template>
          </card>
          <div class="row mt-3">
            <div class="col-6 text-left">
            </div>
            <div class="col-6 text-right">
              <a href="/regist" class="text-black">
                <small>Create new account</small>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Card from "@/ArgonComp/Card.vue";
import { mapActions } from "vuex";
export default {
  components: {
    Card,
  },

  name: "LoginForm",
  data() {
    return {
      id: "",
      password: "",
    };
  },
  methods: {
    ...mapActions(["loginUser"]),
    login() {
      let user = {
        id: this.id,
        password: this.password,
      };
      this.loginUser(user);
      this.$router.replace("/");
    },
    moveRegist() {
      this.$router.replace({ name: "Regist" });
    },
  },
};
</script>

<style scoped></style>
