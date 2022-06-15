import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import Argon from "./plugins/argon-kit";
import VueCarousel from "vue-carousel";
Vue.use(BootstrapVue);
Vue.config.productionTip = false;
Vue.use(Argon);
Vue.use(VueCarousel);
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
