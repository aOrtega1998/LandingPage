import Swal from 'sweetalert2/dist/sweetalert2.js'
import { i18n } from "@/plugins/i18n"

export const gestionarAvisos = (icono,titulo,texto) => {
  Swal.fire({
    icon: icono,
    title: titulo,
    text: texto,
    customClass: {
      confirmButton: "swal-button-ok",
    },
  })
};

export const gestionarErrores = (error,icono,titulo,texto) => {
  console.log('error', error.status);
  if (error.status === 401) {
      this.$router.push('/').then();
  } else {
      console.error('error', error);
  }
  gestionarAvisos(icono,titulo,texto);
};

export const mostrarMensajeError = (mensaje) => {
  console.log('mensaje', mensaje);
};

export const mostrarSolicitudConfirmarOperacion = (icon, title, text, cancelButtonText, confirmButtonText)=>{
  return Swal.fire({
    title: title,
    text: text,
    icon: icon,
    showCancelButton: true,
    cancelButtonText: cancelButtonText,
    confirmButtonText: confirmButtonText,
    customClass: {
      confirmButton: "swal-button-accept",
      cancelButton: "swal-button-cancel",
    },
    buttonsStyling: true,
    reverseButtons: true,
    focusCancel: false,
    focusConfirm: false,
    allowEscapeKey: false,
    allowOutsideClick: false
  })
};

export const mostrarSolicitudConfirmarOperacionButtonStyle = (icon, title, text,
                                                    cancelButtonText, confirmButtonText,
                                                    cancelButtonStyle, confirmButtonStyle)=>{
  return Swal.fire({
    title: title,
    text: text,
    icon: icon,
    showCancelButton: true,
    cancelButtonText: cancelButtonText,
    confirmButtonText: confirmButtonText,
    customClass: {
      confirmButton: confirmButtonStyle,
      cancelButton: cancelButtonStyle,
    },
    buttonsStyling: true,
    reverseButtons: true,
    focusCancel: false,
    focusConfirm: false,
    allowEscapeKey: false,
    allowOutsideClick: false
  })
};

export const mostrarSolicitudConfirmarOperacionConTextArea = (icon, title, text, inputPlaceholder,validationText,cancelButtonText,
                                                              confirmButtonText)=>{
  return Swal.fire({
    title: title,
    text: text,
    input:'textarea',
    inputPlaceholder: inputPlaceholder,
    inputAttributes: {
      autocapitalize: 'off',
      maxlength: 300,
    },
    icon: icon,
    showCancelButton: true,
    cancelButtonText: cancelButtonText,
    confirmButtonText: confirmButtonText,
    customClass: {
      confirmButton: "swal-button-accept",
      cancelButton: "swal-button-cancel",
      input:"swal-text-area",
      validationMessage:"v-application",
    },
    buttonsStyling: true,
    reverseButtons: true,
    focusCancel: false,
    focusConfirm: false,
    allowEscapeKey: false,
    allowOutsideClick: false,
    inputValidator: (value) => {
      if (!value) {
        return validationText
      }
    }
  })
};