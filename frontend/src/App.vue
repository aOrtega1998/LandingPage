<template>
  <v-app>
    <div class="app-container">
      <NavBar></NavBar>
      <main class="main-content">
        <router-view></router-view>
      </main>
    </div>
  </v-app>
</template>

<script>
import NavBar from "@/components/NavBar";
export default {
  name: 'App',
  components:{
    //Footer,
    NavBar
  },
   data: ()=> ({
   })
}

</script>
<style>
html, body, #app, .app-container {
  height: 100%;
  margin: 0;
}

.app-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.main-content {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

#ftr {
  width: 100%;
  position: relative; /* Cambié de absolute a relative */
  bottom: 0;
  left: 0;
}
</style>