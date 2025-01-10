import { createRouter, createWebHistory } from 'vue-router';
import log from '@/views/log.vue'; // 登录页面
import AdminOverview from '@/views/AdminOverview.vue'; // 管理员页面
import AdminManagement from '@/views/AdminManagement.vue';
import AdminNotification from '@/views/AdminNotification.vue';
import StudentHome from '@/views/StudentHome.vue'; // 学生页面

const routes = [
    {
        path: '/', // 初始路径
        name: 'log',
        component: log,
    },
    {
        path:'/admin',
        component:()=> import('@/components/AdminContainer.vue'),
        children:[
            {
                path: 'overview', // 管理员页面
                name: 'Adminoverview',
                component: AdminOverview,
            },
            {
                path: 'student-management',
                name: 'studentManagement',
                component: AdminManagement,
            },
            {
                path:'notification-management',
                name: 'AdminNotification',
                component:AdminNotification,
            },
        ]
    },
    {
        path:'/student',
        component:()=> import('@/components/AdminContainer.vue'),
        children:[
            {
                path: '/student/home', // 学生页面
                name: 'StudentHome',
                component: StudentHome,
            },
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
