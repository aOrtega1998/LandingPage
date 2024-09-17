<template>
  <v-container>
    <v-row>
      <v-col v-for="test in tests" :key="test.id" cols="12" md="4">
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
        { id: 4, name: 'Prueba 5', description: 'Descripción de la prueba 5' },
        { id: 4, name: 'Prueba 6', description: 'Descripción de la prueba 6' },
        { id: 4, name: 'Prueba 7', description: 'Descripción de la prueba 7' },
        { id: 4, name: 'Prueba 8', description: 'Descripción de la prueba 8' },
        { id: 4, name: 'Prueba 9', description: 'Descripción de la prueba 9' },
        { id: 10, name: 'Prueba 10', description: 'Descripción de la prueba 10' }
      ]
    }
  }
}
</script>