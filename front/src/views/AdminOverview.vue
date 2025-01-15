<template>
  <div class="admin-overview">
    <!-- 上排三块 -->
    <div class="row">
      <!-- 信息总览模块 -->
      <div class="block">
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
      <div class="block">
        <h3>按入学年份统计</h3>
        <OverviewEnrollmentYear :data="enrollmentYearData" />
      </div>
      <!-- 模块 3 -->
      <div class="block">
        <h3>性别分布</h3>
        <OverviewGender :boyCount="boyCount" :girlCount="girlCount" />
      </div>
    </div>

    <!-- 下排两块 -->
    <div class="row">
      <!-- 模块 4 -->
      <div class="block">
        <h3>按专业统计</h3>
        <OverviewMajor :data="majorList" />
      </div>
      <!-- 模块 5 -->
      <div class="block">
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
      totalStudent: null, // 或者 0
      boyCount: 0,
      girlCount: 0,
      reportedStudent: 0,
      majorList: [],
      enrollmentYearData: []

    };
  },
  methods: {
    // 获取信息数据
    async getData() {
      try {
        const response = await axios.get("/api/admin/getTotalData");
        if (response.data.success && response.data.totalStudent !== undefined) {
          this.totalStudent = response.data.totalStudent || 0;
          this.boyCount = response.data.boyCount || 0;
          this.girlCount = response.data.girlCount || 0;
          this.reportedStudent = response.data.reportedStudent || 0;

          this.majorList = (response.data.majorList || []).map(item => ({
            name: item.major,
            value: item.count
          }));

          this.enrollmentYearData = (response.data.enrollmentYearData || []).map(item => ({
            name: item.year,
            value: item.count
          }));
        } else {
          console.error("获取数据失败1:", response.data?.message || "未知错误");
        }
      } catch (error) {
        console.error("获取数据失败2:", error);
      }
    },
  },
  mounted() {
    this.getData(); // 组件挂载时获取数据
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
