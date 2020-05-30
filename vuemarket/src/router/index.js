import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "../views/Login";
import Login_index from "../views/Login_index";
import register from "../views/register";
import App from "../App";
import Index from "../views/Index";
import UserShow from "../views/UserShow";
import Admin from "../views/Admin";
import Admin_index from "../views/Admin_index";
import Admin_add from "../views/Admin_add";
import userState from "../views/userState";
import userBuy from "../views/userBuy";
import userSend from "../views/userSend";
import userFinal from "../views/userFinal";
import Admin_user from "../views/Admin_user";
Vue.use(VueRouter)

  const routes = [
    {
      path: '/',
      name:'登录',
      component: Login,
      show:false,
      show2:true,
      show3:false,
      redirect:"Login_index",
      children: [
        {
          path: '/Login_index',
          name:'登录',
          component: Login_index
        },
        {
          path: '/register',
          name:'注册',
          component: register
        }
      ]
    },
  {
    path: '/Index',
    name: '数据',
    component: Index,
    show:true,
    show2:false,
    show3:false,
    redirect:"Home",
    children:[
      {
        path:'/Home',
        name:"浏览商品",
        component: Home
      },
      {
        path:'/UserShow',
        name:"我的物品",
        component: UserShow
      },
      {
        path:'/userState',
        name:"审核中的物品",
        component: userState
      },
      {
        path: '/userBuy',
        name: '我购买的物品',
        component: userBuy
      },
      {
        path: '/userSend',
        name: '我出售的物品',
        component: userSend
      },
      {
        path:'/userFinal',
        name:'交易完成的物品',
        component: userFinal
      }
    ]
  },
    {
      path: '/Admin_index',
      name:'管理员',
      component: Admin_index,
      redirect: "Admin",
      show:false,
      show2:false,
      show3:true,
      children: [
        {
          path:"/Admin",
          name:"查看商品",
          component: Admin
        },
        {
          path:'/Admin_add',
          name: '待审核商品',
          component: Admin_add
        },
        {
          path:'/Admin_user',
          name: '用户信息',
          component: Admin_user,
        }
      ]
    }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
