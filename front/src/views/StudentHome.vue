<template>
    <el-container style="height: 100vh;">
      <!-- 头部 -->
      <el-header class="header">
        <div class="header-content">
          <span class="header-title">学生个人信息</span>
          <el-button type="text" class="logout-button" @click="handleLogout">注销</el-button>
        </div>
      </el-header>
  
      <!-- 主体 -->
      <el-container>
        <!-- 侧边栏 -->
        <el-aside width="200px" class="aside">
          <el-menu :default-active="activeMenu" @select="handleMenuSelect" class="custom-menu">
            <el-menu-item index="overview">
              <i class="el-icon-house"></i> 总览
            </el-menu-item>
            <el-menu-item index="student-info">
              <i class="el-icon-user"></i> 个人信息
            </el-menu-item>
          </el-menu>
        </el-aside>
  
        <!-- 内容区域 -->
        <el-main class="main-content">
          <!-- 学生信息展示区域 -->
          <div class="student-info">
            <el-row>
              <el-col :span="6">
                <el-card>
                  <img :src="student.photo" alt="Student Photo" class="student-photo"/>
                </el-card>
              </el-col>
              <el-col :span="18">
                <el-table :data="[student]" style="width: 100%">
                  <el-table-column label="姓名" prop="name"></el-table-column>
                  <el-table-column label="性别" prop="gender"></el-table-column>
                  <el-table-column label="学号" prop="studentId"></el-table-column>
                  <el-table-column label="生日" prop="birthday"></el-table-column>
                  <el-table-column label="是否住宿" prop="isDormitory" :formatter="formatBoolean"></el-table-column>
                  <el-table-column label="新生注册" prop="isRegistered" :formatter="formatBoolean"></el-table-column>
                  <el-table-column label="电话号码" prop="phone"></el-table-column>
                </el-table>
              </el-col>
            </el-row>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </template>
  
  <script>
  export default {
    name: 'StudentPage',
    data() {
      return {
        activeMenu: 'student-info',
        student: {
          name: '张三',
          gender: '男',
          studentId: '20250123',
          birthday: '2000-01-01',
          isDormitory: true,
          isRegistered: true,
          phone: '13800138000',
          photo: 'https://via.placeholder.com/150' // 假设的学生照片URL
        }
      };
    },
    methods: {
      handleMenuSelect(index) {
        this.activeMenu = index;
        this.$router.push(`/${index}`);
      },
      handleLogout() {
        // 清除登录信息并跳转到根目录
        localStorage.removeItem('token');
        this.$router.push('/');
      },
      formatBoolean(value) {
        return value ? '是' : '否';
      }
    }
  };
  </script>
  
  <style scoped>
  /* 头部样式 */
  .header {
    background-color: #343a40; /* 深色背景 */
    color: #ffffff; /* 白色字体 */
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 20px;
    height: 60px;
  }
  .header-content {
    display: flex;
    align-items: center;
    width: 100%;
  }
  .header-title {
    font-size: 18px;
    font-weight: 500;
  }
  .logout-button {
    color: #ffffff;
    font-size: 14px;
    font-weight: 400;
    margin-left: auto;
  }
  .logout-button:hover {
    color: #e57373; /* 鼠标悬停时的颜色 */
  }
  
  /* 简约风格：侧边栏整体样式 */
  .aside {
    background-color: #f8f9fa; /* 浅色背景 */
    color: #343a40; /* 深灰字体 */
    height: 100%; /* 全高 */
    border-right: 1px solid #dee2e6; /* 右边框 */
    box-shadow: 1px 0 5px rgba(0, 0, 0, 0.1); /* 添加轻微阴影 */
  }
  
  /* 自定义菜单样式 */
  .custom-menu {
    background-color: transparent; /* 菜单背景透明 */
    border: none; /* 移除菜单默认边框 */
  }
  
  /* 菜单项样式 */
  .custom-menu .el-menu-item {
    color: #495057; /* 未选中时的字体颜色 */
    font-size: 14px; /* 字体大小 */
    padding: 12px 20px; /* 内边距 */
    border-radius: 4px; /* 圆角 */
    transition: all 0.2s ease-in-out; /* 添加过渡动画 */
  }
  
  /* 选中菜单项的高亮样式 */
  .custom-menu .el-menu-item.is-active {
    background-color: #e9ecef; /* 激活状态的背景色 */
    color: #212529; /* 激活状态的字体颜色 */
    font-weight: 500; /* 加粗选中项 */
  }
  
  /* 鼠标悬停样式 */
  .custom-menu .el-menu-item:hover {
    background-color: #f1f3f5; /* 悬停时背景色 */
    color: #212529; /* 悬停时字体颜色 */
    cursor: pointer; /* 鼠标变为手形 */
  }
  
  /* 图标样式 */
  .custom-menu .el-icon-house,
  .custom-menu .el-icon-user {
    margin-right: 10px; /* 图标与文字的间距 */
    font-size: 16px; /* 图标大小 */
    color: #868e96; /* 图标颜色 */
  }
  
  /* 内容区域样式 */
  .main-content {
    background-color: #ffffff;
    padding: 20px;
    height: 100%;
    overflow-y: auto;
  }
  
  /* 学生信息表格样式 */
  .student-info {
    padding: 20px;
  }
  
  /* 学生照片样式 */
  .student-photo {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    margin: 20px auto;
  }
  
  </style>
  