<template>
  <v-app>
    <v-img
        :src="require('@/assets/fondo.png')"
        class="background-image"
    >
      <div class="app-container">
        <NavBar />
        <main class="main-content">
          <router-view />
        </main>
      </div>
    </v-img>
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

</style>