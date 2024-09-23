<template>
  <v-container>
    <v-row>
      <v-col v-for="test in filteredTests" :key="test.id" cols="12" md="4">
        <test-card :test="test" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TestCard from '@/components/TestCard.vue';

export default {
  components: {
    TestCard
  },
  data() {
    return {
      tests: [
        { id: 1, name: 'Prueba 1', description: 'Descripción de la prueba 1' },
        { id: 2, name: 'Prueba 2', description: 'Descripción de la prueba 2' },
        { id: 3, name: 'Prueba 3', description: 'Descripción de la prueba 3' },
        { id: 4, name: 'Prueba 4', description: 'Descripción de la prueba 4' },
        { id: 5, name: 'Prueba 5', description: 'Descripción de la prueba 5' },
        { id: 6, name: 'Prueba 6', description: 'Descripción de la prueba 6' },
        { id: 7, name: 'Prueba 7', description: 'Descripción de la prueba 7' },
        { id: 8, name: 'Prueba 8', description: 'Descripción de la prueba 8' },
        { id: 9, name: 'Prueba 9', description: 'Descripción de la prueba 9' },
        { id: 10, name: 'Prueba 10', description: 'Descripción de la prueba 10' }
      ],
      currentUser: null,  // Guardará los datos del usuario actual
      filteredTests: []   // Pruebas filtradas para el usuario
    };
  },
  mounted() {
    this.loadUser();
    this.filterTestsForUser();
  },
  methods: {
    // Cargar datos del usuario desde localStorage
    loadUser() {
      const storedUser = localStorage.getItem('username');
      const storedUsers = JSON.parse(localStorage.getItem('usuarios'));

      if (storedUser && storedUsers) {
        this.currentUser = storedUsers.find(user => user.userLogin === storedUser);
      }
    },
    // Filtrar la prueba correspondiente al usuario actual
    filterTestsForUser() {
      if (this.currentUser && this.currentUser.assignedTest) {
        this.filteredTests = this.tests.filter(test => test.name === this.currentUser.assignedTest);
      }
    }
  }
}
</script>