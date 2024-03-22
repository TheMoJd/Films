<template>
  <MessageSucces v-if="successMessage" :message="successMessage" />
  <div class="locations-container">
    <h2>Historique des Locations</h2>
    <div v-if="locations && locations.length > 0">
      <div class="location-item" v-for="location in locations" :key="location.rental_id">

        <div v-for="rental in location.rentals" :key="rental.movie_id">
          <h3>Film: {{ rental.film.name }} - ID: {{ rental.movie_id }}</h3>
          <p>Date de location: {{ rental.rental_date }}</p>
          <p>Date de retour: {{ rental.return_date }}</p>
          <p>État: {{ rental.etat === '0' ? 'Non Retourné' : 'Retourné' }}</p>
        </div>
      </div>
    </div>
    <div v-else>
      <p>Aucune location trouvée.</p>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted, computed} from 'vue';
import LocationService from '../services/LocationService'; // Assurez-vous d'avoir ce service
import FilmService from '../services/FilmService'; // Ce service est utilisé pour enrichir les locations avec les noms des films
import MessageSucces from "@/components/MessageSucces.vue";
import useAuthStore from "@/store/authStore.js";

const {state} = useAuthStore();
const userId = computed(() => state.user ? state.user.user_id : null);
console.log(userId.value);
const locations = ref([]);
const successMessage = ref('');

async function chargerLocations() {
  if (!userId.value) return; // Arrêt si aucun utilisateur n'est connecté
  try {
    const response = await LocationService.getLocationsByUserId(userId.value);
    locations.value = response.data;
    console.log(locations.value);

    for (const location of locations.value) {
      for (const rental of location.rentals) {
        console.log(rental);
        const filmResponse = await FilmService.getFilmById(rental.movie_id);
        rental.film = filmResponse.data; // Enrichissement des rentals avec les noms des films
        console.log(rental);
      }
    }
  } catch (error) {
    console.error("Erreur lors du chargement des locations: ", error);
  }
}

onMounted(chargerLocations);
</script>

<style>
.locations-container {
  max-width: 800px;
  margin: auto;
  padding: 20px;
}

.location-item {
  background-color: #f0f0f0;
  padding: 15px;
  margin-bottom: 15px;
  border-radius: 5px;
}

.location-item h3 {
  margin-top: 0;
}
</style>
