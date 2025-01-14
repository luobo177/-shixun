<template>
    <div v-if="isLoading">正在加载通知，请稍后...</div>
    <div v-else>
        <h1>通知管理</h1>
        <button @click="AddButtonVisible">新建通知</button>
        <div v-if="isAddVisible">
            <AddNotification :isVisible="isAddVisible" @update:isVisible="isAddVisible = $event" @add="addNotification" />

        </div>
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
import NotificationModel from '@/components/NotificationModel.vue';
import AddNotification from '@/components/AddNotification.vue';
export default {
    name: 'AdminNotification',
    components: {
        NotificationModel,
        AddNotification,
    },
    data() {
        return {
            notification_list: [],// 初始化为空数组
            isModelVisible: false,//关联详细信息的显示
            isAddVisible: false,//关联新建通知的显示
            selectedNotification: null,
            isLoading: true, // 加载状态
            hoveredIndex: null, // 当前悬停的通知索引
        };
    },
    methods: {
        AddButtonVisible() {
            this.isAddVisible = true;
        },
        closeAddButton() {
            this.isAddVisible = false;
        },
        showDetails(notification) {
            this.selectedNotification = notification;
            this.isModelVisible = true;
        },
        closeModal() {
            this.selectedNotification = null;
            this.isModelVisible = false;
        },
        async deleteNotification(notification) {
            try {
                this.isLoading = true;
                const response = await axios.delete(`/api/admin/deleteNotification?id=${notification.id}`);
                console.log("删除成功", response.data);
                this.notification_list = this.notification_list.filter(
                    n => n.id !== notification.id
                );
            } catch (error) {
                console.error("删除失败", error);
            } finally {
                this.isLoading = false;
                this.closeModal();
            }
        },
        async get_notification() {
            this.isLoading = true; // 开始加载
            try {
                const response = await axios.get('/api/admin/notification');
                if(response.data.success){
                    console.log("获取通知成功", response.data);
                    this.notification_list = response.data.notifications || [];
                }else{
                    console.log("获取通知失败：", response.data.message);
                }
            } catch (error) {
                console.error("连接后端失败：", error);
            } finally {
                this.isLoading = false; // 加载完成
            }
        },
        async editNotification(notification) {
            try {
                const response = await axios.post(`/api/admin/editNotification`, notification);
                console.log("编辑成功", response.data);
                this.isModelVisible = false;
                this.get_notification(); // 编辑后重新获取数据
            } catch (error) {
                console.error("编辑失败", error);
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
