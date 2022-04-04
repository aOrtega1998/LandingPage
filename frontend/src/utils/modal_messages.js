import Swal from "sweetalert2";

export default {
  methods: {
    successNotification: function (title, text) {
      return Swal.fire({
        title: title,
        text: text || "",
        icon: "success",
        showConfirmButton: false,
        timer: 5000,
      });
    },
    clickAcceptNotification: function (title, text) {
      return Swal.fire({
        position: "center",
        confirmButtonColor: "#003043",
        title: title,
        text: text,
      });
    },
    failNotification: function (title, text) {
      return Swal.fire({
        icon: "error",
        position: "center",
        title: title,
        text: text || "",
        showConfirmButton: true,
      });
    },
    questionNotificacion: function (title, text) {
      return Swal.fire({
        title: title,
        text: text,
        icon: "warning",
        iconColor: "#1976d2",
        showCancelButton: true,
        showConfirmButton: true,
        confirmButtonColor: "#1976d2",
        cancelButtonColor: "#1976d2",
      });
    },
  },
};
