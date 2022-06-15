<template>
  <div class="chart">
    <canvas id="myChart" width="100" height="100"></canvas>
  </div>
</template>

<script>
//import { Chart } from "vue-chartjs";
import { Chart, BarElement, BarController, LinearScale, LineController, LineElement, PointElement, CategoryScale } from "chart.js";
Chart.register(Chart, BarElement, BarController, LinearScale, LineController, LineElement, PointElement, CategoryScale);
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  extends: Chart,
  mixins: [reactiveProp],
  props: ["data", "options"],
  data() {
    return {
      chartArea: null,
      myChart: null,
      labels: [],
      data: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["houseAvgs"]),
  },
  watch: {
    houseAvgs() {
      console.log("수정");
      this.setdata();
      this.updateChart();
    },
    // Avgnames(newv) {
    //   this.labels = newv;
    //   this.makeChart();
    // },
  },
  mounted() {
    console.log("마운트");
    this.chartArea = document.getElementById("myChart").getContext("2d");
    this.setdata();
    this.makeChart();
    this.renderChart(this.data, this.options);
  },
  methods: {
    setdata() {
      this.data = [];
      this.labels = [];
      this.houseAvgs.forEach((houseavg) => {
        this.labels.push(houseavg.aptName);
        this.data.push(houseavg.avg);
      });
      console.log("set" + this.labels);
      console.log("set" + this.data);
    },
    updateChart() {
      console.log(this.chartArea);
      console.log(this.myChart);
      this.myChart.update();
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
        type: "bar",
        // ②차트의 데이터(Object)
        data: {
          // ③x축에 들어갈 이름들(Array)
          // labels: ["1990", "2000", "Yellow", "Green", "Purple", "Orange"],
          labels: this.labels,
          // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
          datasets: [
            {
              // ⑤dataset의 이름(String)
              label: "# of Votes",
              //line형태일때, 선안쪽을 채울지, 안채울지
              fill: false,
              // ⑥dataset값(Array)
              data: this.data,
              // ⑦dataset의 배경색(rgba값을 String으로 표현)
              backgroundColor: "rgba(255, 99, 132, 0.2)",
              // ⑧dataset의 선 색(rgba값을 String으로 표현)
              borderColor: "rgba(255, 99, 132, 1)",
              // ⑨dataset의 선 두께(Number)
              borderWidth: 1,
            },
          ],
        },
        // ⑩차트의 설정(Object)
        options: {
          // ⑪축에 관한 설정(Object)
          scales: {
            // ⑫y축에 대한 설정(Object)
            y: {
              // ⑬시작을 0부터 하게끔 설정(최소값이 0보다 크더라도)(boolean)
              beginAtZero: true,
            },
          },
        },
      });
      this.myChart.update();
    },
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
