<template>
    <div class="admin-log">
        <h1>日志管理</h1>
        <!-- 搜索框 -->
        <div class="search-bar">
            <input type="text" v-model="searchQuery" placeholder="搜索日志内容" @input="filterLogs" />
        </div>
        <!-- 日志列表 -->
        <div v-if="currentLogs.length > 0" class="log-list">
            <div v-for="log in currentLogs" :key="log.id" class="log-container">
                <p class="log-content">{{ log.content }}</p>
                <p class="log-timestamp">{{ log.timestamp }}</p>
            </div>
        </div>
        <!-- 无日志时的提示 -->
        <div v-else class="no-logs">
            暂无匹配的日志
        </div>
    </div>
</template>


<script>
import axios from 'axios';

export default {
    name: 'AdminLog',
    data() {
        return {
            logs: [
                { id: 1, content: "用户A成功登录系统。", timestamp: "2025-01-11 08:30:00" },
                { id: 2, content: "系统检测到异常访问，IP: 192.168.1.100。", timestamp: "2025-01-11 09:00:00" },
                { id: 3, content: "管理员B修改了权限设置。", timestamp: "2025-01-10 15:45:00" },
                { id: 4, content: "定时任务执行成功，生成报表文件。", timestamp: "2025-01-10 18:00:00" },
                { id: 5, content: "系统更新完成，版本升级到v2.1.4。", timestamp: "2025-01-09 22:30:00" },
                { id: 6, content: "用户A成功登录系统。", timestamp: "2025-01-11 08:30:00" },
                { id: 7, content: "系统检测到异常访问，IP: 192.168.1.100。", timestamp: "2025-01-11 09:00:00" },
                { id: 8, content: "管理员B修改了权限设置。", timestamp: "2025-01-10 15:45:00" },
                { id: 9, content: "定时任务执行成功，生成报表文件。", timestamp: "2025-01-10 18:00:00" },
                { id: 10, content: "系统更新完成，版本升级到v2.1.4。", timestamp: "2025-01-09 22:30:00" },
                { id: 11, content: "用户C访问了资源并下载报告。", timestamp: "2025-01-11 10:00:00" },
                { id: 12, content: "系统出现错误，导致请求超时，IP: 192.168.2.50。", timestamp: "2025-01-11 11:30:00" },
                { id: 13, content: "管理员C删除了不必要的日志记录。", timestamp: "2025-01-10 13:00:00" },
                { id: 14, content: "成功生成月度销售报告并发送至管理邮箱。", timestamp: "2025-01-10 16:45:00" },
                { id: 15, content: "用户A请求重置密码，并成功修改。", timestamp: "2025-01-09 19:30:00" },
                { id: 16, content: "系统自动清理缓存数据。", timestamp: "2025-01-11 12:30:00" },
                { id: 17, content: "管理员B为用户D分配了新的权限。", timestamp: "2025-01-11 14:00:00" },
                { id: 18, content: "用户D成功登录并修改了个人信息。", timestamp: "2025-01-11 14:30:00" },
                { id: 19, content: "系统启动并检测到新版本的可用更新。", timestamp: "2025-01-11 15:00:00" },
                { id: 20, content: "管理员A成功清除系统中的冗余数据。", timestamp: "2025-01-11 15:30:00" },
            ],
            filteredLogs: [], // 筛选后的日志数据
            searchQuery: '' // 搜索框输入内容
        };
    },
    computed: {
        currentLogs() {
            // 根据搜索状态返回显示的日志
            return this.searchQuery.trim()
                ? this.filteredLogs // 如果有搜索内容，显示筛选结果
                : this.logs; // 如果没有搜索内容，显示全部日志
        }
    },
    methods: {
        async get_logs() {
            try {
                const response = await axios.get('/api/admin/getLogs');
                if (response.data.success) {
                    this.logs = response.data.logs;
                    this.filteredLogs = this.logs; // 初始化筛选结果
                    console.log("请求日志成功", this.logs);
                } else {
                    console.error("请求日志失败");
                }
            } catch (error) {
                console.error("请求日志时发生错误:", error);
            }
        },
        filterLogs() {
            // 根据搜索内容实时筛选日志
            const query = this.searchQuery.trim().toLowerCase();
            this.filteredLogs = this.logs.filter(log =>
                log.content.toLowerCase().includes(query)
            );
        }
    },
    mounted() {
        this.get_logs();
    }
};
</script>


<style scoped>
.admin-log {
    padding: 10px;
    max-width: 1000px;
    /* 增大最大宽度 */
    margin: 0 auto;
    background-color: #f9f9f9;
}

.search-bar {
    margin-bottom: 15px;
    text-align: center;
}

.search-bar input {
    width: 100%;
    padding: 8px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.log-list {
    display: flex;
    flex-direction: column;
    gap: 10px;
}

/* 日志容器 */
.log-container {
    background-color: #fff;
    border: 1px solid #ddd;
    padding: 8px;
    border-radius: 6px;
    margin: 5px 0;
    font-size: 13px;
    line-height: 1.3;
    transition: background-color 0.3s ease;
    cursor: pointer;
    max-width: 100%;
    /* 宽度自适应 */
    word-wrap: break-word;
    /* 让内容自动换行 */
    overflow: hidden;
    /* 防止内容超出 */
}

/* 鼠标悬停时的效果 */
.log-container:hover {
    background-color: #f0f0f0;
    border-color: #007bff;
}

/* 日志内容 */
.log-container p {
    margin: 0;
    color: #333;
}

/* 时间戳 */
.log-timestamp {
    font-size: 13px;
    color: #777;
    margin-top: 6px;
}

.no-logs {
    text-align: center;
    color: #777;
    font-size: 15px;
    margin-top: 15px;
}
</style>
