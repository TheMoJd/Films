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

const username = ref('');
const password = ref('');

async function inscription() {
  try {
    // Vérifiez l'endpoint et ajustez-le selon votre API
    const response = await axios.post('http://localhost:3000/inscription', {
      username: username.value,
      password: password.value,
    });

    // Vous pouvez ajuster cette réponse en fonction de ce que votre API renvoie
    if (response.data.res) {
      alert('Inscription réussie. Vous pouvez maintenant vous connecter.');
      // Redirection ou nettoyage du formulaire ici
      username.value = '';
      password.value = '';
    } else {
      // Gestion des messages d'erreur renvoyés par votre API
      alert('Erreur lors de l’inscription: ' + response.data.mess.join('\n'));
    }
  } catch (error) {
    // Gestion des erreurs de connexion ou d'autres erreurs côté client
    console.error('Erreur lors de l’inscription: ', error);
    alert('Erreur lors de l’inscription. Veuillez réessayer plus tard.');
  }
}
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
