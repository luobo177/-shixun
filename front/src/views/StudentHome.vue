<template>
  <div>
    <div class="student-container">
      <!-- 学生照片 -->
      <div class="photo-container">
        <div class="photo-wrapper">
          <img :src="student.photo" alt="学生照片" class="student-photo" />
        </div>
      </div>

      <!-- 信息表格 -->
      <el-table :data="tableData" border style="width: 100%" class="info-table">
        <el-table-column label="字段" width="120">
          <template #default="scope">{{ scope.row.label }}</template>
        </el-table-column>
        <el-table-column label="内容" min-width="200">
          <template #default="scope">{{ scope.row.value }}</template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "StudentHome",
  data() {
    return {
      student: {
        photo: "", // 初始为空，稍后从数据库模拟填充
        name: "许建航",
        gender: "男",
        birthDate: "2024—06",
        phoneNumber: "18782896937",
        studentId: "2022131032",
        dormInfo: "12",
        registrationStatus: "1",
      },
      tableData: [] // 用于存储表格数据
    };
  },
  mounted() {
    this.fetchStudentData(); // 在组件挂载时调用数据获取方法
  },
  methods: {
    async fetchStudentData() {
      try {
        // 发起 GET 请求从后端获取学生数据
        const response = await axios.get('/api/studentmessage');
        this.student = response.data.student; // 假设返回的结构是 { student: {...} }

        // 格式化数据填充到 tableData
        this.tableData = [
          { label: "姓名", value: this.student.name },
          { label: "性别", value: this.student.gender },
          { label: "出生日期", value: this.student.birthDate },
          { label: "电话号码", value: this.student.phoneNumber },
          { label: "学号", value: this.student.studentId },
          { label: "住校情况", value: this.student.dormInfo },
          { label: "注册信息", value: this.student.registrationStatus },
        ];
      } catch (error) {
        console.error('获取学生信息失败:', error);
      }
    }
  }
};
</script>

<style scoped>
.student-container {
  max-width: 600px;
  margin: 0 auto; /* 居中 */
  background-color: #f9f9f9; /* 浅灰背景 */
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* 添加阴影 */
}

.photo-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.photo-wrapper {
  border: 2px solid #000; /* 实线边框 */
  padding: 5px;
  border-radius: 8px;
}

.student-photo {
  width: 150px;
  height: 150px;
  border-radius: 8px;
  object-fit: cover; /* 确保图片不变形 */
}

.info-table {
  margin-top: 20px;
  border: 1px solid #ddd; /* 为表格添加边框 */
}

.el-table th, .el-table td {
  border: 1px solid #ddd; /* 为表格的每个单元格添加边框 */
}

.el-table th {
  background-color: #f0f0f0; /* 设置表头背景色 */
}

.el-table .cell {
  padding: 10px; /* 增加单元格的内边距 */
  text-align: center; /* 居中对齐内容 */
}
</style>
