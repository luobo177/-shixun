<template>
  <div class="modal">
    <h3>学生信息详情</h3>
    <div class="form-container">
      <label>
        学号：
        <input v-model="editStudentData.id" disabled />
      </label>
      <label>
        姓名：
        <input v-model="editStudentData.name" disabled />
      </label>
      <label>
        电话：
        <input v-model="editStudentData.phoneNumber" />
      </label>
      <label>
        性别：
        <input v-model="editStudentData.gender" disabled />
      </label>
      <label>
        专业：
        <input v-model="editStudentData.major" disabled />
      </label>
      <label>
        报到状态：
        <input v-model="editStudentData.registrationStatus" disabled />
      </label>
      <label>
        年龄：
        <input v-model="editStudentData.age" disabled />
      </label>
    </div>

    <div class="button-group">
      <button @click="saveEdit" class="save-button">保存</button>
      <button @click="deleteStudent" class="delete-button">删除</button>
      <button @click="closeModal" class="cancel-button">取消</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    student: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      editStudentData: { ...this.student }, // 用于编辑的学生信息副本
    };
  },
  methods: {
    // 保存修改
    async saveEdit() {
      try {
        const response = await axios.post("/api/admin/updateStudent", {
          student: this.editStudentData,
        });
        if (response.data.success) {
          this.$emit("update-student", this.editStudentData); // 通知父组件更新
          this.closeModal();
        } else {
          alert("保存失败，请稍后重试！");
        }
      } catch (error) {
        console.error("同步到后端失败：", error);
        alert("保存失败，请检查网络连接！");
      }
    },

    // 删除学生
    deleteStudent() {
      if (confirm("确定要删除此学生信息吗？")) {
        this.$emit("delete-student", this.editStudentData.id); // 通知父组件删除
        this.closeModal();
      }
    },

    // 关闭弹窗
    closeModal() {
      this.$emit("close"); // 通知父组件关闭弹窗
    },
  },
};
</script>

<style scoped>
.modal {
  position: fixed;
  top: 20%;
  left: 50%;
  transform: translate(-50%, -20%);
  background: white;
  padding: 20px 30px;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  width: 400px;
}

h3 {
  text-align: center;
  margin-bottom: 20px;
}

.form-container {
  display: grid;
  grid-template-columns: 1fr;
  gap: 15px;
}

label {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  color: #333;
}

input {
  flex: 1;
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-left: 10px;
}

input:disabled {
  background-color: #f9f9f9;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

button {
  flex: 1;
  margin: 0 5px;
  padding: 10px;
  font-size: 14px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.save-button {
  background-color: #28a745;
  color: white;
}

.save-button:hover {
  background-color: #218838;
}

.delete-button {
  background-color: #dc3545;
  color: white;
}

.delete-button:hover {
  background-color: #c82333;
}

.cancel-button {
  background-color: #6c757d;
  color: white;
}

.cancel-button:hover {
  background-color: #5a6268;
}
</style>
