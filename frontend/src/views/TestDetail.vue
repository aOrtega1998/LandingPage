<template>
  <v-container>
    <v-card>
      <v-card-title>Detalles de la Prueba {{ test.id }}</v-card-title>
      <v-card-subtitle>{{ test.name }}</v-card-subtitle>
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
        { id: 1, name: 'Prueba 1', description: 'Descripción de la prueba 1', audio: require('@/assets/audios/prueba1.mp3'), code: '1111' },
        { id: 2, name: 'Prueba 2', description: 'Descripción de la prueba 2', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 3, name: 'Prueba 3', description: 'Descripción de la prueba 3', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 4, name: 'Prueba 4', description: 'Descripción de la prueba 4', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
        { id: 5, name: 'Prueba 5', description: 'Descripción de la prueba 5', audio: require('@/assets/audios/prueba2.mp3'), code: '2222' },
       // { id: 10, name: 'Prueba 10', description: 'Descripción de la prueba 10', audio: require('@/assets/audios/audio10.mp3'), code: '1010' }
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
    }
  }
};
</script>