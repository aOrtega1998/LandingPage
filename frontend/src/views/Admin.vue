<template>
  <v-container class="mx-auto" style="max-width: 800px; background-color: #f7f7f7; border-radius: 10px; padding: 20px;">
    <h1 class="text-center" style="font-family: 'Roboto', sans-serif; font-weight: bold; font-size: 36px; color: #1976d2;">
      Administración de Usuarios
    </h1>
    <v-data-table
        :headers="headers"
        :items="users"
        item-key="userLogin"
        class="elevation-3"
        style="border-radius: 10px;"
        :header-props="{ backgroundColor: '#1976d2', color: '#ffffff' }"
    >
      <template v-slot:item.completedTests="{ item }">
        <div style="transition: background-color 0.3s;" @mouseover="this.style.backgroundColor = '#e0f7fa'" @mouseleave="this.style.backgroundColor = 'transparent'">
          {{ item.completedTests.join(', ') }}
        </div>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import { getData } from '@/services/firebaseService';
export default {
  data() {
    return {
      usuarios: [],
      users:[],
      headers: [
        { text: 'Login del Usuario', value: 'userLogin' },
        { text: 'Contador de Pruebas', value: 'contadorPruebas' },
        { text: 'Prueba Asignada', value: 'assignedTest' }, // Nueva columna para la prueba asignada
        { text: 'Pruebas Completadas', value: 'completedTests', sortable: false }, // Nueva columna para pruebas completadas
      ],
    };
  },
  async mounted() {
    this.users = await getData("usuarios")
    this.users = this.users.filter(user => user.userLogin !== 'admin');

  },
  methods: {
  }
};
</script>

<style scoped>
.text-center {
  text-align: center;
}
</style>