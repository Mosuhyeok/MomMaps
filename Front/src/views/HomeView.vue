<template>
  <div>
    <!-- Header Component -->
    <div>
      <homehead-view></homehead-view>
      <section-view></section-view>
      <crawling></crawling>
      <chatbot></chatbot>
      <footer-view></footer-view>
    </div>
  </div>
</template>

<script>
import HomeheadView from "../components/HomeheadView.vue";
import SectionView from "../components/SectionView.vue";
import FooterView from "@/components/layout/FooterView.vue";
import Crawling from "@/components/crawling/Crawling.vue";
import chatbot from "@/components/chat/chatbot.vue";
export default {
  name: "HomeView",
  components: {
    HomeheadView,
    SectionView,
    FooterView,
    Crawling,
    chatbot,
  },
};
</script>

<style lang="scss" scoped></style>
