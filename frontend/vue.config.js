module.exports = {
  transpileDependencies: [
    'vuetify','vuex-persist'
  ],

  devServer: {
    https: false,
    public: 'localhost',
    host: '0.0.0.0',
    port: 8081,
    disableHostCheck: true,
    proxy: {
      '/*': {
        target: 'http://localhost:8081',
        changeOrigin: true,
        ws: true,
        onProxyReq: function (proxyReq) {
          proxyReq.setHeader('X-Special-Proxy-Header', 'foobar');
          // Aquí puedes eliminar cookies grandes u otros encabezados innecesarios si es el caso
        }
      }
    }
  },

  // Change build paths to make them Maven compatible
  // see https://cli.vuejs.org/config/
  outputDir: 'target/dist',

  assetsDir: 'static',

  pluginOptions: {
    i18n: {
      locale: 'es',
      fallbackLocale: 'es',
      localeDir: 'lang',
      enableInSFC: false
    }
  }
}
