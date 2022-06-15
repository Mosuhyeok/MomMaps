<template>
  <div class="chart">
    <bar></bar>
  </div>
</template>

<script>
import Bar from "@/components/house/Bar.vue";

export default {
  name: "chart",
  data() {
    return {};
  },
  computed: {
    Bar,
  },
  mounted() {
    this.renderChart(this.data, this.options);
  },
};
</script>

<style>
.chart {
  margin: 100px;
  padding: 100px;
  width: 50%;
}
</style>
