<template>
    <div class="gender-chart">
      <canvas ref="chart"></canvas>
    </div>
  </template>
  
  <script>
  import { Chart, registerables } from "chart.js";
  
  Chart.register(...registerables);
  
  export default {
    name: "OverviewGender",
    props: {
      boyCount: {
        type: Number,
        required: true,
      },
      girlCount: {
        type: Number,
        required: true,
      },
    },
    data() {
      return {
        chartInstance: null,
      };
    },
    mounted() {
      this.initChart();
    },
    watch: {
      boyCount: "updateChart",
      girlCount: "updateChart",
    },
    methods: {
      initChart() {
        const ctx = this.$refs.chart.getContext("2d");
  
        // 准备数据
        const data = [this.boyCount, this.girlCount];
  
        // 创建图表实例
        this.chartInstance = new Chart(ctx, {
          type: "doughnut",
          data: {
            labels: ["男生", "女生"],
            datasets: [
              {
                label: "性别分布",
                data: data,
                backgroundColor: ["#36A2EB", "#FF6384"], // 配置环形图颜色
                hoverOffset: 10, // 鼠标悬停偏移量
              },
            ],
          },
          options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
              legend: {
                position: "top",
              },
              tooltip: {
                enabled: true,
              },
            },
          },
        });
      },
      updateChart() {
        if (this.chartInstance) {
          // 更新数据
          this.chartInstance.data.datasets[0].data = [this.boyCount, this.girlCount];
          this.chartInstance.update(); // 刷新图表
        }
      },
    },
  };
  </script>

<style scoped>
.gender-chart {
  text-align: center;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

canvas {
  max-width: 100%;
  max-height: 100%;
}
</style>
