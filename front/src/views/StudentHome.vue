<template>
  <div>
    <el-form label-position="top" label-width="120px" class="student-form">
      <!-- 学生照片 -->
      <el-form-item label="照片">
        <img 
          :src="student.photo" 
          alt="学生照片" 
          class="student-photo" 
        />
      </el-form-item>

      <!-- 学生姓名 -->
      <el-form-item label="姓名">
        <el-input v-model="student.name" readonly></el-input>
      </el-form-item>

      <!-- 学生性别 -->
      <el-form-item label="性别">
        <el-input v-model="student.gender" readonly></el-input>
      </el-form-item>

      <!-- 出生日期 -->
      <el-form-item label="出生日期">
        <el-input v-model="student.birthDate" readonly></el-input>
      </el-form-item>

      <!-- 电话号码 -->
      <el-form-item label="电话号码">
        <el-input v-model="student.phoneNumber" readonly></el-input>
      </el-form-item>

      <!-- 学号 -->
      <el-form-item label="学号">
        <el-input v-model="student.studentId" readonly></el-input>
      </el-form-item>

      <!-- 住校信息 -->
      <el-form-item label="住校情况">
        <el-input v-model="student.dormInfo" readonly></el-input>
      </el-form-item>

      <!-- 注册信息 -->
      <el-form-item label="注册信息">
        <el-input v-model="student.registrationStatus" readonly></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'StudentHome',
  data() {
    return {
      student: {
        photo: '', // 初始为空，稍后从数据库模拟填充
        name: '',
        gender: '',
        birthDate: '',
        phoneNumber: '',
        studentId: '',
        dormInfo: '',
        registrationStatus: '',
      },
    };
  },
  mounted() {
    // 模拟从数据库获取数据
    setTimeout(() => {
      const mockData = {
        photo: 'https://via.placeholder.com/150', // 模拟的学生照片
        name: '张三',
        gender: '男',
        birthDate: '2000-01-01',
        phoneNumber: '1234567890',
        studentId: '20220101',
        dormInfo: '住校', // 模拟住校信息
        registrationStatus: '已注册', // 模拟注册信息
      };

      // 将模拟数据填充到 student 对象中
      this.student = mockData;
    }, 1000); // 模拟 1 秒的网络延迟
  },
};
</script>

<style scoped>
.student-form {
  max-width: 400px;
  margin: 0 auto; /* 居中 */
  background-color: #f9f9f9; /* 浅灰背景 */
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* 添加阴影 */
}
.student-photo {
  width: 150px;
  height: 150px;
  border-radius: 8px;
  object-fit: cover; /* 确保图片不变形 */
}
</style>
