<template>
  <div>
    <el-form label-position="top" label-width="120px" class="student-form" :model="student">
      <!-- 学生照片 -->
      <el-form-item label="照片">
        <img 
          :src="student.photo" 
          alt="学生照片" 
          class="student-photo" 
        />
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/" 
          :on-success="handlePhotoUpload"
          :show-file-list="false"
        >
          <el-button size="small" type="primary">上传照片</el-button>
        </el-upload>
      </el-form-item>

      <!-- 学生姓名 -->
      <el-form-item label="姓名">
        <el-input v-model="student.name"></el-input>
      </el-form-item>

      <!-- 学生性别 -->
      <el-form-item label="性别">
        <el-select v-model="student.gender" placeholder="请选择性别">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>

      <!-- 出生日期 -->
      <el-form-item label="出生日期">
        <el-date-picker v-model="student.birthDate" type="date" placeholder="选择出生日期"></el-date-picker>
      </el-form-item>

      <!-- 电话号码 -->
      <el-form-item label="电话号码">
        <el-input v-model="student.phoneNumber"></el-input>
      </el-form-item>

      <!-- 学号 -->
      <el-form-item label="学号">
        <el-input v-model="student.studentId"></el-input>
      </el-form-item>

      <!-- 住校信息 -->
      <el-form-item label="住校情况">
        <el-select v-model="student.dormInfo" placeholder="请选择住校情况">
          <el-option label="住校" value="住校"></el-option>
          <el-option label="走读" value="走读"></el-option>
        </el-select>
      </el-form-item>

      <!-- 注册信息 -->
      <el-form-item label="注册信息">
        <el-select v-model="student.registrationStatus" placeholder="请选择注册状态">
          <el-option label="已注册" value="已注册"></el-option>
          <el-option label="未注册" value="未注册"></el-option>
        </el-select>
      </el-form-item>

      <!-- 提交按钮 -->
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ElUpload, ElButton, ElSelect, ElOption, ElDatePicker, ElInput, ElForm, ElFormItem } from 'element-plus';

export default {
  name: 'StudentMessageChange',
  components: {
    ElForm,
    ElFormItem,
    ElInput,
    ElButton,
    ElSelect,
    ElOption,
    ElDatePicker,
    ElUpload
  },
  data() {
    return {
      student: {
        photo: 'https://via.placeholder.com/150', // 默认照片
        name: '张三',
        gender: '男',
        birthDate: '2000-01-01',
        phoneNumber: '1234567890',
        studentId: '20220101',
        dormInfo: '住校', // 或 "走读"
        registrationStatus: '已注册', // 或 "未注册"
      }
    };
  },
  methods: {
  // 模拟处理照片上传
  handlePhotoUpload(response, file) {
    // 这里可以根据后端返回的 URL 更新学生照片
    this.student.photo = file.url || 'https://via.placeholder.com/150'; // 使用上传返回的 URL
  },
  // 提交表单
  submitForm() {
    console.log('提交的学生信息：', this.student);
    // 在这里可以将数据发送到后端 API
  }
}
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
  margin-bottom: 10px;
}
</style>
