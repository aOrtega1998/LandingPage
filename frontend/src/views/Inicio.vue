<template>

  <div id="principal" class="fill-height login-bg">
    <v-container class="my-1">
          <v-col></v-col>
          <v-row class="text-center">
            <v-col>
              <v-card-title  class="text-h4">EASY GRAMMAR!</v-card-title>
              <v-spacer></v-spacer>
              <v-card-title >
                <p>APRENDE LA GRAMÁTICA INGLESA ¡SIN MORIR EN EL INTENTO!</p>
              </v-card-title>
            </v-col>
            <v-col  >
              <v-img
                  :src="require('@/assets/EASYGRAMMAR.png')"
                  max-height="300"
                  max-width="300"
              >
              </v-img>
            </v-col>
          </v-row>
          <v-card-text class="text-h5">¿Tienes problemas con la gramática? ¿Llevas años estudiándola y no terminas de entenderla? ¿Sabes aplicarla en ejercicios, pero en la vida real se te complica…?</v-card-text>
          <v-row class="text-center">
            <v-card-text class="text-h4">¡EASY GRAMMAR es lo que necesitas! </v-card-text>
            <v-card-text class="text-h6">Tras varios años ayudando a cientos de alumnos a aprender inglés, he podido ver la importancia de la gramática, pero, sobre todo, de saber aplicarla en la vida real.  </v-card-text>
            <v-card-text class="text-h6"> Por esto decidí crear este curso, para personas como tú que, tras años de estudio, todavía se lían con los tiempos verbales, confunden unos con otros o no saben exactamente en qué situaciones aplicarlos…</v-card-text>
            <v-card-text class="text-h6"> Prueba los tres primeros vídeos del curso TOTALMENTE GRATIS y podrás disfrutar de un descuentazo ¡del 50%! </v-card-text>
          </v-row>
      <v-spacer></v-spacer>
      <v-row class="text-center">
        <v-col>
          <v-btn
              class="grey darken-2 white--text text--lighten-4"
              @click="dialogForm=true"
          >
            Quiero mis vídeos gratis</v-btn>
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col>
            <video width="50%" controls autoplay>
              <source src ="@/assets/videos/Vídeo-promo-easy-grammar.mp4"
              type="video/mp4"/>
            </video>
        </v-col>
      </v-row>
    </v-container>
    <v-divider></v-divider>
    <div :style="{backgroundImage: `url(${LogoTRNSinFondo})`, backgroundSize:'53% 53%', backgroundPosition: 'right top' }">

      <v-container class="my-1">
            <v-card-title>¿Todavía tienes dudas? ¡Sigue leyendo!</v-card-title>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-card-subtitle>¿QUÉ APRENDERÁS EN ESTE CURSO?</v-card-subtitle>
            <v-card-text>
              <p>-	Toda la gramática inglesa de una forma fácil, práctica y divertida. </p>
              <p>-	Aprenderás a utilizar los tiempos verbales en situaciones reales, no volverás a confundirlos jamás.</p>
              <p>-	Trucos para recordar las reglas y saber aplicarlos a la vida real. </p>
            </v-card-text>
            <v-spacer></v-spacer>
            <v-card-subtitle>¿QUÉ INCLUYE EL CURSO?</v-card-subtitle>
            <v-card-text>
              <p>-	31 lecciones en vídeo.</p>
              <p>-	31 PDFs correspondientes a cada lección.</p>
              <p>-	Ejemplos prácticos para aprender a aplicar la gramática a la vida real.</p>
              <p>-	Tests interactivos al final de cada lección para practicar lo aprendido.</p>
              <p>-	Masterclass para resolver dudas para las 20 primeras personas que entren en el curso.</p>
              <p>-	Acceso al curso de por vida.</p>
            </v-card-text>
            <v-card-subtitle>¿POR QUÉ NECESITAS ESTE CURSO?</v-card-subtitle>
            <v-card-text>
              <p>-	Tendrás toda la gramática en un mismo espacio, no tendrás que preocuparte por comprarte un nuevo libro, curso… ¡nada!</p>
              <p>-	¡Vas a disfrutar taaanto las clases! Es como si estuviera ahí contigo y te lo vas a pasar genial.</p>
              <p>-	Al ser en formato vídeo, podrás verlo las veces que quieras y practicar conmigo todo lo que necesites.</p>
              <p>-	Por fin conseguirás entender y saber aplicar la gramática para poder aprender inglés de verdad y poder comunicarte dónde, cuándo y con quién quieras.</p>
            </v-card-text>

            <v-card-subtitle>¿QUIÉN ES IRENE?</v-card-subtitle>
            <v-row>
              <v-col cols="12" sm="10" md="8" lg="5" xl="4" >
                <v-card-text>Hi there! Si todavía no me conoces, aquí te dejo un trocito de mi. </v-card-text>
                <v-card-text>Me llamo Irene y me dedico a ayudar a jóvenes y adultos a aprender inglés de la forma más divertida y práctica posible. Tras varios años ayudando a cientos de alumnos, creo que puedo decir con toda seguridad que soy experta en gramática. Después de haberla estudiado, contado y explicado tantas veces ¡ya me contarás! </v-card-text>
              </v-col>
              <v-col  sm="10" md="8" lg="5" xl="4" >
                <v-img
                    :src="require('@/assets/Irene.png')"
                    max-height="250"
                    max-width="250"
                >
                </v-img>
              </v-col>
            </v-row>
            <v-card-text>Por eso decidí crear este curso de esta manera tan “de verdad”, sin tecnicismos, algo fácil y sencillo que se entienda a la primera, sin reglas raras y enfocado al uso en la vida real. Así es como me gusta explicar el inglés, como una herramienta práctica que sirve para comunicarnos, para poder conocer personas y nuevas culturas, para poder conseguir el trabajo de nuestros sueños o vivir en ese país que nos encanta. </v-card-text>
            <v-card-text>Espero poder transmitirte un poquito más de mi pasión por este idioma, pero, sobre todo, por todo lo que puedes hacer en esta vida hablando inglés. </v-card-text>
            <v-spacer></v-spacer>
            <v-row class="text-center">
              <v-col>
                <p class="text-h5" >¡NOS VEMOS DENTRO!</p>
                <v-btn
                    class="grey darken-2 white--text text--lighten-4"
                    @click="dialogForm=true"
                >
                  Quiero mis vídeos gratis</v-btn>
              </v-col>
            </v-row>

            <v-card-text></v-card-text>
            <v-card-text></v-card-text>


    <!-- Carga de dialog para alta de usuario -->
        <v-dialog v-model="dialogForm" persistent max-width="40%" >
          <v-form ref="form">
            <v-card>
              <v-card-title>
                <v-img
                    :src="require('@/assets/LogoTRNSinFondo.png')"
                    max-height="60"
                    max-width="60"
                >
                </v-img>
                <p class="headline ml-2 mt-2">QUIERO MIS VÍDEOS GRATIS!!</p>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>

                    <v-col>
                      <v-text-field
                        v-model="usuario.nombre"
                        label="Nombre *"
                        :rules="[rules.required,rules.notEmptyText]"
                        append-icon="mdi-account-outline"
                      ></v-text-field>
                    </v-col>
                  </v-row>

                  <v-row>

                    <v-col>
                      <v-text-field
                          v-model="usuario.apellido"
                          label="Apellido *"
                          :rules="[rules.required,rules.notEmptyText]"
                          append-icon="mdi-account-outline"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-text-field
                          v-model="usuario.email"
                          label="Email *"
                          :rules="[rules.required,rules.email]"
                          append-icon="mdi-email-outline"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="dialogForm = false">Cancelar</v-btn>
                <v-btn color="pink lighten-5 gray--text" @click="createUsuario(usuario)">Envíamelo</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-form>
        </v-dialog>
    </v-container>
   </div>
  </div>
</template>

<script>

import {usuariosService} from '@/services/usuarios.service';
import LogoTRNSinFondo from '@/assets/LogoTRNSinFondo.png';
import {i18n} from "@/plugins/i18n";
import modal_messages from "@/utils/modal_messages";
import validations from '@/mixins/validations'
const MESSAGES = i18n.messages[i18n.locale];


export default {
  name: 'Inicio',

  data () {
    return {
      LogoTRNSinFondo,
      dialogForm: false,
      usuario:{
        nombre: null,
        apellido: null,
        email: null,
      },
      rules: validations,
    }

  },
  methods: {

    createUsuario (usuario) {
      if (this.$refs.form.validate()) {
        usuariosService.createUsuario(usuario)
            .then(() => {
              modal_messages.methods.successNotification(
                  `Formulario enviado`,
                  `Se ha enviado el email correctamente`
              );
            })
            .catch((error) => {
              console.error("error", error);
              modal_messages.methods.failNotification(
                  `Error`,
                  `Email ya existe`
              );
            })
        this.$refs.form.reset()
        this.dialogForm = false
      }
    },

  },

}
</script>

