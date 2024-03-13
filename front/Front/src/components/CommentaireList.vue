<script setup>
/*
import {ref, onMounted} from 'vue';
import CommentaireService from '../services/CommentaireService.js';

const commentaires = ref([]);

onMounted(async () => {
  try {
    const response = await CommentaireService.get();
    commentaires.value = response.data;
    console.log(commentaires.value);
  } catch (error) {
    console.error(error);
  }
});

import {useRoute} from 'vue-router';
import MessageSucces from "@/components/MessageSucces.vue";

let message = "";
const route = useRoute();

if (route.query.message) {
  message = route.query.message;
}
console.log(message);*/

</script>

<template>
<!--  <MessageSucces v-if="message" :message="message" />
  <div>
    <h2>Liste des Commentaires</h2>
    <ul>
      <template v-for="commentaire in commentaires" :key="commentaire.id">
&lt;!&ndash;        <CommentaireCard :id="commentaire.id"
                         :texte="commentaire.texte" />&ndash;&gt;
        <li>{{ commentaire.texte }}</li>
      </template>
    </ul>
  </div>-->
</template>

<style scoped>

</style>