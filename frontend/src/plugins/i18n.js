import Vue from 'vue'
import VueI18n from 'vue-i18n'
import messages from '@/lang/es'
import axios from 'axios'

Vue.use(VueI18n)

export const i18n = new VueI18n({
  locale: 'es', // set locale
  fallbackLocale: 'es',
  messages: { es: messages } // set locale messages
})

const loadedLanguages = ['es'] // our default language that is preloaded

function setI18nLanguage (lang) {
  // update i18n current lang
  i18n.locale = lang
  // modify axios common headers
  axios.defaults.headers.common['Accept-Language'] = lang
  // and update html lang
  document.querySelector('html').setAttribute('lang', lang)
  // finally, return the lang
  return lang
}

export function loadLanguageAsync (lang) {
  // If the same language
  if (i18n.locale === lang) {
    return Promise.resolve(setI18nLanguage(lang))
  }

  // If the language was already loaded
  if (loadedLanguages.includes(lang)) {
    return Promise.resolve(setI18nLanguage(lang))
  }

  // If the language hasn't been loaded yet
  return import('@/lang/' + lang + '.js').then(
      response => {
        i18n.setLocaleMessage(lang, response.default)
        loadedLanguages.push(lang)
        return setI18nLanguage(lang)
      }
  )
}
