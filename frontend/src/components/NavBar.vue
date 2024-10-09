<template>
  <v-container>
    <v-app-bar elevation="4" dense app color="red accent-4" hide-on-scroll>
      <v-spacer></v-spacer>
      <v-col cols="auto" class="pa-0 pt-1">
        <v-img
            :src="require('@/assets/logo_halloween.png')"
            max-height="50"
            max-width="50"
        />
      </v-col>
      <v-spacer></v-spacer>
      <v-btn @click="handleLogout" color="white" text>
        Cerrar Sesión
      </v-btn>
    </v-app-bar>
  </v-container>
</template>

<script>
export default {
  name: "NavBar",
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