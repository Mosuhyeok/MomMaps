<template>
  <div class="chart">
    <canvas id="resultChart" width="361" height="361" style="display: bolck; box-sizing; border-box; height:361px; width: 361px;"></canvas>
    <!-- <canvas id="resultChart" width="100" height="100"></canvas> -->
  </div>
</template>

<script>
import {
  Chart,
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip,
  SubTitle,
} from "chart.js";

Chart.register(
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip,
  SubTitle
);
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  data() {
    return {
      chartArea: null,
      resultChart: null,
      labels: [],
      data: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["houseNearby"]),
  },
  watch: {
    houseNearby(newv) {
      //this.chartArea = document.getElementById("resultChart").getContext("2d");
      this.setdata(newv);
      this.makeChart();
    },
  },
  created() {
    console.log("니얼:" + JSON.stringify(this.houseNearby));
  },
  mounted() {
    this.chartArea = document.getElementById("resultChart").getContext("2d");
    this.setdata(this.houseNearby);
    this.makeChart();
  },
  methods: {
    setdata(newv) {
      this.data = [];
      this.labels = [];

      newv.forEach((item) => {
        this.labels.push(item.place.place);
        this.data.push(item.cnt);
      });
    },
    updateChart() {
      //this.myChart.chartArea = this.chartArea;
      var resultChart = this.resultChart;
      resultChart.data.datasets.data = this.data;
      resultChart.data.datasets.labels = this.labels;
      resultChart.update();
    },
    makeChart() {
      Chart.defaults.font.size = 16;
      // 차트를 그럴 영역을 dom요소로 가져온다.
      //var chartArea = this.chartArea;
      // 차트를 생성한다.
      this.resultChart = new Chart(this.chartArea, {
        // ①차트의 종류(String)
        type: "radar",

        // ②차트의 데이터(Object)
        data: {
          // ③x축에 들어갈 이름들(Array)
          // labels: ["1990", "2000", "Yellow", "Green", "Purple", "Orange"],
          labels: this.labels,
          // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
          datasets: [
            {
              // ⑤dataset의 이름(String)
              label: "1km 이내 시설",
              //line형태일때, 선안쪽을 채울지, 안채울지
              // fill: true,
              // ⑥dataset값(Array)
              data: this.data,
              // ⑦dataset의 배경색(rgba값을 String으로 표현)
              backgroundColor: "rgba(00,255,00,0.1)",
              hoverBackgroundColor: "#007a00",
              // ⑧dataset의 선 색(rgba값을 String으로 표현)
              borderColor: "#007a00",
              // ⑨dataset의 선 두께(Number)
              borderWidth: 4,
            },
          ],
        },
        // ⑩차트의 설정(Object)

        options: {
          tooltips: {
            backgroundColor: "#007a00",
          },
          scales: {
            r: {
              pointLabels: {
                font: {
                  size: 20,
                  weight: 50,
                },
              },
            },
          },
        },
      });
      // this.resultChart.destroy();
      this.resultChart.update();
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
