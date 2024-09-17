<template>
  <v-container
      fluid
      fill-height
      class="d-flex align-center justify-center"
  >
    <v-card
        class="pa-4"
        max-width="400"
        tile
    >
      <v-card-title class="headline">Iniciar Sesión</v-card-title>
      <v-form @submit.prevent="handleLogin">
        <v-text-field
            v-model="username"
            label="Nombre de Usuario"
            required
        ></v-text-field>
        <v-card-actions>
          <v-btn
              type="submit"
              color="primary"
              block
          >
            Iniciar Sesión
          </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      username: ''
    };
  },
  methods: {
    handleLogin() {
      if (this.username) {
        // Puedes almacenar el nombre de usuario en el almacenamiento local (localStorage)
        localStorage.setItem('username', this.username);
        this.$router.push('/inicio');
      } else {
        alert('Por favor, ingresa un nombre de usuario.');
      }
    }
  }
}
</script>
<style scoped>
/* Asegúrate de que el contenedor ocupe toda la altura y esté centrado */
.v-container {
  height: 100vh; /* Hace que el contenedor ocupe toda la altura de la ventana */
}
</style>