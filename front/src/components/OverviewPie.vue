<template>
    <div>
      <h1>报告统计</h1>
      <canvas ref="myPieChart"></canvas>
    </div>
  </template>
  
  <script>
  import { Chart, ArcElement, Tooltip, Legend, Title, CategoryScale, LinearScale, PieController } from 'chart.js';
  
  // 注册这些元素和控制器
  Chart.register(ArcElement, Tooltip, Legend, Title, CategoryScale, LinearScale, PieController);
  
  export default {
    name: 'ReportChart',
    props: {
      total: {
        type: Number,
        required: true,
      },
      reported: {
        type: Number,
        required: true,
      },
    },
    mounted() {
      this.drawChart();
    },
    methods: {
      drawChart() {
        const ctx = this.$refs.myPieChart.getContext('2d');
        new Chart(ctx, {
          type: 'pie',
          data: {
            labels: ['已报告', '未报告'],
            datasets: [
              {
                data: [this.reported, this.total - this.reported],
                backgroundColor: ['#4caf50', '#f44336'],
                hoverBackgroundColor: ['#45a049', '#e53935'],
              },
            ],
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'top',
              },
              tooltip: {
                callbacks: {
                  label: (tooltipItem) => {
                    const percentage = ((tooltipItem.raw / this.total) * 100).toFixed(2);
                    return `${tooltipItem.label}: ${percentage}%`;
                  },
                },
              },
            },
          },
        });
      },
    },
  };
  </script>
  
  <style scoped>
  /* 你的样式代码 */
  </style>
  