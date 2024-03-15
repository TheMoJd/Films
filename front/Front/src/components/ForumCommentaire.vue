<script setup>
import {onMounted, ref} from "vue";
import CommentaireService from "@/services/CommentaireService.js";
import { defineProps } from "vue";
import { useRouter } from "vue-router";
import MessageSucces from "@/components/MessageSucces.vue";
import FilmService from "@/services/FilmService.js";

const router = useRouter();
const successMessage = ref(''); // Pour stocker le message de succès

const props = defineProps({
  movieId: {
    type: Number,
    required: true,
  },
  userId: {
    type: Number,
    required: true,
  },
});

const comment = ref({
  movieId: props.movieId,
  userId: props.userId,
  text: ""
});
const film = ref({});
const loadFilm = async () => {
  try {
    const response = await FilmService.getFilmById(props.movieId);
    film.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

onMounted(() => {
  loadFilm();
});
// Fonction pour soumettre le commentaire
const submitComment = async () => {
  const fullComment = { movieId: props.movieId, user_id: props.userId, corps: comment.value.text};
  console.log(fullComment);
  try {
    const response = await CommentaireService.createCommentaire(fullComment);
    console.log(response);
    comment.value.text = ""; // Réinitialiser le texte du commentaire
    successMessage.value = "Commentaire ajouté avec succès"; // Mettre à jour le message de succès
    setTimeout(() => successMessage.value = '', 3000);

    //await router.push({path: '/'}); // Naviguer à la racine ou à une autre route selon les besoins
  } catch (error) {
    console.error(error); // Gérer l'erreur éventuelle
  }
};
</script>

<template>
  <MessageSucces v-if="successMessage" :message="successMessage" />
  <h2>Laissez un commentaire pour le film</h2>
  <div class="film-card">
    <h3>{{ film.name }}</h3>
    <img v-if="film.url" :src="`/${film.url}`" :alt="film.name" class="film-image"/>
  </div>
    <form @submit.prevent="submitComment">
    <div class="form-group">
      <input type="text" id="text" v-model="comment.text" required>
    </div>
    <button type="submit">Envoyer</button>
  </form>

</template>

<style scoped>


</style>