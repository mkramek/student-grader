import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/style.css'
import 'vue-toastification/dist/index.css'
import Toast, { PluginOptions, POSITION } from "vue-toastification";

const toastOptions: PluginOptions = {
    timeout: 2500,
    position: POSITION.TOP_RIGHT
}

createApp(App).use(router).use(Toast, toastOptions).mount('#app')
