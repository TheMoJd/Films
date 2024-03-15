<template>
  <div>
    <MessageSuccess v-if="messageSuccess" :message="messageSuccess" />
    <h2>Liste des Utilisateurs</h2>
    <table>
      <thead>
      <tr>
        <th>Nom d'utilisateur</th>
        <th>Role</th>
        <th>Active</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.id">
        <td>{{ user.username }}</td>
        <td>
          <select v-model="user.is_admin">
            <option :value="true">Administrateur</option>
            <option :value="false">Utilisateur</option>
          </select>
        </td>
        <td>
          <select v-model="user.active">
            <option :value="true">Oui</option>
            <option :value="false">Non</option>
          </select>
        </td>
        <td>
          <button @click="saveUser(user, user_id) ">Modifier</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import UserService from '@/services/UserService.js';
import MessageSucces from "@/components/MessageSucces.vue";

const users = ref([]);

const messageSuccess = ref('');
onMounted(async () => {
  try {
    const response = await UserService.getAllUsers();
    users.value = response.data;
    console.log(users.value);
  } catch (error) {
    console.error('Erreur lors de la récupération des utilisateurs:', error);
  }
});

const saveUser = async (user, user_id) => {
  await UserService.updateUser(user_id, user);
  messageSuccess.value = 'Utilisateur modifié avec succès';

};
</script>

<style scoped>
/* Ajoutez votre style ici */
</style>
