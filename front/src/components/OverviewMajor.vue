<template>
  <div class="major-chart">
    <canvas ref="chart"></canvas>
  </div>
</template>

<script>
import { Chart, registerables } from "chart.js";

Chart.register(...registerables);

export default {
  name: "OverviewMajor",
  props: {
    data: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      chartInstance: null,
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart();
    });
  },
  watch: {
    data: {
      handler() {
        this.$nextTick(() => {
          if (this.chartInstance) {
            this.updateChart();
          } else {
            this.initChart();
          }
        });
      },
      deep: true,
    },
  },
  methods: {
    initChart() {
      const canvas = this.$refs.chart;
      if (!canvas) return;

      const ctx = canvas.getContext("2d");
      if (!ctx) return;

      if (this.chartInstance) {
        this.chartInstance.destroy();
      }

      const majors = this.data.map((item) => item.major);
      const counts = this.data.map((item) => item.count);

      this.chartInstance = new Chart(ctx, {
        type: "bar",
        data: {
          labels: majors,
          datasets: [
            {
              label: "人数",
              data: counts,
              backgroundColor: "rgba(54, 162, 235, 0.6)",
              borderColor: "rgba(54, 162, 235, 1)",
              borderWidth: 1,
              borderRadius: 5,
              barThickness: 30,
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              display: false,
            },
            tooltip: {
              enabled: true,
              backgroundColor: "rgba(0,0,0,0.7)",
              titleColor: "#fff",
              bodyColor: "#fff",
              padding: 10,
              displayColors: false,
              callbacks: {
                label: function(context) {
                  return `人数: ${context.parsed.y}`;
                }
              }
            },
            customCanvasBackgroundColor: {
              id: 'customCanvasBackgroundColor',
              afterDraw: (chart) => {
                const {ctx} = chart;
                ctx.save();
                ctx.textAlign = 'center';
                ctx.textBaseline = 'bottom';
                ctx.font = '12px Arial';
                ctx.fillStyle = '#666';

                chart.data.datasets.forEach((dataset, i) => {
                  const meta = chart.getDatasetMeta(i);
                  meta.data.forEach((bar, index) => {
                    const data = dataset.data[index];
                    ctx.fillText(data, bar.x, bar.y - 5);
                  });
                });
                ctx.restore();
              }
            }
          },
          scales: {
            x: {
              grid: {
                display: false,
              },
              ticks: {
                color: "#666",
                font: {
                  size: 11
                }
              }
            },
            y: {
              beginAtZero: true,
              grid: {
                color: "rgba(0, 0, 0, 0.1)",
              },
              ticks: {
                color: "#666",
                font: {
                  size: 12
                }
              }
            },
          }
        },
      });
    },
    updateChart() {
      if (!this.chartInstance) return;

      this.chartInstance.data.labels = this.data.map((item) => item.major);
      this.chartInstance.data.datasets[0].data = this.data.map(
        (item) => item.count
      );
      this.chartInstance.update();
    },
  },
  beforeUnmount() {
    if (this.chartInstance) {
      this.chartInstance.destroy();
      this.chartInstance = null;
    }
  },
};
</script>

<style scoped>
.major-chart {
  text-align: center;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

canvas {
  width: 100% !important;
  height: 100% !important;
}
</style>
