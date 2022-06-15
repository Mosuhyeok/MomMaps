<template>
  <div>
    <div class="bv-example-row mt-3 text-center" style="witdh:100%; height: 800px;">
      <router-view></router-view>
      <footer-view></footer-view>
    </div>
  </div>
</template>
<script>
import FooterView from "@/components/layout/FooterView.vue";
export default {
  name: "BoardView",
  components: {
    FooterView,
  },
};
</script>
<style></style>