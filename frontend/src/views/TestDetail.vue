<template>
  <v-container>
    <v-card>
      <v-card-title>{{ test.name }}</v-card-title>
      <v-card-text>
        <p>{{ test.description }}</p>

        <!-- Campo para ingresar el código -->
        <v-text-field
            v-model="inputCode"
            label="Introduce el código para desbloquear el audio"
            :disabled="codeVerified"
            required
        ></v-text-field>
        <v-btn
            @click="verifyCode"
            color="primary"
            v-if="!codeVerified"
        >Verificar Código</v-btn>

        <!-- Mensaje de error si el código es incorrecto -->
        <p v-if="codeError" style="color: red;">Código incorrecto. Inténtalo de nuevo.</p>

        <!-- Reproductor de audio si el código es correcto -->
        <audio v-if="codeVerified" :src="audioSrc" controls autoplay></audio>
      </v-card-text>
    </v-card>

    <!-- Botón para finalizar la prueba una vez se escuche el audio -->
    <v-btn
        v-if="codeVerified && audioFinished"
        color="success"
        @click="finishTest"
    >Finalizar Prueba</v-btn>

  </v-container>
</template>

<script>
export default {
  data() {
    return {
      test: {},
      inputCode: '',  // Código introducido por el usuario
      codeVerified: false,  // Si el código ha sido verificado correctamente
      codeError: false,  // Para mostrar un mensaje de error si el código es incorrecto
      audioSrc: '',  // Fuente del archivo de audio
      audioFinished: false,  // Para saber si el audio ha terminado de reproducirse
      tests: [
        { id: 1, name: 'CIRCO DEL SOL', description: 'Descripción de la prueba 1', audio: require('@/assets/audios/prueba1.mp3'), code: '1111' },
        { id: 2, name: 'ESCAPISMO', description: 'Descripción de la prueba 2', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 3, name: 'HOMBRE BALA', description: 'Descripción de la prueba 3', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 4, name: 'ADIVINACIÓN', description: 'Descripción de la prueba 4', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 5, name: 'TELEQUINESIS', description: 'Descripción de la prueba 5', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 6, name: 'TELEQUINESIS', description: 'Descripción de la prueba 5', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 7, name: 'ESPEJISMOS', description: 'Descripción de la prueba 5', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 8, name: 'ILUSIONISMO', description: 'Tenéis que traer una cinta de tela de las que hay en la Morala, y llevársela al domador, que está dando vueltas por el pueblo con una vela. Él os dará lo que necesitáis.', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 9, name: 'EQUILIBRISMO', description: 'Descripción de la prueba 5', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 10, name:'MALABARISMO', description: 'Descripción de la prueba 5', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
      ]
    };
  },
  created() {
    const testId = parseInt(this.$route.params.id);
    // Encontrar la prueba con el id correspondiente
    this.test = this.tests.find(t => t.id === testId);
    this.audioSrc = this.test.audio;  // Asigna el audio correspondiente a la prueba
  },
  methods: {
    verifyCode() {
      if (this.inputCode === this.test.code) {
        this.codeVerified = true;
        this.codeError = false;
        this.audioFinished = true;
      } else {
        this.codeError = true;
      }
    },
    finishTest() {
      let users = JSON.parse(localStorage.getItem('usuarios'));
      const currentUser = localStorage.getItem('username');
      const user = users.find(u => u.userLogin === currentUser);

      if (user) {
        // Actualizar el contador de pruebas completadas
        user.contadorPruebas += 1;

        // Asegurarse de que el array de pruebas completadas exista
        if (!user.completedTests) {
          user.completedTests = [];
        }

        // Agregar la prueba actual a las completadas
        user.completedTests.push(this.test.name);
        this.unlockAudioForTest(this.test, user)
        // Obtener las pruebas que ya están asignadas a otros usuarios
        const assignedTests = users
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

        // Actualizar el usuario en localStorage
        localStorage.setItem('usuarios', JSON.stringify(users));

        // Volver a la vista de inicio
        this.$router.push('/inicio');
      }
    },
    unlockAudioForTest(test, user) {
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
      if (audioToUnlock[completedCount - 1]) { // Asegúrate de que el índice sea válido
        const audio = audioToUnlock[completedCount - 1]; // Obtener el siguiente audio
        if (!user.unlockedAudios.some(unlocked => unlocked.name === audio.name)) { // Verificar si ya está desbloqueado
          user.unlockedAudios.push(audio); // Agregar solo si no está desbloqueado
        }
      }
    },

  }
};
</script>