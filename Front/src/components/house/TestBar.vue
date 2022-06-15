<template>
  <div>
    <br />
    <kakao-test></kakao-test>
    <!-- <base-slider v-model="sliders.slider2" :range="{ min: 0, max: 500 }"> </base-slider>
    <p>Value: {{ sliders.slider2 }}</p> -->
  </div>
</template>

<script>
// import BaseSlider from "@/ArgonComp/BaseSlider.vue";
import KakaoTest from "@/components/map/KakaoTest.vue";
export default {
  components: {
    //BaseSlider,
    KakaoTest,
  },
  name: "TestBar",

  data() {
    return {
      step: 100,
      sliders: {
        slider1: 0,
        slider2: [150, 400],
      },
    };
  },
  methods: {
  },
};

</script>

<style></style>
