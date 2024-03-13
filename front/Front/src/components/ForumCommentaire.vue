<script setup>
import { ref } from "vue";


const comment = ref({
  pseudo: "",
  text: "",
});
</script>

<template>
  <form @submit.prevent="submitComment">
    <div class="form-group">
      <label for="name">Nom</label>
      <input type="text" id="name" v-model="comment.text" required>
    </div>

    <button type="submit">Envoyer</button>
  </form>
</template>

<style scoped>

</style>