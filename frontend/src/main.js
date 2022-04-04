import Vue from "vue";
import "./plugins/axios";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import { i18n } from "@/plugins/i18n"
import VueSweetalert2 from 'vue-sweetalert2'
import VuetifyDialog from "vuetify-dialog";
import VueHighlightJS from "vue-highlightjs";
import "vuetify/dist/vuetify.min.css";
import "material-design-icons-iconfont/dist/material-design-icons.css";
import "./assets/GlobalStyles.css"

Vue.config.productionTip = false;
Vue.use(VueSweetalert2)
Vue.use(VuetifyDialog);
Vue.use(VueHighlightJS);



router.afterEach((to, from) => {
  Vue.nextTick(() => {
    document.title = 'Landing Page'
  })
})

new Vue({
  router,
  store,
  vuetify,
  i18n,
  render: h => h(App)
}).$mount("#app");
