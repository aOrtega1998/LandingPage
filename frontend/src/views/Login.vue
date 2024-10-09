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
      username: '',  // Nombre de usuario ingresado
      usuarios: []  // Lista de usuarios desde localStorage
    };
  },
  mounted() {
    // Cargar los usuarios desde localStorage al montar el componente
    this.loadUsuarios();
  },
  methods: {
    // Método para cargar los usuarios desde localStorage
    loadUsuarios() {
      const usuariosLocalStorage = localStorage.getItem('usuarios');
      if (usuariosLocalStorage) {
        this.usuarios = JSON.parse(usuariosLocalStorage);
      } else {
        this.usuarios = [];
      }
    },
    // Método para manejar el inicio de sesión
    handleLogin() {
      if (this.username) {
        localStorage.setItem('username', this.username);
        // Almacena el estado de sesión como 'true'
        localStorage.setItem('sessionActive', 'true');
        // Verifica si el username existe en la lista de usuarios
        if(this.username === "admin"){
          this.$router.push('/admin');
        }else{
          const usuarioEncontrado = this.usuarios.find(user => user.userLogin === this.username);

          if (usuarioEncontrado) {
            // Almacena el nombre de usuario en localStorage
            localStorage.setItem('username', this.username);

            // Redirecciona a diferentes rutas según el nombre de usuario
            if (this.username === 'admin') {
              this.$router.push('/admin');  // Redirige a la vista de administrador
            } else {
              this.$router.push('/inicio');  // Redirige a la vista de usuarios normales
            }
          } else {
            // Si no se encuentra el usuario, muestra un mensaje de error
            alert('El usuario no existe en la base de datos.');
          }
        }

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