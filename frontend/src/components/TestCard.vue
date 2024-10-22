<template>
  <v-card
      class="custom-card mx-auto my-4 elevation-2 rounded-lg hover--elevate-2"
      max-width="344"
  >
    <v-img
        :src="require(`@/assets/imagenPruebas/${test.imagen}`)"
        height="200px"
        class="rounded-t-lg"
    ></v-img>
    <v-card-title class="headline">{{ test.name }}</v-card-title>
    <v-card-actions>
      <v-btn
          @click.stop="goToTestDetail"
          color="primary"
          block
          style="transition: background-color 0.3s;"
          @mouseover="'#0056b3'"
          @mouseleave="'#1976d2'"
      >Iniciar Prueba</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    test: Object
  },
  methods: {
    goToTestDetail() {
      this.$router.push({ name: 'test-detail', params: { id: this.test.id } });
    }
  }
}
</script>

<style scoped>
.custom-card {
  transition: transform 0.2s;
}
.custom-card:hover {
  transform: scale(1.05);
}
</style>