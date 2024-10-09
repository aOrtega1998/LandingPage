import Vue from "vue";
import VueRouter from "vue-router";

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

Vue.use(VueRouter);

export function createRouter(routes) {
  const router = new VueRouter({
    routes,
    mode: "history",
    scrollBehavior(to, from, savedPosition) {
      if (savedPosition) {
        return savedPosition;
      }
      if (to.hash) {
        return {
          selector: to.hash,
        };
      }
    },
  });

  router.afterEach(() => {
    window.scrollTo(0, 0);
  });

  return router;
}