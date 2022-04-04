<template>
  <v-container >
    <v-app-bar elevation="4" dense app color="pink lighten-5" hide-on-scroll   >
      <v-spacer></v-spacer>
      <v-col cols="auto" class="pa-0 pt-1">
        <v-img
            :src="require('@/assets/LogoTRNSinFondo.png')"
            max-height="50"
            max-width="50"
        >
        </v-img>
      </v-col>
      <v-spacer></v-spacer>
    </v-app-bar>
  </v-container>
</template>

<script>
export default {
  name: "NavBar"
}
</script>

<style scoped>

</style>