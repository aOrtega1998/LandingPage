<template>

  <v-app>
    <div>
      <NavBar></NavBar>
    </div>
   <main>
     <router-view></router-view>
   </main>
    <Footer id="ftr"></Footer>
  </v-app>
</template>

<script>
import Footer from '@/components/Footer'
import NavBar from "@/components/NavBar";
export default {
  name: 'App',
  components:{
    Footer,
    NavBar
  },
   data: ()=> ({
   })
}

</script>
<style>
#ftr {
  width: 100%;
  position: absolute;
  bottom: 0;
  left: 0;
}
</style>