<template>
  <v-container>
    <v-card
        class="mx-auto my-4 elevation-2"
        max-width="500px"
        tile
    >
      <v-card-title class="headline">{{ test.name }}</v-card-title>
      <v-card-text>
        <p>{{ test.description }}</p>
        <v-img
            v-if="test.mapa"
            :src="require(`@/assets/imagenesMapas/${test.mapa}`)"
            width="480px"
            height="270px"
            contain
            class="rounded-lg mb-3"
        ></v-img>
        <v-row justify="center" class="image-group">
          <v-col
              v-for="(image, index) in selectedImages"
              :key="index"
              cols="4"
              class="d-flex justify-center"
          >
          <v-img
              :src="require(`@/assets/imagenPruebas/prueba10/${image}`)"
              width="120px"
              height="120px"
              contain
              class="mx-2"
              @click="openDialog(image)"
          ></v-img>
          </v-col>
        </v-row>
        <!-- Campo para ingresar el primer código -->
        <v-text-field
            v-model="inputCode1"
            label="Introduce el primer código"
            :disabled="code1Verified"
            required
            clearable
        ></v-text-field>

        <v-btn
            @click="verifyCode1"
            color="primary"
            v-if="!code1Verified"
            class="mb-2"
        >
          Verificar Primer Código
        </v-btn>

        <p v-if="code1Error" class="error-text">Código incorrecto. Inténtalo de nuevo.</p>

        <!-- Campo para ingresar el segundo código -->
        <v-text-field
            v-if="test.id === 3 && code1Verified"
            v-model="inputCode2"
            label="Introduce el segundo código"
            :disabled="code2Verified"
            required
            clearable
        ></v-text-field>

        <v-btn
            v-if="test.id === 3 && code1Verified && !code2Verified"
            @click="verifyCode2"
            color="primary"
            class="mb-2"
        >
          Verificar Segundo Código
        </v-btn>

        <p v-if="code2Error" class="error-text">Segundo código incorrecto. Inténtalo de nuevo.</p>

        <!-- Reproductor de audio si el código es correcto -->
        <audio v-if="isAudioUnlocked" :src="audioSrc" controls autoplay></audio>
      </v-card-text>

      <v-card-actions>
        <v-btn
            v-if="isAudioUnlocked"
            color="success"
            @click="finishTest"
        >
          Finalizar Prueba
        </v-btn>
      </v-card-actions>
    </v-card>
    <!-- Dialogo para ampliar la imagen -->
    <v-dialog v-model="dialog" max-width="600px">
      <v-card>
        <v-img v-if="selectedImage != null" :src="require(`@/assets/imagenPruebas/prueba10/${selectedImage}`)" width="100%" height="auto"></v-img>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog = false">Cerrar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import {getData, updateData} from "@/services/firebaseService";

