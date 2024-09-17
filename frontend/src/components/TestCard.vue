<template>
  <v-card class="custom-card mx-auto my-4" @click="goToTestDetail" max-width="344">
    <v-img
        src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
        height="200px"
    ></v-img>
    <v-card-title>{{ test.name }}</v-card-title>
    <v-card-subtitle>{{ test.description }}</v-card-subtitle>
    <v-card-actions>
      <v-btn @click.stop="goToTestDetail" color="primary">Ver Prueba</v-btn>
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
  margin-top: 24px; /* Ajusta el valor según lo necesites */
  cursor: pointer;
}
</style>