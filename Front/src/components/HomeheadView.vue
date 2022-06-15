<template>
  <div class="demo">
    <div class="row ms-sm-5 p-5" style="float: center; margin: 0 auto"></div>
  </div>
</template>

<script>
export default {
  name: "HomeheadView",
};
</script>

<style lang="scss" scoped>
.demo {
  background: url(@/assets/logos/facebook_cover_photo_2.png);
  /*백그라운드 위치 옵션을 center 정중앙에 위치 */
  background-position: center; /*기본적으로 백그라운드의 옵션은 repeat이다. */
  //background-repeat: no-repeat; /* 백그라운드 이미지를 보여주기 위한 크기 옵션 */
  width: 100%;
  height: 500px; /* 외부 여백 옵션 */
}
</style>
