<template>
  <v-container>
    <v-card
        class="mx-auto"
        max-width="500px"
    >
      <v-card-title>{{ test.name }}</v-card-title>
      <v-card-text>
        <p>{{ test.description }}</p>
        <v-img
            :src="require(`@/assets/imagenesMapas/${test.mapa}`)"
            width="480px"
            height="270px"
            contain
        ></v-img>
        <!-- Campo para ingresar el primer código -->
        <v-text-field
            v-model="inputCode1"
            label="Introduce el primer código"
            :disabled="code1Verified"
            required
        ></v-text-field>
        <v-btn
            @click="verifyCode1"
            color="primary"
            v-if="!code1Verified"
        >
          Verificar Primer Código
        </v-btn>

        <!-- Mensaje de error si el primer código es incorrecto -->
        <p v-if="code1Error" style="color: red;">Código incorrecto. Inténtalo de nuevo.</p>
        <!-- Campo para ingresar el segundo código -->
        <v-text-field
            v-if="test.id === 3 && code1Verified"
            v-model="inputCode2"
            label="Introduce el segundo código"
            :disabled="code2Verified"
            required
        ></v-text-field>
        <v-btn
            v-if="test.id === 3 && code1Verified && !code2Verified"
            @click="verifyCode2"
            color="primary"
        >
          Verificar Segundo Código
        </v-btn>
        <!-- Mensaje de error para el segundo código -->
        <p v-if="code2Error" style="color: red;">Segundo código incorrecto. Inténtalo de nuevo.</p>

        <!-- Reproductor de audio si el código es correcto -->
        <audio v-if="isAudioUnlocked" :src="audioSrc" controls autoplay></audio>
      </v-card-text>
      <!-- Botón para finalizar la prueba una vez se escuche el audio -->
      <v-btn
          v-if="isAudioUnlocked"
          color="success"
          @click="finishTest"
      >Finalizar Prueba</v-btn>
    </v-card>



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
    };
  },
  async created() {
    const testId = parseInt(this.$route.params.id);
    this.tests= await getData("pruebas")
    this.audios= await getData("audios")
    this.users = await getData("usuarios")
    // Encontrar la prueba con el id correspondiente
    this.test = this.tests.find(t => t.id === testId);
    //this.audioSrc = this.test.audio;  // Asigna el audio correspondiente a la prueba
  },
  computed: {
    isAudioUnlocked() {
      return this.test.id === 3
          ? this.code1Verified && this.code2Verified
          : this.code1Verified;
    }
  },
  methods: {
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
      // Aquí defines qué audio se desbloquea para cada prueba completada

      const audioToUnlock = [
        { name: 'Audio 1', src: require('@/assets/audios/prueba1.mp3') },
        { name: 'Audio 2', src: require('@/assets/audios/prueba2.mp3') },
        { name: 'Audio 3', src: require('@/assets/audios/prueba2.mp3') },
        { name: 'Audio 4', src: require('@/assets/audios/prueba2.mp3') },
        { name: 'Audio 5', src: require('@/assets/audios/prueba2.mp3') },
        { name: 'Audio 6', src: require('@/assets/audios/prueba2.mp3') },
        { name: 'Audio 7', src: require('@/assets/audios/prueba2.mp3') },
        { name: 'Audio 8', src: require('@/assets/audios/prueba2.mp3') },
        { name: 'Audio 9', src: require('@/assets/audios/prueba2.mp3') },
        { name: 'Audio 10', src: require('@/assets/audios/prueba2.mp3') },
      ]

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

  }
};
</script>