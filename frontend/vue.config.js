module.exports = {
  transpileDependencies: [
    'vuetify'
  ],

  devServer: {
    https: false,
    public: 'localhost',
    host: '0.0.0.0',
    port: 8081,
    disableHostCheck: true,
    proxy: {
      '/*': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        ws: true
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
