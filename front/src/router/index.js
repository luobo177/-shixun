import { createRouter, createWebHistory } from 'vue-router';
import log from '@/views/log.vue'; // 登录页面
import AdminHome from '@/views/AdminHome.vue'; // 管理员页面
import StudentHome from '@/views/StudentHome.vue'; // 学生页面

const routes = [
    {
        path: '/', // 初始路径
        name: 'log',
        component: log,
    },
    {
        path: '/admin/home', // 管理员页面
        name: 'AdminHome',
        component: AdminHome,
    },
    {
        path: '/student/home', // 学生页面
        name: 'StudentHome',
        component: StudentHome,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
