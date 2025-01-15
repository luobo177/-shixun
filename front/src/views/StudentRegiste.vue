<template>
  <div class="container">
    <!-- 显示加载状态 -->
    <div v-if="isLoading" class="loading">正在加载，请稍后...</div>

    <!-- 注册页面 -->
    <div v-else>
      <h1 class="title">学生签到</h1>

      <!-- 注册成功提示 -->
      <div v-if="isRegistered" class="registration-success">
        <p>{{ successMessage }}</p>
      </div>

      <!-- 身份证输入表单 -->
      <div v-else>
        <div class="input-container">
          <label for="idCard">请输入身份证号：</label>
          <input
            id="idCard"
            type="text"
            v-model="idCard"
            class="input-field"
            placeholder="请输入身份证号"
          />
        </div>

        <button @click="register" class="btn btn-register">签到</button>
      </div>

      <!-- 未查询到身份证号的提示框 -->
      <div v-if="isDialogVisible" class="modal">
        <div class="modal-content">
          <h2>{{ dialogMessage }}</h2>
          <div class="modal-actions">
            <button class="btn btn-retry" @click="retryInput">重新输入</button>
            <button class="btn btn-exit" @click="exitRegistration">退出</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'StudentRegiste',
  data() {
    return {
      idCard: '', // 存储输入的身份证号
      isLoading: false, // 是否正在加载
      isDialogVisible: false, // 是否显示提示框
      isRegistered: false, // 是否已成功注册
      successMessage: '', // 签到成功提示信息
      dialogMessage: '', // 对话框提示信息
    };
  },
  methods: {
    // 处理注册请求
    async register() {
      if (!this.idCard) {
        alert('请输入身份证号！');
        return;
      }

      this.isLoading = true; // 显示加载状态
      try {
        // 向后台请求验证身份证号
        const response = await axios.post('/api/student/validate-id', { idCard: this.idCard });

        if (response.data.exists) {
          if (response.data.registrationStatus === '未签到') {
            // 更新签到状态
            const updateResponse = await axios.post('/api/student/update-registration', { idCard: this.idCard });
            if (updateResponse.data.success) {
              this.successMessage = '签到成功！';
              this.isRegistered = true; // 显示成功提示
            } else {
              alert('签到更新失败，请稍后再试。');
            }
          } else {
            this.successMessage = '您已签到，无需重复签到。';
            this.isRegistered = true; // 显示已签到提示
          }
        } else {
          this.dialogMessage = '未找到对应身份证信息';
          this.isDialogVisible = true; // 显示提示框
        }
      } catch (error) {
        console.error('注册请求失败:', error);
        alert('请求失败，请稍后再试。');
      } finally {
        this.isLoading = false; // 请求完成后关闭加载状态
      }
    },
    // 重新输入身份证号
    retryInput() {
      this.isDialogVisible = false;
      this.idCard = ''; // 清空输入框
    },
    // 退出注册，返回上一页面
    exitRegistration() {
      this.isDialogVisible = false;
      this.$router.go(-1); // 使用 Vue Router 返回上一页面
    },
  },
};
</script>

<style scoped>
/* 整体布局 */
.container {
  font-family: 'Arial', sans-serif;
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* 页面标题 */
.title {
  font-size: 28px;
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

/* 注册成功提示 */
.registration-success {
  color: #4caf50;
  font-size: 18px;
  text-align: center;
  margin-bottom: 20px;
}

/* 输入框容器 */
.input-container {
  margin-bottom: 20px;
}

/* 输入框样式 */
.input-field {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 16px;
  margin-top: 10px;
  background-color: #f9f9f9;
  transition: all 0.3s ease;
}

.input-field:focus {
  border-color: #1890ff;
  background-color: #fff;
  outline: none;
}

/* 按钮样式 */
.btn {
  width: 100%;
  padding: 14px;
  font-size: 16px;
  border-radius: 8px;
  cursor: pointer;
  border: none;
  transition: background-color 0.3s ease;
}

.btn-register {
  background-color: #1890ff;
  color: white;
}

.btn-register:hover {
  background-color: #1274d2;
}

/* 模态框 */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.modal-content {
  background: #fff;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
  width: 300px;
  text-align: center;
}

.modal-actions {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

/* 重新输入按钮 */
.btn-retry {
  background-color: #f0ad4e;
  color: white;
}

.btn-retry:hover {
  background-color: #ec9c2f;
}

/* 退出按钮 */
.btn-exit {
  background-color: #d9534f;
  color: white;
}

.btn-exit:hover {
  background-color: #c9302c;
}

/* 加载状态 */
.loading {
  text-align: center;
  font-size: 18px;
  color: #777;
}
</style>
