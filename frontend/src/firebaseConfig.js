// firebaseConfig.js
import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";

const firebaseConfig = {
    apiKey: "AIzaSyCL61qXEqvybKOsh-49Pvmf_Rybk950Uk8",
    authDomain: "halloween2024-1a36a.firebaseapp.com",
    projectId: "halloween2024-1a36a",
    storageBucket: "halloween2024-1a36a.appspot.com",
    messagingSenderId: "62427548754",
    appId: "1:62427548754:web:8ce1b73f38b225a222638b",
    measurementId: "G-CCKX2GY3QW"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getFirestore(app);

export { db };