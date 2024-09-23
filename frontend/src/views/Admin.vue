<template>
  <v-container>
    <h1 class="text-center">Administración de Usuarios</h1>
    <v-data-table
        :headers="headers"
        :items="usuarios"
        item-key="userLogin"
        class="elevation-1"
    >
      <template v-slot:item.pruebasCompletadas="{ item }">
        <div>{{ item.completedTests.join(', ') }}</div>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      usuarios: [],
      headers: [
        { text: 'Login del Usuario', value: 'userLogin' },
        { text: 'Contador de Pruebas', value: 'contadorPruebas' },
        { text: 'Prueba Asignada', value: 'assignedTest' }, // Nueva columna para la prueba asignada
        { text: 'Pruebas Completadas', value: 'pruebasCompletadas', sortable: false }, // Nueva columna para pruebas completadas
      ],
    };
  },
  mounted() {
    // Crear los usuarios directamente en localStorage
    this.crearUsuariosIniciales();
    // Cargar los usuarios de localStorage para mostrarlos en la tabla
    this.loadUsuarios();
  },
  methods: {
    // Crear usuarios iniciales en localStorage
    crearUsuariosIniciales() {
      // Verificar si ya existen usuarios en localStorage para evitar sobrescribir
      const usuariosLocalStorage = localStorage.getItem('usuarios');
      if (!usuariosLocalStorage) {
        const usuariosIniciales = [
          { id: 1, userLogin: 'admin', contadorPruebas: 0, assignedTest: null, completedTests: [] },
          { id: 2, userLogin: 'user1', contadorPruebas: 0, assignedTest: null, completedTests: [] },
          { id: 3, userLogin: 'user2', contadorPruebas: 0, assignedTest: null, completedTests: [] },
          { id: 4, userLogin: 'user3', contadorPruebas: 0, assignedTest: null, completedTests: [] }
        ];
        // Guardar los usuarios iniciales en localStorage
        localStorage.setItem('usuarios', JSON.stringify(usuariosIniciales));
      }
    },
    // Cargar usuarios desde localStorage
    loadUsuarios() {
      const usuariosLocalStorage = localStorage.getItem('usuarios');
      if (usuariosLocalStorage) {
        // Filtrar para excluir al usuario 'admin'
        const allUsers = JSON.parse(usuariosLocalStorage);
        this.usuarios = allUsers.filter(user => user.userLogin !== 'admin');
      } else {
        this.usuarios = [];
      }
    },
    // Guardar los usuarios en localStorage
    saveUsuarios() {
      localStorage.setItem('usuarios', JSON.stringify(this.usuarios));
    },
  }
};
</script>

<style scoped>
.text-center {
  text-align: center;
}
</style>