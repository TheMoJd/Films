<script setup>
import {ref, onMounted, nextTick} from 'vue';
import FilmService from '../services/FilmService.js';
import CommentaireService from "@/services/CommentaireService.js";
import MessageSucces from "@/components/MessageSucces.vue";

const films = ref([]);

const commentaires = ref([]);

const successMessage = ref(''); // Pour stocker le message de succès

const loadFilmsandComments = async () => {
  try {
    const response = await FilmService.getAllFilms(); // Récupération des lieux depuis le service
    films.value = response.data; // Mise à jour de la liste des lieux avec les données récupérées
    // recuperer les commentaires pour chaque film

    for (const film of films.value) {
      const response = await CommentaireService.getCommentaireByMovieId(film.id);
      commentaires.value[film.id] = response.data;
    }
    console.log(commentaires.value);
    await nextTick(); // Attente de la mise à jour du DOM

  } catch (error) {
    console.error(error);
  }
};

onMounted(() => {
  loadFilmsandComments();
});
const deleteFilm = async (filmId) => {
  try {
    const response = await FilmService.deleteFilm(filmId);
    await nextTick(); // Attente de la mise à jour du DOM

    console.log(response);
    films.value = films.value.filter(film => film.id !== filmId);
    successMessage.value = "Film supprimé avec succès"; // Mettre à jour le message de succès
    setTimeout(() => successMessage.value = '', 3000);

  } catch (error) {
    console.error(error);
  }
};

const deleteComment = async (commentId) => {
  if (typeof commentId === 'undefined' || commentId === null) {
    console.error('L\'ID du commentaire est undefined ou null.');
    return;
  }
  try {
    const response = await CommentaireService.deleteCommentaire(commentId);
    await nextTick(); // Attente de la mise à jour du DOM

    console.log(response);
    // Supprimer le commentaire de la liste des commentaires
    for (const film of films.value){
      commentaires.value[film.id] = commentaires.value[film.id].filter(comment => comment.id !== commentId);
    }
    await loadFilmsandComments();
    successMessage.value = "Commentaire supprimé avec succès"; // Mettre à jour le message de succès
    setTimeout(() => successMessage.value = '', 3000);

  } catch (error) {
    console.error(error);
  }
};



</script>

<template>
<!-- Message de succes -->
  <MessageSucces v-if="successMessage" :message="successMessage" />

  <div class="film-list-container">
    <h2>Gestion de Films et Commentaires</h2>
    <div class="films">
      <div class="film-card" v-for="film in films" :key="film.id">
        <h3>{{ film.name }}  <font-awesome-icon icon="times" @click="deleteFilm(film.id)" style="color : red"/></h3>


        <img v-if="film.url" :src="`/${film.url}`" :alt="film.name" class="film-image"/>

        <p><strong>Date de sortie :</strong> {{ film.release_date }}</p>

        <p>{{ film.description }}</p>

        <!--
                <button @click="deleteFilm(film.id)">Supprimer</button>
        -->
        <!--boucle for pour parcourir les commentaires correspondant a ce film -->
        <h4>Commentaires</h4>
        <ul>
          <li v-for="commentaire in commentaires[film.id]" :key="commentaire.id">
            {{ commentaire.corps }} -- {{commentaire.comment_id}}
            <button @click="deleteComment(commentaire.comment_id)">Supprimer</button>

          </li>
        </ul>
      </div>
    </div>
  </div>
</template>


<style>

</style>

