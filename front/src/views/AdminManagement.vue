<template>
  <div class="student-management">
    <!-- 搜索功能 -->
    <div class="search-container">
      <input type="text" v-model="searchText" placeholder="请输入要查询的学生学号或姓名" class="search-input" />
      <button @click="searchStudent" class="search-button">搜索</button>
    </div>

    <!-- 学生列表展示 未完成 -->
    <table class="student-table">
      <thead>
        <tr>
          <th>学号</th>
          <th>姓名</th>
          <th>年龄</th>
          <th>班级</th>
          <th>性别</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in filterList.length > 0 ? filterList : OriginList" :key="student.id">
          <td>{{ student.id }}</td>
          <td>{{ student.name }}</td>
          <td>{{ student.age }}</td>
          <td>{{ student.class }}</td>
          <td>{{ student.gender }}</td>
          <td>
            <button @click="viewDetail(student)" class="detail-button">详情</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 子组件：学生详情弹窗 -->
    <AdminStudentDetail v-if="showDetail" :student="selectedStudent" @update-student="updateStudent"
      @delete-student="deleteStudent" @close="closeDetail" />
  </div>
</template>

<script>
import axios from "axios";
import AdminStudentDetail from "@/components/AdminStudentDetail.vue";

export default {
  data() {
    return {
      searchText: "", // 搜索关键字
      OriginList: [
        { id: 1001, name: "张三", age: 18, class: "高一(1)班", gender: "男" },
        { id: 1002, name: "李四", age: 17, class: "高一(2)班", gender: "女" },
        { id: 1003, name: "王五", age: 18, class: "高一(3)班", gender: "男" },
        { id: 1004, name: "赵六", age: 17, class: "高一(4)班", gender: "女" },
        { id: 1005, name: "孙七", age: 19, class: "高一(5)班", gender: "男" },
        { id: 1006, name: "周八", age: 18, class: "高一(6)班", gender: "男" },
        { id: 1007, name: "吴九", age: 16, class: "高一(7)班", gender: "女" },
        { id: 1008, name: "郑十", age: 17, class: "高一(8)班", gender: "男" },
        { id: 1009, name: "陈十一", age: 18, class: "高一(9)班", gender: "女" },
        { id: 1010, name: "朱十二", age: 17, class: "高一(10)班", gender: "男" },
      ],
      // 学生数据从后端获取
      filterList: [], // 筛选后的学生数据
      showDetail: false, // 是否显示详情弹窗
      selectedStudent: null, // 当前选中的学生
    };
  },
  components: {
    AdminStudentDetail,
  },
  methods: {
    // 初始化获取学生数据
    async fetchStudents() {
      try {
        const response = await axios.get("/api/admin/getStudents");
        if (response.data.success) {
          this.OriginList = response.data.data; // 从后端获取学生数据
        } else {
          console.error("获取学生数据失败");
        }
      } catch (error) {
        console.error("接口调用失败：", error);
      }
    },

    // 搜索学生
    searchStudent() {
      const searchValue = this.searchText.trim().toLowerCase();
      if (!searchValue) {
        this.filterList = [];
        return;
      }
      this.filterList = this.OriginList.filter(
        (student) =>
          student.id.toString().includes(searchValue) ||
          student.name.toLowerCase().includes(searchValue)
      );
    },

    // 查看学生详情
    viewDetail(student) {
      this.selectedStudent = student;
      this.showDetail = true;
    },

    // 关闭详情弹窗
    closeDetail() {
      this.showDetail = false;
      this.selectedStudent = null;
    },

    // 更新学生信息
    async updateStudent(updatedStudent) {
      try {
        const response = await axios.post("/api/admin/updateStudent", {
          student: updatedStudent,
        });
        if (response.data.success) {
          const index = this.OriginList.findIndex(
            (student) => student.id === updatedStudent.id
          );
          if (index !== -1) {
            this.OriginList.splice(index, 1, updatedStudent); // 更新本地数据
          }
          this.closeDetail();
        } else {
          console.error("更新学生信息失败");
        }
      } catch (error) {
        console.error("接口调用失败：", error);
      }
    },

    // 删除学生信息
    async deleteStudent(studentId) {
      try {
        const response = await axios.post("/api/admin/deleteStudent", {
          id: studentId,
        });
        if (response.data.success) {
          this.OriginList = this.OriginList.filter(
            (student) => student.id !== studentId
          ); // 删除本地数据
          this.closeDetail();
        } else {
          console.error("删除学生信息失败");
        }
      } catch (error) {
        console.error("接口调用失败：", error);
      }
    },
  },
  mounted() {
    this.fetchStudents(); // 初始化获取学生数据
  },
};
</script>


<style scoped>
.student-management {
  margin: 20px;
}

.search-container {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

.search-input {
  flex: 1;
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.search-button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.search-button:hover {
  background-color: #0056b3;
}

.student-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.student-table th,
.student-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

.student-table th {
  background-color: #f4f4f4;
}

.detail-button {
  padding: 5px 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.detail-button:hover {
  background-color: #218838;
}
</style>