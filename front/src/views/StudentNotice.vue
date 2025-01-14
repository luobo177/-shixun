<template>
    <div v-if="isLoading">正在加载通知，请稍后...</div>
    <div v-else>
        <h1>通知管理</h1>

        <div v-if="notification_list.length === 0" class="no-notification">
            暂无通知
        </div>
        <div v-else class="notification-container">
            <div v-for="(notification, index) in notification_list" :key="index" class="notification-item"
                @mouseenter="hoverNotification(index)" @mouseleave="hoverNotification(null)"
                :class="{ 'highlight': hoveredIndex === index }">
                <span @click="showDetails(notification)">{{ notification.title }}</span>
            </div>
        </div>

        <NotificationModel :visible="isModelVisible" :notification="selectedNotification" @close="closeModal"
            @delete="deleteNotification" @edit="editNotification" />
    </div>
</template>

<script>
import axios from 'axios';
import NotificationModel from '@/components/StudentNotification.vue';

export default {
    name: 'StudentNotice',
    components: {
        NotificationModel,
    },
    data() {
        return {
            notification_list: [
                {
                    id: 1,
                    title: "系统更新通知",
                    content: "系统将于今晚23:00-01:00进行维护，请提前保存好数据。",
                    timestamp: "2025-01-11 10:00:00",
                },
                {
                    id: 2,
                    title: "账户安全提醒",
                    content: "检测到您的账户在异地登录，请确认是否为本人操作。",
                    timestamp: "2025-01-10 16:45:00",
                },
                {
                    id: 3,
                    title: "节日福利活动",
                    content: "春节即将到来，登录平台领取您的专属新年礼物！",
                    timestamp: "2025-01-09 09:30:00",
                },
                {
                    id: 4,
                    title: "产品更新发布",
                    content: "我们推出了新功能，快来体验全新的工作流程吧！",
                    timestamp: "2025-01-08 14:20:00",
                },
                {
                    id: 5,
                    title: "新用户指南",
                    content: "欢迎使用本平台！点击查看新手引导，快速上手操作。",
                    timestamp: "2025-01-07 11:15:00",
                },
            ], // 初始化为空数组
            isModelVisible: false, // 关联详细信息的显示
            selectedNotification: null,
            isLoading: true, // 加载状态
            hoveredIndex: null, // 当前悬停的通知索引
        };
    },
    methods: {
        showDetails(notification) {
            this.selectedNotification = notification;
            this.isModelVisible = true;
        },
        closeModal() {
            this.selectedNotification = null;
            this.isModelVisible = false;
        },
        async get_notification() {
            this.isLoading = true; // 开始加载
            try {
                const response = await axios.get('/api/student/student-notice');
                this.notification_list = response.data.notifications || [];
            } catch (error) {
                console.error("获取通知失败：", error);
            } finally {
                this.isLoading = false; // 加载完成
            }
        },
        hoverNotification(index) {
            this.hoveredIndex = index; // 记录悬停的索引
        }
    },
    mounted() {
        this.get_notification(); // 初始化时获取通知
    },
};
</script>

<style scoped>
/* 通知容器 */
.notification-container {
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    gap: 10px;
    /* 每个通知之间的间距 */
}

/* 单个通知项 */
.notification-item {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
    transition: all 0.3s ease;
    cursor: pointer;
}

/* 鼠标悬停高亮 */
.notification-item.highlight {
    background-color: #e8f4fc;
    /* 高亮背景色 */
    text-decoration: underline;
    /* 下划线 */
    border-color: #007bff;
    /* 边框颜色变化 */
}

/* 标题 */
.notification-item span {
    font-size: 16px;
    font-weight: 600;
    color: #333;
}

.notification-item span:hover {
    color: #007bff;
    /* 鼠标悬停时文字颜色 */
}

/* 无通知提示 */
.no-notification {
    color: #777;
    font-size: 14px;
    margin-top: 20px;
}
</style>
