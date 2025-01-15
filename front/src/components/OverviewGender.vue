<template>
  <div class="chart-container">
    <canvas ref="genderChart"></canvas>
  </div>
</template>

<script>
import { Chart, DoughnutController, ArcElement, Tooltip, Legend } from 'chart.js';

// 注册必要的组件
Chart.register(DoughnutController, ArcElement, Tooltip, Legend);

export default {
  name: 'OverviewGender',
  props: {
    boyCount: {
      type: Number,
      required: true
    },
    girlCount: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      chart: null
    };
  },
  methods: {
    initChart() {
      const ctx = this.$refs.genderChart.getContext('2d');
      
      // 销毁旧的图表实例
      if (this.chart) {
        this.chart.destroy();
      }

      this.chart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['男生', '女生'],
          datasets: [{
            data: [this.boyCount, this.girlCount],
            backgroundColor: ['#36A2EB', '#FF6384'],
            borderWidth: 0
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              position: 'bottom'
            },
            tooltip: {
              callbacks: {
                label: function(context) {
                  const label = context.label || '';
                  const value = context.raw || 0;
                  const total = context.dataset.data.reduce((a, b) => a + b, 0);
                  const percentage = Math.round((value / total) * 100);
                  return `${label}: ${value}人 (${percentage}%)`;
                }
              }
            }
          }
        }
      });
    },
    updateChart() {
      if (this.chart) {
        this.chart.data.datasets[0].data = [this.boyCount, this.girlCount];
        this.chart.update();
      }
    }
  },
  mounted() {
    this.initChart();
  },
  watch: {
    boyCount() {
      this.updateChart();
    },
    girlCount() {
      this.updateChart();
    }
  },
  beforeUnmount() {
    // 组件销毁前清理图表实例
    if (this.chart) {
      this.chart.destroy();
      this.chart = null;
    }
  }
};
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
  position: relative;
}
</style>
