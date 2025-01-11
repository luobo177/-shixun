<template>
    <div v-if="isVisible" class="modal-overlay">
      <div class="modal-content">
        <h2>添加通知</h2>
        <form @submit.prevent="addNotification">
          <div class="form-group">
            <label for="title">标题</label>
            <input type="text" id="title" v-model="notification.title" placeholder="输入通知标题" required />
          </div>
  
          <div class="form-group">
            <label for="content">内容</label>
            <textarea id="content" v-model="notification.content" placeholder="输入通知内容" required></textarea>
          </div>
  
          <div class="form-group">
            <button type="submit" class="btn btn-primary">提交</button>
          </div>
        </form>
  
        <!-- 提交成功提示 -->
        <div v-if="isSuccess" class="success-message">
          <p>通知添加成功！</p>
        </div>
  
        <!-- 错误提示 -->
        <div v-if="isError" class="error-message">
          <p>添加通知失败，请稍后再试。</p>
        </div>
  
        <!-- 关闭按钮 -->
        <button class="close-btn" @click="closeModal">×</button>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    props: {
      isVisible: {
        type: Boolean,
        required: true,
      }
    },
    data() {
      return {
        notification: {
          title: '',
          content: '',
        },
        isSuccess: false,
        isError: false,
      };
    },
    methods: {
      async addNotification() {
        try {
          const response = await axios.post('/api/addNotification', this.notification);
          if (response.data.success) {
            this.isSuccess = true;
            this.isError = false;
            this.resetForm();
          }
        } catch (error) {
          this.isError = true;
          this.isSuccess = false;
          console.error('添加通知失败', error);
        }
      },
      resetForm() {
        this.notification.title = '';
        this.notification.content = '';
      },
      closeModal() {
        this.$emit('update:isVisible', false); // 触发父组件的事件，关闭弹框
      },
    },
  };
  </script>
  
  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.4); /* 更柔和的遮罩层 */
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000; /* 使弹框显示在最上层 */
    transition: background 0.3s ease;
  }
  
  .modal-content {
    background: #fff;
    padding: 30px;
    max-width: 500px;
    width: 100%;
    border-radius: 12px; /* 更圆滑的边角 */
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* 增加阴影 */
    position: relative;
    animation: slideIn 0.3s ease-out;
  }
  
  @keyframes slideIn {
    from {
      opacity: 0;
      transform: translateY(-30px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
  
  h2 {
    font-size: 22px;
    font-weight: bold;
    margin-bottom: 20px;
    color: #333;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
    font-size: 14px;
    color: #555;
  }
  
  input,
  textarea {
    width: 100%;
    padding: 12px;
    font-size: 16px;
    border-radius: 6px;
    border: 1px solid #ddd;
    box-sizing: border-box;
    transition: border-color 0.3s ease;
  }
  
  input:focus,
  textarea:focus {
    border-color: #007bff;
  }
  
  button {
    background-color: #4caf50;
    color: white;
    padding: 12px 24px;
    border: none;
    border-radius: 6px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #45a049;
  }
  
  .success-message {
    color: green;
    font-size: 16px;
    margin-top: 20px;
  }
  
  .error-message {
    color: red;
    font-size: 16px;
    margin-top: 20px;
  }
  
  .close-btn {
    position: absolute;
    top: 10px;
    right: 10px;
    background-color: transparent;
    border: none;
    font-size: 30px;
    color: #888;
    cursor: pointer;
    transition: color 0.3s ease;
  }
  
  .close-btn:hover {
    color: #e74c3c;
  }
  </style>
  