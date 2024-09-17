<template>
  <v-container>
    <v-card>
      <v-card-title>Detalles de la Prueba {{ test.id }}</v-card-title>
      <v-card-subtitle>{{ test.name }}</v-card-subtitle>
      <v-card-text>
        <p>{{ test.description }}</p>
        <!-- Aquí puedes agregar más detalles de la prueba -->
      </v-card-text>
    </v-card>
    <v-btn @click="$router.push('/inicio')">Volver a Inicio</v-btn>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      test: {}
    }
  },
  created() {
    const testId = this.$route.params.id;
    // Aquí debes obtener los detalles de la prueba desde un API o fuente de datos
    // Para el ejemplo, usaremos un conjunto de datos ficticios
    const tests = [
      { id: 1, name: 'Prueba 1', description: 'Descripción de la prueba 1' },
      { id: 2, name: 'Prueba 2', description: 'Descripción de la prueba 2' },
      // Agrega aquí tus otras pruebas
      { id: 10, name: 'Prueba 10', description: 'Descripción de la prueba 10' }
    ];
    this.test = tests.find(t => t.id == testId);
  }
}
</script>