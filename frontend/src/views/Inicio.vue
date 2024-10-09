<template>
  <v-container>
    <v-row>
      <v-col v-if="allTestsCompleted" cols="12">
        <v-alert type="success" dismissible>
          ¡Has completado todas las pruebas! ¡Felicidades!
        </v-alert>
      </v-col>
      <v-col v-if="noAvailableTests" cols="12">
        <v-alert type="warning" dismissible>
          No hay pruebas disponibles en este momento. ¡Por favor, inténtalo más tarde!
        </v-alert>
      </v-col>
      <v-col v-if="currentTest" cols="12" md="4">
        <test-card :test="currentTest" />
      </v-col>
      <v-col cols="12">
      <v-expansion-panels>
        <v-expansion-panel v-for="audio in currentUser.unlockedAudios" :key="audio.src">
          <v-expansion-panel-header>
            {{ audio.name }}
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <audio :src="audio.src" controls></audio>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
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
        { id: 1, name: 'CIRCO DEL SOL', description: 'Descripción de la prueba 1', imagen: require('@/assets/imagenPruebas/p1_circo-del-sol.png') },
        { id: 2, name: 'ESCAPISMO', description: 'Descripción de la prueba 2', imagen:require('@/assets/imagenPruebas/p2_escapismo.jpg') },
        { id: 3, name: 'HOMBRE BALA', description: 'Descripción de la prueba 3', imagen:require('@/assets/imagenPruebas/p3_hombre-bala.jpg') },
        { id: 4, name: 'ADIVINACIÓN', description: 'Descripción de la prueba 4', imagen:require('@/assets/imagenPruebas/p4_adivinacion.jpg') },
        { id: 5, name: 'TELEQUINESIS', description: 'Descripción de la prueba 5', imagen:require('@/assets/imagenPruebas/p5_telequinesia.png') },
        { id: 6, name: 'TRAPECISTAS', description: 'Descripción de la prueba 6', imagen:require('@/assets/imagenPruebas/p6_trapecistas.jpg') },
        { id: 7, name: 'ESPEJISMOS', description: 'Descripción de la prueba 7', imagen:require('@/assets/imagenPruebas/p7_espejismos.jpg') },
        { id: 8, name: 'ILUSIONISMO', description: 'Os veréis descubriendo un mensaje oculto con un artilugio que os puede dar el domador del circo', imagen:require('@/assets/imagenPruebas/p8_ilusionismo.jpg') },
        { id: 9, name: 'EQUILIBRISMO', description: 'Descripción de la prueba 9', imagen:require('@/assets/imagenPruebas/p9_equilibrismo.jpg') },
        { id: 10, name: 'MALABARISMO', description: 'Descripción de la prueba 10', imagen:require('@/assets/imagenPruebas/p10_malabarismo.jpg') }
      ],
      currentUser: null,
      currentTest: null,
    };
  },
  computed: {
    allTestsCompleted() {
      return this.currentUser && this.currentUser.completedTests.length === this.tests.length;
    },
    noAvailableTests() {
      return !this.currentTest && !this.allTestsCompleted;
    }
  },
  mounted() {
    const sessionActive = localStorage.getItem('sessionActive');
    if (sessionActive) {
      // Lógica para cargar la información del usuario y redirigirlo
      this.loadUser();
      this.assignTestToUser();
    } else {
      this.$router.push('/'); // Redirige al usuario a la página de login
    }
  },
  methods: {
    loadUser() {
      const storedUser = localStorage.getItem('username');
      const storedUsers = JSON.parse(localStorage.getItem('usuarios'));

      if (storedUser && storedUsers) {
        this.currentUser = storedUsers.find(user => user.userLogin === storedUser);
      }
    },
    assignTestToUser() {
      if (this.currentUser) {
        const occupiedTests = this.getOccupiedTests();

        // Verificar si la prueba asignada está ocupada
        if (this.currentUser.assignedTest && !this.currentUser.completedTests.includes(this.currentUser.assignedTest) &&
            !occupiedTests.includes(this.currentUser.assignedTest)) {
          this.currentTest = this.tests.find(test => test.name === this.currentUser.assignedTest);
        } else {
          // Buscar la siguiente prueba que no haya completado y no esté ocupada
          this.currentTest = this.tests.find(test =>
              !occupiedTests.includes(test.name) &&
              !this.currentUser.completedTests.includes(test.name)
          );
          console.log(this.currentTest)
          // Si hay una prueba disponible, asignarla al usuario
          if (this.currentTest) {
            this.currentUser.assignedTest = this.currentTest.name;
            this.updateUserInStorage(this.currentUser); // Actualiza el usuario en localStorage
          }
        }
      }
    },
    getOccupiedTests() {
      const users = JSON.parse(localStorage.getItem('usuarios'));
      return users
          .filter(user => user.userLogin !== this.currentUser.userLogin && user.assignedTest)
          .map(user => user.assignedTest);
    },
    updateUserInStorage(user) {
      const users = JSON.parse(localStorage.getItem('usuarios'));
      const updatedUsers = users.map(u => (u.userLogin === user.userLogin ? user : u));
      localStorage.setItem('usuarios', JSON.stringify(updatedUsers));
    },
  }
}
</script>

<style scoped>
.text-center {
  text-align: center;
}
</style>