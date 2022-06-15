<template>
  <div class="chart">
    <canvas id="myChart" width="100" height="100"></canvas>
  </div>
</template>

<script>
import { Chart, BarElement, DoughnutController, BarController, LinearScale, RadialLinearScale, LineController, RadarController, LineElement, PointElement, CategoryScale } from "chart.js";
Chart.register(BarElement, BarController, DoughnutController, RadialLinearScale, RadarController, LinearScale, LineController, LineElement, PointElement, CategoryScale);
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  props: {
    houseInfo: Array,
  },

  data() {
    return {
      chartArea: null,
      myChart: null,
      labels: [],
      data: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["houseDetails", "chart"]),
  },

  watch: {
    chart(newv) {
      console.log("확인가능?" + newv);
      //this.chartArea = document.getElementById("myChart").getContext("2d");
      this.setdata();
      this.makeChart();
    },
  },
  mounted() {
    console.log("마운트실행");
    this.chartArea = document.getElementById("myChart").getContext("2d");
    this.setdata();
    this.makeChart();
  },
  methods: {
    setdata() {
      this.data = [];
      this.labels = [];
      // console.log("마운트데이터" + this.houseAvgs[0].aptName);
      this.houseDetails.forEach((houseDetail) => {
        this.labels.push(houseDetail.dealYear);
        this.data.push(houseDetail.dealAmount);
      });
      console.log("버근가 " + this.data[0]);
      console.log("set" + this.labels);
      console.log("set" + this.data);
    },
    updateChart() {
      //console.log(this.chartArea);
      //console.log(this.myChart);
      //this.myChart.chartArea = this.chartArea;
      var myChart = this.myChart;
      myChart.data.datasets.data = this.data;
      myChart.data.datasets.labels = this.labels;
      myChart.update();
    },
    makeChart() {
      // 차트를 그럴 영역을 dom요소로 가져온다.
      //var chartArea = this.chartArea;
      // 차트를 생성한다.
      this.myChart = new Chart(this.chartArea, {
        // ①차트의 종류(String)
        type: "line",
        // ②차트의 데이터(Object)
        data: {
          // ③x축에 들어갈 이름들(Array)
          // labels: ["1990", "2000", "Yellow", "Green", "Purple", "Orange"],
          labels: this.labels,
          // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
          datasets: [
            {
              // ⑤dataset의 이름(String)
              label: "가격",
              //line형태일때, 선안쪽을 채울지, 안채울지
              fill: false,
              // ⑥dataset값(Array)
              data: this.data,
              //data: ["1", "1", "1", "1"],
              // ⑦dataset의 배경색(rgba값을 String으로 표현)
              backgroundColor: "rgba(255, 99, 132, 0.2)",
              // ⑧dataset의 선 색(rgba값을 String으로 표현)
              borderColor: "rgba(255, 99, 132, 1)",
              // ⑨dataset의 선 두께(Number)
              borderWidth: 1,
            },
          ],
        },
      });
      console.log("첫번째데이터: " + this.data[0].type);
      console.log("첫번째데이터: " + (this.data[0] * 1).type);
      this.updateChart();
      //this.myChart.update();
    },
  },
};
</script>

<style>
/* .chart {
  margin: 100px;
  padding: 100px;
  width: 50%;
} */
</style>
