import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import axios from 'axios';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';



const app=createApp(App);
app.use(ElementPlus);
app.use(router);
app.config.globalProperties.$axios = axios;
axios.defaults.baseURL='http://localhost:8112'
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}

app.mount('#app');
