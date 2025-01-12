<template>
  <div class="admin-overview">
    <!-- 上层布局 -->
    <div class="top-section">
      <!-- 信息总览 -->
      <div class="info-box">
        <h2>信息总览</h2>
        <ul>
          <li>新生总人数: <strong>{{ totalStudent }}</strong></li>
          <li>男生人数: <strong>{{ boyCount }}</strong></li>
          <li>女生人数: <strong>{{ girlCount }}</strong></li>
          <li>报道人数: <strong>{{ reportedStudent }}</strong></li>
          <li>未报道人数: <strong>{{ totalStudent - reportedStudent }}</strong></li>
        </ul>
      </div>
      <!-- 预留位置 -->
      <div class="placeholder-box">
        <h2>预留区域</h2>
        <p>可以放置额外信息或功能</p>
      </div>
    </div>

    <!-- 下层布局 -->
    <div class="bottom-section">
      <h2>图表视图</h2>
      <OverviewPie />
    </div>
  </div>
</template>

<script>
import OverviewPie from '@/components/OverviewPie.vue';
import axios from 'axios';

export default {
  data() {
    return {
      totalStudent: '',
      boyCount: '',
      girlCount: '',
      reportedStudent: '',
    };
  },
  name: 'AdminOverview',
  components: {
    OverviewPie,
  },
  methods:{
    async getData(){
      try{
      const response=await axios.get('/api/admin/getTotalData');
      if (response.data.success){
        console.log("请求数据成功");
        this.totalStudent=response.data.totalStudent;
        this.boyCount=response.data.boyCount;
        this.girlCount=response.data.girlCount;
        this.reportedStudent=response.data.reportedStudent;
      }
      }catch(error){
        console.error("请求数据错误",error);
      }
    }
  }
};
</script>

<style scoped>
/* 外层容器 */
.admin-overview {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
}

/* 上层布局 */
.top-section {
  display: flex;
  gap: 20px;
}

/* 信息总览框样式 */
.info-box {
  flex: 1;
  background: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.info-box h2 {
  margin-bottom: 15px;
  color: #333;
}

.info-box ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.info-box li {
  margin-bottom: 10px;
  color: #555;
  font-size: 16px;
}

.info-box strong {
  color: #000;
}

/* 预留位置框样式 */
.placeholder-box {
  flex: 1;
  background: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.placeholder-box h2 {
  color: #333;
  margin-bottom: 10px;
}

.placeholder-box p {
  color: #666;
}

/* 下层布局 */
.bottom-section {
  width: 100%; /* 占满整个页面宽度 */
  background: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.bottom-section h2 {
  margin-bottom: 15px;
  color: #333;
  text-align: center; /* 居中标题 */
}
</style>
