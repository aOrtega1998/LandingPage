<template>
  <div>
    <v-footer padless hide-on-scroll>
      <v-row id="ftr-future">
        <div class="col pa-0">

        </div>
        <div class="col pt-5" align="center">

        </div>
        <div class="col  mt-5 mr-3">
          <v-row class="mr-4">
            <v-spacer></v-spacer>

          </v-row>
        </div>
      </v-row>
    </v-footer>
    <v-footer padless dark>
      <v-row id="ftr-intranet-version">
        <v-col
            id="copyright"
            class="text-center ml-3 mr-3"
            cols="auto">
          Copyright © {{ new Date().getFullYear() }} — <strong>Talking Right Now</strong>
        </v-col>
      </v-row>
    </v-footer>
  </div>
</template>

<script>


export default {
  name: "Footer",
  data () {
    return {
      version: String,
      fab: false
    }
  },
  methods: {
    goTo (link) {
      window.open(link)
    },
    onScroll (e) {
      if (typeof window === 'undefined') return
      const top = window.pageYOffset || e.target.scrollTop || 0
      this.fab = top > 20
    },
    toTop () {
      this.$vuetify.goTo(0)
    }
  }
}


</script>

<style scoped>
.social-network-icon :hover{
  cursor: pointer;
}
#ftr-future {
  background-color: #001021;
  min-height: 50px;
}
#ftr-intranet-version {
  background-color: #00030F;
}
#copyright {
  font-size: small;
}

</style>