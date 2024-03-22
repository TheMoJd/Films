<script setup>
import { ref } from 'vue';
import FilmService from "@/services/FilmService.js";

const movie = ref({
  name: '',
  releaseDate: '',
  description: '',
  url: '' // URL d'une image ou d'une affiche du film
});

let messageSuccess = ref('');
function submitForm() {
  console.log('Film à ajouter:', movie.value);
  const response = FilmService.createFilm(movie.value);
  console.log('Réponse du serveur:', response);
  messageSuccess = 'Film ajouté avec succès!';
  // Réinitialiser le formulaire après l'ajout
  movie.value = { name: '', releaseDate: '', description: '', url: '' };
}

</script>


<template>
  <div class="add-movie">
    <MessageSuccess :message="messageSuccess" />
    <h2>Ajouter un Film</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="title">Titre:</label>
        <input v-model="movie.name" type="text" id="name" required>
      </div>

      <div class="form-group">
        <label for="releaseDate">Date de Sortie:</label>
        <input v-model="movie.releaseDate" type="date" id="releaseDate" required>
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea v-model="movie.description" id="description" required></textarea>
      </div>

      <div class="form-group">
        <label for="imageUrl">URL de l'Image:</label>
        <input v-model="movie.url" type="text" id="url">
      </div>

      <button type="submit">Ajouter le Film</button>
    </form>
  </div>
</template>


<style scoped>

</style>