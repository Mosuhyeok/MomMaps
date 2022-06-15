import VueChatBot from "@/components/chat/BotUI.vue";

const Plugin = {
  install(Vue, options) {
    Vue.component("VueChatBot", VueChatBot);

    if (typeof window !== "undefined" && window.Vue) {
      window.Vue.use(Plugin);
    }
  },
};

export default Plugin;
export { VueChatBot };
