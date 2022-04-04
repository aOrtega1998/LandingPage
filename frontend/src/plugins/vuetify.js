import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.use(Vuetify)

export default new Vuetify({
    icons: {
        iconfont: 'md'
    },
    theme: {
        options: {
            customProperties: true,
        },
        themes: {
            light: {
                //Here are the default background colors
                primary: '#616161',
                secondary: '#FCE4EC',
                error: '#dc3545'
            }
        }
    }
})
