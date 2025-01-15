<template>
  <div class="admin-overview">
    <!-- 上排三块 -->
    <div class="row">
      <!-- 信息总览模块 -->
      <div class="block" v-if="chartReady">
        <h2>信息总览</h2>
        <ul class="info-list">
          <li>新生总人数: <strong>{{ totalStudent }}</strong></li>
          <li>男生人数: <strong>{{ boyCount }}</strong></li>
          <li>女生人数: <strong>{{ girlCount }}</strong></li>
          <li>报道人数: <strong>{{ reportedStudent }}</strong></li>
          <li>未报道人数: <strong>{{ totalStudent - reportedStudent }}</strong></li>
        </ul>
      </div>
      <!-- 模块 2 -->
      <div class="block" v-if="chartReady">
        <h3>按入学年份统计</h3>
        <OverviewEnrollmentYear :data="enrollmentYearData" />
      </div>
      <!-- 模块 3 -->
      <div class="block" v-if="chartReady">
        <h3>性别分布</h3>
        <OverviewGender :boyCount="boyCount" :girlCount="girlCount" />
      </div>
    </div>

    <!-- 下排两块 -->
    <div class="row">
      <!-- 模块 4 -->
      <div class="block" v-if="chartReady">
        <h3>按专业统计</h3>
        <OverviewMajor :data="majorList" />
      </div>
      <!-- 模块 5 -->
      <div class="block" v-if="chartReady">
        <h3>报道情况</h3>
        <OverViewRegister :totalStudent="totalStudent" :reportedStudent="reportedStudent" />
      </div>
    </div>
  </div>
</template>

<script>
import OverviewEnrollmentYear from "@/components/OverviewEnrollmentYear.vue";
import OverviewGender from "@/components/OverviewGender.vue";
import OverviewMajor from "@/components/OverviewMajor.vue";
import OverViewRegister from "@/components/OverViewRegister.vue";
import axios from "axios";

export default {
  name: "AdminOverview",
  components: {
    OverviewEnrollmentYear,
    OverviewGender,
    OverviewMajor,
    OverViewRegister,
  },
  data() {
    return {
      chartReady: false,
      totalStudent: 1500, // 或者 0
      boyCount: 1000,
      girlCount: 500,
      reportedStudent: 1000,
      majorList: [{ major: '计算机科学与技术', count: 100 }, { major: '软件工程', count: 200 }, { major: '网络工程', count: 300 }],
      enrollmentYearData: [{ year: '2020', count: 150 }, { year: '2021', count: 180 }, { year: '2022', count: 200 }, { year: '2023', count: 220 }]

    };
  },
  methods: {
    // 获取信息数据
    async getData() {
      try {
        const response = await axios.get("/api/admin/getTotalData");
        console.log(response.data);
        if (response.data.success) {
          const { totalStudent, boyCount, girlCount, reportedStudent, majorList, enrollmentYearData } = response.data;
          console.log(totalStudent, boyCount, girlCount, reportedStudent, majorList, enrollmentYearData);

          // 验证每个字段是否存在
          this.totalStudent = totalStudent || 0;
          this.boyCount = boyCount || 0;
          this.girlCount = girlCount || 0;
          this.reportedStudent = reportedStudent || 0;

          this.majorList = Array.isArray(majorList)
            ? majorList.map(item => ({ major: item.major, count: item.count }))
            : [];

          this.enrollmentYearData = Array.isArray(enrollmentYearData)
            ? enrollmentYearData.map(item => ({ year: item.year, count: item.count }))
            : [];
          this.chartReady = true;
        } else {
          console.error("获取数据失败: 数据格式不符合预期", response.data);
        }

      } catch (error) {
        console.error("获取数据失败2:", error);
      }
    },
  },
  mounted() {
    setTimeout(() => {
      const mockResponse = {
        success: true,
        data: {
          totalStudent: 500,
          boyCount: 300,
          girlCount: 200,
          reportedStudent: 450,
          majorList: [
            { major: "计算机科学", count: 150 },
            { major: "电子工程", count: 100 },
            { major: "机械工程", count: 200 },
            { major: "数学", count: 50 },
          ],
          enrollmentYearData: [
            { year: "2020", count: 120 },
            { year: "2021", count: 130 },
            { year: "2022", count: 140 },
            { year: "2023", count: 110 },
          ],
        },
      };

      if (mockResponse.success) {
        const { totalStudent, boyCount, girlCount, reportedStudent, majorList, enrollmentYearData } = mockResponse.data;

        this.totalStudent = totalStudent;
        this.boyCount = boyCount;
        this.girlCount = girlCount;
        this.reportedStudent = reportedStudent;
        this.majorList = majorList.map(item => ({ major: item.major, count: item.count }));
        this.enrollmentYearData = enrollmentYearData.map(item => ({ year: item.year, count: item.count }));
        this.chartReady = true; 
      }
    }, 1000); // 模拟 1 秒延迟
    
  },

}
</script>

<style scoped>
/* 页面整体容器 */
.admin-overview {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
  background-color: #f9f9f9;
  height: 100%;
  box-sizing: border-box;
  overflow: hidden;
}

/* 行容器 */
.row {
  display: flex;
  justify-content: space-between;
  gap: 20px;
  width: 100%;
  height: calc(50% - 10px);
  /* 每行占50%高度，减去gap的一半 */
  min-height: 0;
}

/* 模块样式 */
.block {
  flex: 1;
  background: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  height: 100%;
  box-sizing: border-box;
  overflow: hidden;
}

/* 标题样式 */
.block h2,
.block h3 {
  margin: 0 0 15px 0;
  /* 减小标题的margin */
  color: #333;
  text-align: center;
  flex-shrink: 0;
  /* 防止标题被压缩 */
}

/* 图表容器样式 */
.block>div {
  flex: 1;
  width: 100%;
  overflow: hidden;
  /* 防止图表溢出 */
}

/* 信息列表的特殊样式 */
.info-list {
  margin: 0;
  padding: 0;
  list-style: none;
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.block li {
  font-size: 16px;
  color: #555;
  margin-bottom: 8px;
}

.block strong {
  color: #000;
}
</style>
