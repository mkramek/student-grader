import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Main from '../views/MainPage.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        component: Main
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
