<template>
  <MessageSucces v-if="successMessage" :message="successMessage" />
  <div class="panier-container">
    <h2>Votre Panier</h2>
    <div v-if="panier && panier.items && panier.items.length > 0">
      <div class="panier-item" v-for="item in panier.items" :key="item.movie_id">
        <h3>{{ item.film.name }} - {{item.movie_id}}</h3> <!-- Affichage du nom du film -->
        <p>Quantité: {{ item.quantity }}</p>
        <button @click="retirerDuPanier(panier.id, item.movie_id)">Retirer du panier</button>
      </div>
    </div>
    <div v-else>
      <p>Votre panier est vide.</p>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import PanierService from '../services/PanierService'; // Assurez-vous d'avoir créé ce service
import FilmService from '../services/FilmService'; // Assurez-vous d'avoir créé ce service
import MessageSucces from "@/components/MessageSucces.vue";

const user_id = 1; // Remplacez par l'ID de l'utilisateur connecté
const panier = ref({ items: [] });
const successMessage = ref('');

async function chargerPanier() {
  try {
    const response = await PanierService.getAllPaniers();
    const userPaniers = response.data.filter(p => p.user_id === user_id); // Filtrer les paniers pour l'utilisateur connecté

    if (userPaniers.length > 0) {
      panier.value = userPaniers[0];

      for (const [index, item] of panier.value.items.entries()) {
        const filmResponse = await FilmService.getFilmById(item.movie_id); // Récupération des détails du film
        panier.value.items[index].film = filmResponse.data; // Enrichissement de l'item avec les détails du film
      }
    }
    console.log(panier.value);
  } catch (error) {
    console.error(error);
  }

}
onMounted(chargerPanier);

function retirerDuPanier(panier_id, movie_id) {
  // Ici, vous implémenteriez la logique pour retirer un film du panier en utilisant PanierService
  console.log(`Retirer le film avec l'ID ${movie_id} du panier ${panier_id}`);
  PanierService.retirerFilmDuPanier(panier_id, movie_id);
  successMessage.value = 'Film retiré du panier avec succès';
  setTimeout(() => successMessage.value = '', 3000);
}
</script>

<style>
/* Ajoutez ici votre style pour panier-container et panier-item */
.panier-container {
  max-width: 600px;
  margin: auto;
  padding: 20px;
}

.panier-item {
  background-color: #f9f9f9;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 4px;
}

.panier-item h3 {
  margin-top: 0;
}

button {
  margin-top: 10px;
}
</style>
