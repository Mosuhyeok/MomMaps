<template>
  <div class="container">
    <modal :show.sync="modals.mLogin" body-classes="p-0" modal-classes="modal-dialog-centered modal-sm">
      <div class="container mt-5">
        <h2>로그인</h2>
      </div>
      <card type="secondary" shadow 
      header-classes="bg-white pb-5" body-classes="px-lg-5 py-lg-5" class="border-0">
        <template>
            <form role="form">
                <base-input alternative
                            class="mb-3"
                            placeholder="ID"
                            type="text" id="id" v-model="id"
                            addon-left-icon="ni ni-email-83">
                </base-input>
                <base-input alternative
                            type="password" id="password" v-model="password"
                            placeholder="Password"
                            addon-left-icon="ni ni-lock-circle-open">
                </base-input>
                <base-checkbox>
                    Remember me
                </base-checkbox>
                <div class="text-center">
                    <base-button type="primary" class="col-md-4" @click="login">Sign In</base-button>
                </div>
            </form>
        </template>
      </card>
    </modal>
  </div>
</template>
<script>
import Modal from "@/ArgonComp/Modal.vue";
import { mapActions } from "vuex";
export default {
  name: "LoginForm",
  components: {
    Modal,
  },
  data() {
    return {
      id: "",
      password: "",
      modals: {
        mLogin: true,
      }
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
  },
};
</script>
<style></style>