export default {
  data() {
    return {
      test: {},
      users:[],
      inputCode1: '',  // Código introducido por el usuario para la primera etapa
      inputCode2: '',  // Código introducido por el usuario para la segunda etapa
      code1Verified: false,  // Si el primer código ha sido verificado correctamente
      code2Verified: false,  // Si el segundo código ha sido verificado correctamente
      code1Error: false,  // Para mostrar un mensaje de error si el primer código es incorrecto
      code2Error: false,  // Para mostrar un mensaje de error si el segundo código es incorrecto
      audioSrc: null,  // Fuente del archivo de audio
      tests:[],
      audios:[],
      selectedImages: [],
      selectedImage: null,
      dialog: false,
    };
  },
  async created() {
    const testId = parseInt(this.$route.params.id);
    this.tests= await getData("pruebas")
    this.audios= await getData("audios")
    this.users = await getData("usuarios")
    this.selectedImages = this.getRandomImageSet();
    // Encontrar la prueba con el id correspondiente
    this.test = this.tests.find(t => t.id === testId);
  },
  computed: {
    isAudioUnlocked() {
      return this.test.id === 3
          ? this.code1Verified && this.code2Verified
          : this.code1Verified;
    }
  },
  methods: {
    openDialog(image) {
      this.selectedImage = image;
      this.dialog = true;
    },
    verifyCode1() {
      if (this.inputCode1 === this.test.code1) {
        this.code1Verified = true;
        this.code1Error = false;
        this.updateAudioSource();
      } else {
        this.code1Error = true;
      }
    },
    verifyCode2() {
      if (this.inputCode2 === this.test.code2) {
        this.code2Verified = true;
        this.code2Error = false;
        this.updateAudioSource();
      } else {
        this.code2Error = true;
      }
    },
    updateAudioSource() {
      if (this.isAudioUnlocked) {
        const currentUser = localStorage.getItem('username');
        const user = this.users.find(u => u.userLogin === currentUser);

        if (!user) {
          console.error("Usuario no encontrado");
          return;
        }

        let audio;
        if (user.unlockedAudios.length === 0) {
          // Si no hay audios desbloqueados, usar el audio con ID 1
          audio = this.audios.find(a => a.id === 1);
        } else {
          // Obtener el siguiente audio según el contador de pruebas completadas
          const completedCount = user.contadorPruebas;
          audio = this.audios[completedCount - 1];
        }

        if (audio) {
          this.audioSrc = require(`@/assets/audios/${audio.url}`);
          console.log("Audio seleccionado:", audio);
        } else {
          console.error("No se encontró el audio correspondiente.");
        }
      }
    },
   async finishTest() {
      const currentUser = localStorage.getItem('username');
      const user = this.users.find(u => u.userLogin === currentUser);

      if (user) {
        // Actualizar el contador de pruebas completadas
        user.contadorPruebas += 1;

        // Asegurarse de que el array de pruebas completadas exista
        if (!user.completedTests) {
          user.completedTests = [];
        }
        // Agregar la prueba actual a las completadas
        user.completedTests.push(this.test.name);
        this.unlockAudioForTest(user)
        // Obtener las pruebas que ya están asignadas a otros usuarios
        const assignedTests = this.users
            .filter(u => u.userLogin !== currentUser) // Excluir al usuario actual
            .map(u => u.assignedTest)
            .filter(Boolean);

        // Asignar la siguiente prueba que no haya completado el usuario y que no esté asignada a otro
        const nextTest = this.tests.find(t =>
            !user.completedTests.includes(t.name) &&
            !assignedTests.includes(t.name)
        );

        if (nextTest) {
          user.assignedTest = nextTest.name; // Almacena la prueba asignada
        } else {
          user.assignedTest = null; // Asegurarse de que no haya una prueba asignada
        }
        console.log(user)
        await updateData("usuarios",user.id,user)
        console.log(user)
        // Actualizar el usuario en localStorage
        //localStorage.setItem('usuarios', JSON.stringify(users));

        // Volver a la vista de inicio
        await this.$router.push('/inicio');
      }
    },
    unlockAudioForTest(user) {

      // Agrega más audios según sea necesario
      const completedCount = user.contadorPruebas; // Este es el número de pruebas completadas
      console.log(completedCount)
      if (this.audios[completedCount - 1]) { // Asegúrate de que el índice sea válido
        const audio = this.audios[completedCount - 1]; // Obtener el siguiente audio
        console.log(audio)
        if (!user.unlockedAudios.some(unlocked => unlocked.id === audio.id)) { // Verificar si ya está desbloqueado
          user.unlockedAudios.push(audio); // Agregar solo si no está desbloqueado
        }
      }
    },
    getRandomImageSet() {
      const imageGroups = [
        ['grupo1/d1_1.jpg', 'grupo1/d1_2.jpg', 'grupo1/d1_3.jpg'],
        ['grupo2/d2_1.jpg', 'grupo2/d2_2.jpg', 'grupo2/d2_3.jpg'],
        ['grupo3/d3_1.jpg', 'grupo3/d3_2.jpg', 'grupo3/d3_3.jpg'],
        ['grupo4/d4_1.jpg', 'grupo4/d4_2.jpg', 'grupo4/d4_3.jpg'],
        ['grupo5/d5_1.jpg', 'grupo5/d5_2.jpg', 'grupo5/d5_3.jpg']
      ];
      // Selecciona un grupo de imágenes aleatorio
      const randomIndex = Math.floor(Math.random() * imageGroups.length);
      return imageGroups[randomIndex];
    }

  }
};
</script>
<style scoped>
.error-text {
  color: red;
  margin: 8px 0;
}
.clickable-image {
  cursor: pointer;
}
</style>