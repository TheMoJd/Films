<template>
  <div class="connexion">
    <h2>Connexion</h2>
    <input v-model="username" type="text" placeholder="Nom d'utilisateur" />
    <input v-model="password" type="password" placeholder="Mot de passe" />
    <button @click="login">Se connecter</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import {jwtDecode} from "jwt-decode";
import useAuthStore from "@/store/authStore.js";
import router from "@/router/index.js";

const {setUser} = useAuthStore();

const username = ref('');
const password = ref('');


async function login() {
  try {
    if (!username.value || !password.value) {
      alert("Veuillez remplir tous les champs.");
      return;
    }

    const response = await axios.post('http://localhost:3000/connexion', {
      username: username.value,
      password: password.value,
    });
    const token = response.data.token;
    localStorage.setItem('token', response.data.token);
    const decodedToken = jwtDecode(token);
    console.log(decodedToken);
    setUser(decodedToken);
    alert('Connexion réussie');
    //redirection ver la page d'acceuils
    await router.push({name: 'Accueil'});

  } catch (error) {
    const message = error.response && error.response.data ? error.response.data.message : 'Erreur lors de la connexion.';
    alert(message);
  }

}
</script>

<style scoped>
/* Votre style ici */
</style>
