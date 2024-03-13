<script setup>
import { ref } from "vue";
import CommentaireService from "@/services/CommentaireService.js";
import { defineProps } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

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

// Fonction pour soumettre le commentaire
const submitComment = async () => {
  const fullComment = { movieId: props.movieId, user_id: props.userId, corps: comment.value.text};
  console.log(fullComment);
  try {
    const response = await CommentaireService.createCommentaire(fullComment);
    console.log(response);
    comment.value.text = ""; // Réinitialiser le texte du commentaire
    await router.push({path: '/'}); // Naviguer à la racine ou à une autre route selon les besoins
  } catch (error) {
    console.error(error); // Gérer l'erreur éventuelle
  }
};
</script>

<template>
  <form @submit.prevent="submitComment">
    <div class="form-group">
      <label for="name">Nom</label>
      <input type="text" id="text" v-model="comment.text" required>
    </div>
    <button type="submit">Envoyer</button>
  </form>
</template>

<style scoped>

</style>