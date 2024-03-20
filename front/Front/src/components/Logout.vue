<script setup>
  import {useRouter} from 'vue-router';
  import useAuthStore from "@/store/authStore.js";

  const router = useRouter();
  const {clearUser} = useAuthStore();

  function logout() {
  // Supprimer le token du localStorage
  localStorage.removeItem('token');

  // Mise à jour de l'état global pour refléter la déconnexion de l'utilisateur
  clearUser();

  // Redirection vers la page de connexion
    router.push({ name: 'Connexion' });
}
</script>


<template>
  <button @click="logout">Déconnexion</button>

</template>

<style scoped>

</style>