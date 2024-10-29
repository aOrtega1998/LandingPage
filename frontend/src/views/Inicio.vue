<template>
  <v-container >
    <v-row class="d-flex align-center justify-center">
      <v-col v-if="allTestsCompleted" cols="12">
        <v-alert type="success" dismissible>
          <v-icon left>mdi-trophy</v-icon>
          ¡Has completado todas las pruebas! ¡Felicidades!
        </v-alert>
      </v-col>
      <v-col v-if="noAvailableTests" cols="12">
        <v-alert type="warning" dismissible>
          <v-icon left>mdi-alert-circle</v-icon>
          No hay pruebas disponibles en este momento. ¡Por favor, inténtalo más tarde!
        </v-alert>
      </v-col>
      <v-col v-if="currentTest" cols="12" md="4">
        <test-card :test="currentTest" />
      </v-col>
      <v-col cols="12" v-if="currentUser.unlockedAudios != null">
        <v-row class="mt-4" justify="center">
          <v-col cols="12" md="6">
            <v-expansion-panels multiple>
              <v-expansion-panel
                  v-for="audio in currentUser.unlockedAudios"
                  :key="audio.url"
                  class="mb-2"
                  style="max-width: 400px; width: 100%;"
              >
                <v-expansion-panel-header  class="d-flex align-start">
                  <span class="ml-2">{{ audio.name }}</span>
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                  <audio
                      :src="require(`@/assets/audios/${audio.url}`)"
                      controls
                      class="w-100"
                  ></audio>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TestCard from '@/components/TestCard.vue';
import {getData, updateData} from "@/services/firebaseService";

export default {
  components: {
    TestCard
  },
  data() {
    return {
      tests:[],
      currentUser: null,
      currentTest: null,
      users:[]
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
  async mounted() {
    this.tests = await getData("pruebas")
    this.users = await getData("usuarios")
    const sessionActive = localStorage.getItem('sessionActive');
    if (sessionActive) {
      // Lógica para cargar la información del usuario y redirigirlo
      await this.loadUser();
      this.assignTestToUser();
    } else {
      await this.$router.push('/'); // Redirige al usuario a la página de login
    }
  },
  methods: {
    async loadUser() {
      const storedUser = localStorage.getItem('username');
      if (storedUser && this.users) {
        this.currentUser = this.users.find(user => user.userLogin === storedUser);
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
          // Si hay una prueba disponible, asignarla al usuario
          if (this.currentTest) {
            this.currentUser.assignedTest = this.currentTest.name;
            this.updateUserInStorage(this.currentUser); // Actualiza el usuario en localStorage
          }
        }
      }
    },
    getOccupiedTests() {
      return this.users
          .filter(user => user.userLogin !== this.currentUser.userLogin && user.assignedTest)
          .map(user => user.assignedTest);
    },
   async updateUserInStorage(user) {
      await updateData("usuarios",user.id,user)
      localStorage.setItem('usuarios', JSON.stringify(updatedUsers));
    },
  }
}
</script>

<style scoped>
.text-center {
  text-align: center;
}
.v-alert {
  margin-bottom: 16px;
}
</style>