import Vue from "vue";
import Router from "vue-router";
import Login from "@/views/Login";
import Inicio from "@/views/Inicio";
import TestDetail from "@/views/TestDetail";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [

    {
      path: "/",
      name: "Login",
      component: () => import("./views/Login")
    },
    {
      path: '/inicio',
      name: 'inicio',
      component: () => import("./views/Inicio"),
      meta: { requiresAuth: true }
    },
    {
      path: '/test/:id',
      name: 'test-detail',
      component: () => import("./views/TestDetail"),
      meta: { requiresAuth: true }
    }


  ]
});