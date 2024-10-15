import Vue from "vue";
import Router from "vue-router";
import Login from "@/views/Login";
import Inicio from "@/views/Inicio";
import TestDetail from "@/views/TestDetail";
import Admin from "@/views/Admin";

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
      name: 'Inicio',
      component: () => import("./views/Inicio"),
    },
    {
      path: '/test/:id',
      name: 'test-detail',
      component: () => import("./views/TestDetail"),
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import("./views/Admin"),
    },


  ]
});