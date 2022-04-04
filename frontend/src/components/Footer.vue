<template>
  <div>
    <v-footer padless>
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
        <v-divider
            id="divider"
            vertical class="my-3"></v-divider>

        <v-spacer></v-spacer>
        <div>
          <v-row>
            <v-col cols="auto" class="text-center pl-9 pr-1">
              <h5>Síguenos en nuestras <strong>Redes Sociales</strong></h5>
            </v-col>
            <v-col cols="auto" class="pa-0 pt-1">
              <v-img
                  class="social-network-icon"
                  v-on:click= "goTo('https://twitter.com/FutureSpaceES', '_blank')"
                  :src="require('@/assets/social_networks_icons/Twitter.png')">
              </v-img>
            </v-col>

            <v-col cols="auto" class="pa-0 pt-1">
              <v-img
                  class="social-network-icon"
                  v-on:click= "goTo('https://www.linkedin.com/company/future-space-s.a.', '_blank')"
                  :src="require('@/assets/social_networks_icons/Linkedin.png')">
              </v-img>
            </v-col>

            <v-col cols="auto" class="pa-0 pt-1">
              <v-img
                  class="social-network-icon"
                  v-on:click= "goTo('https://www.instagram.com/futurespace_es/', '_blank')"
                  :src="require('@/assets/social_networks_icons/Instagram.png')">
              </v-img>
            </v-col>

            <v-col cols="auto" class="pa-0 pt-1">
              <v-img
                  class="social-network-icon"
                  v-on:click= "goTo('https://www.youtube.com/channel/UCE5B4NVXjFSgmbzEH93gdxw/', '_blank')"
                  :src="require('@/assets/social_networks_icons/Youtube.png')">
              </v-img>
            </v-col>
          </v-row>
        </div>
        <v-col class="text-center" cols="auto"></v-col>
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
  /*#social-networks{*/
  /*  background: url("../assets/solcial-networks-background.jpg");*/
  /*}*/
.social-network-icon :hover{
  cursor: pointer;
}
.social-network-icon {
  width: 33px;
  height: 33px;
}
.iso-csq-icon {
  width: 44px;
  height: 44px;
  display: inline-block;
}
.sello-pyme-icon {
  width: 44px;
  height: 63px;
  display: inline-block;
}
.ens-icon {
  width: 44px;
  height: 61px;
  display: inline-block;
}
.logo-future {
  align-items: center;
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
#support {
  font-size: small;
}
#support-link {
  color: #FFFFFF;
}
#support-link:hover {
  color: #18a0ff;
}
#divider {
  background-color: #FFFFFF;
}
</style>