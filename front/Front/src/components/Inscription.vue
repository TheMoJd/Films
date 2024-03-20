<template>
  <div class="inscription">
    <h2>Inscription</h2>
    <input v-model="username" type="text" placeholder="Nom d'utilisateur" />
    <input v-model="password" type="password" placeholder="Mot de passe" />
    <button @click="inscription">S'inscrire</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import PanierService from "@/services/PanierService.js";
import UserService from "@/services/UserService.js";
import router from "@/router/index.js";

const username = ref('');
const password = ref('');
const user_id = ref('');
async function inscription() {
  try {
    // Vérifiez l'endpoint et ajustez-le selon votre API
    const response = await axios.post('http://localhost:3000/inscription', {
      username: username.value,
      password: password.value,
    });
    console.log(response);

    if (response.data.res) {
      const response1 = await UserService.getUserByUsername(username.value);
      console.log(response1.data);
      user_id.value = response1.data.id;
      console.log(user_id.value);
      const panierResponse = await PanierService.createPanier(user_id.value);
      alert('Inscription réussie. Vous pouvez maintenant vous connecter.');
      // Redirection ou nettoyage du formulaire ici
      username.value = '';
      password.value = '';
      redirectToLogin();
    } else {
      alert('Erreur lors de l’inscription. Veuillez réessayer plus tard.');
    }
  } catch (error) {
    // Gestion des erreurs de connexion ou d'autres erreurs côté client
    console.error('Erreur lors de l’inscription: ', error);
    alert('Erreur lors de l’inscription. Veuillez réessayer plus tard.');
  }
}

const redirectToLogin = () => {
  // Rediriger l'utilisateur vers la page de connexion
  router.push({ name: 'Connexion' });
};
</script>

<style scoped>
/* Votre style ici */
.inscription {
  max-width: 300px;
  margin: auto;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.inscription input,
.inscription button {
  padding: 8px;
}

.inscription button {
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}

.inscription button:hover {
  background-color: #0056b3;
}
</style>
