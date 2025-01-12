<template>
    <div class="chart-container">
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
  };
  </script>
  
  <style scoped>
  .chart-container {
    width: 400px; /* 限制图表的宽度 */
    height: 400px; /* 限制图表的高度 */
    margin: 0 auto; /* 居中对齐 */
    position: relative; /* 保证子元素绝对定位时相对父元素 */
  }
  
  canvas {
    display: block;
    width: 100%;
    height: 100%;
  }
  </style>
  