<template>
  <Bar></Bar>
</template>

<script>
import { Bar } from "vue-chartjs/legacy";

export default {
  name: "Bar",
  components: { Bar },
  data() {
    return {
      chartData: {
        labels: ["January", "February", "March"],
        datasets: [{ data: [40, 20, 12] }],
      },
      chartOptions: {
        responsive: true,
      },
    };
  },
  mounted() {
    this.renderChart(this.datacollection, this.options);
  },
};
</script>
