<template>
  <div>
    <sidebar></sidebar>
    <KakaoBook></KakaoBook>
  </div>
</template>

<script>
import sidebar from "@/components/sidebar/sidebar.vue";
import KakaoBook from "@/components/map/KakaoBook.vue";
export default {
  name: "BookView",
  components: {
    sidebar,
    KakaoBook,
  },
};
</script>

<style></style>
