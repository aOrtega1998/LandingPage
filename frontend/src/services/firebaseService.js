// firebaseService.js
import {db} from '@/firebaseConfig';
import {addDoc, collection, doc, getDocs, updateDoc, getDoc} from 'firebase/firestore';

// Función para obtener los datos
export const getData = async (collectionName) => {
    try {
        const querySnapshot = await getDocs(collection(db, collectionName));
        return querySnapshot.docs.map(doc => ({
           id: doc.id,
           ...doc.data()
        }));
    } catch (error) {
        console.error("Error al obtener los datos:", error);
        return [];
    }
};
export const getDocumentById = async (collectionName, id) => {
    try {
        const docRef = doc(db, collectionName, id);
        const docSnap = await getDoc(docRef);

        if (docSnap.exists()) {
            return docSnap.data(); // Devuelve los datos del documento
        } else {
            console.log("No se encontró el documento.");
            return null; // Si no existe, devuelve null o maneja el caso según tu necesidad
        }
    } catch (error) {
        console.error("Error al obtener el documento:", error);
        return null;
    }
};
// Función para agregar un nuevo documento
export const addData = async (collectionName, data) => {
    await addDoc(collection(db, collectionName), data);
};

// Función para actualizar un documento existente
export const updateData = async (collectionName, id, newData) => {
    const docRef = doc(db, collectionName, id);
    await updateDoc(docRef, newData);
};