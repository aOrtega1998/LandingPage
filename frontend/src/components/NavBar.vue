<template>
  <v-container>
    <v-app-bar elevation="4" dense app color="red accent-4" hide-on-scroll>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-btn v-if="this.$route.name !== 'Login'" @click="handleLogout" color="white" text>
        Cerrar Sesión
      </v-btn>
    </v-app-bar>
  </v-container>
</template>

<script>
export default {
  name: "NavBar",
  data() {
    return {

    };
  },
  methods: {
    handleLogout() {
      // Elimina el nombre de usuario del almacenamiento local
      localStorage.removeItem('username');
      localStorage.removeItem('sessionActive');
      // Redirige al usuario a la página de inicio de sesión
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>

</style>