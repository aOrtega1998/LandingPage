module.exports = {
  transpileDependencies: ['vuetify', 'vuex-persist'],

  devServer: {
    https: false,
    public: 'localhost',
    host: '0.0.0.0',
    port: 8081,
    disableHostCheck: true,
    historyApiFallback: true,  // Agrega esta línea
  },

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
