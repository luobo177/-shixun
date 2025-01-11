import { createRouter, createWebHistory } from 'vue-router';
import log from '@/views/log.vue'; // 登录页面
import AdminOverview from '@/views/AdminOverview.vue'; // 管理员页面
import AdminManagement from '@/views/AdminManagement.vue';
import AdminNotification from '@/views/AdminNotification.vue';
import LogManagement from '@/views/AdminLog.vue';
import StudentHome from '@/views/StudentHome.vue'; // 学生页面
<<<<<<< HEAD
import AdminLog from '@/views/AdminLog.vue';
=======
import ChangeMessage from '@/views/changemessage.vue'; // 信息修改页面
>>>>>>> e55dcdc20f9f870c0e166134de5fb5972f915210

const routes = [
    {
        path: '/', // 初始路径
        name: 'log',
        component: log,
    },
    {
        path:'/admin',
        component: ()=> import('@/components/AdminContainer.vue'),
        children:[
            {
                path: 'overview', // 管理员页面
                name: 'Adminoverview',
                component: AdminOverview,
            },
            {
                path: 'student-management',
                name: 'StudentManagement',
                component: AdminManagement,
            },
            {
                path:'notification-management',
                name: 'AdminNotification',
                component: AdminNotification,
            },
            {
                path:'Log-management',
                name:LogManagement,
                component:AdminLog,
            }
        ]
    },
    {
        path:'/student',
        component: ()=> import('@/components/StudentContainer.vue'),
        children:[
            {
                path: 'home', // 学生首页
                name: 'StudentHome',
                component: StudentHome,
            },
            {
                path: 'student-change', // 信息修改页面
                name: 'ChangeMessage',
                component: ChangeMessage,
            },
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
