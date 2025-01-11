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
                { id: 5, content: "系统更新完成，版本升级到v2.1.4。", timestamp: "2025-01-09 22:30:00" }
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
                const response = await axios.get('/api/getLogs');
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
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
}

.search-bar {
    margin-bottom: 20px;
    text-align: center;
}

.search-bar input {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.log-list {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.log-container {
    padding: 15px;
    background: #f9f9f9;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s;
}

.log-container:hover {
    transform: translateY(-3px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.log-content {
    font-size: 16px;
    font-weight: 600;
    color: #333;
}

.log-timestamp {
    font-size: 14px;
    color: #777;
    margin-top: 8px;
}

.no-logs {
    text-align: center;
    color: #777;
    font-size: 16px;
    margin-top: 20px;
}
</style>
