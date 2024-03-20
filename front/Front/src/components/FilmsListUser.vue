<script setup>
import {ref, onMounted, nextTick, computed} from 'vue';
import FilmService from '../services/FilmService.js';
import CommentaireService from "@/services/CommentaireService.js";
import router from "@/router/index.js";
import PanierService from "@/services/PanierService.js";
import MessageSuccess from "@/components/MessageSucces.vue";
import useAuthStore from "@/store/authStore.js";

const { state } = useAuthStore();
const user_id = computed(() => state.user ? state.user.user_id : 0);
console.log(user_id.value);
const films = ref([]);

const commentaires = ref([]);


onMounted(async () => {
  try {
    const response = await FilmService.getAllFilms(); // Récupération des lieux depuis le service
    films.value = response.data; // Mise à jour de la liste des lieux avec les données récupérées
    console.log(films.value);
    // recuperer les commentaires pour chaque film

    for (const film of films.value){
      const response = await CommentaireService.getCommentaireByMovieId(film.id);
      commentaires.value[film.id] = response.data;
    }
    //console.log(commentaires.value);
    await nextTick(); // Attente de la mise à jour du DOM

  } catch (error) {
    console.error(error);
  }
});



const redirectToCommentPage = (movieId) => {
  // Ou utilisez votre routeur Vue pour naviguer
  if (user_id.value === 0) {
    redirectToLogin();
    return; // Arrêter l'exécution si l'utilisateur n'est pas connecté
  }
  else {
    console.log("ici");
    router.push({ name: 'ForumCommentaire', params: { movieId: movieId, userId: user_id.value } });
  }
};

const panier = ref({ items: [] });

const successMessage = ref(''); // Pour stocker le message de succès
const ajouterAuPanier = async (filmId) => {
  if (user_id.value === 0) {
    redirectToLogin();
    return; // Arrêter l'exécution si l'utilisateur n'est pas connecté
  }

  try {
    // Obtention de tous les paniers, puis filtrage pour celui de l'utilisateur actuel
    const response1 = await PanierService.getAllPaniers();
    const userPanier = response1.data.filter(p => p.user_id === user_id.value);
    console.log(userPanier);
    if (userPanier.length > 0) {
      const panierId = userPanier[0].id; // Assurez-vous que l'ID est correctement extrait
      console.log(panierId, filmId);

      // Appel du service pour ajouter le film au panier
      const response = await PanierService.ajouterFilmAuPanier(panierId, filmId, 1);
      await nextTick(); // Attente de la mise à jour du DOM

      successMessage.value = 'Film ajouté au panier avec succès';
      setTimeout(() => successMessage.value = '', 3000);
      console.log(response);
    } else {
      redirectToLogin();
      console.log("Aucun panier trouvé pour cet utilisateur. Redirection vers la page de connenxion");
    }
  } catch (error) {
    console.error("Erreur lors de l'ajout au panier :", error);
  }
};


const redirectToLogin = () => {
  // Rediriger l'utilisateur vers la page de connexion
  router.push({ name: 'Connexion' });
};
</script>

<template>
  <div class="film-list-container">
    <MessageSuccess v-if="successMessage" :message="successMessage" />
    <h2>Liste de Films</h2>
    <div class="films">
      <div class="film-card" v-for="film in films" :key="film.id">
        <h3>{{ film.name }}</h3>
        <img v-if="film.url" :src="`/${film.url}`" :alt="film.name" class="film-image"/>

        <p><strong>Date de sortie :</strong> {{ film.release_date }}</p>
        <p>{{ film.description }}</p>
        <!--boucle for pour parcourir les commentaires correspondant a ce film -->
        <h4>Commentaires</h4>
        <ul>
          <li v-for="commentaire in commentaires[film.id]" :key="commentaire.id">
            {{ commentaire.corps }}
          </li>
          <!--Button qui redirige vers forum pour laisser un commentaire-->
          <button @click="redirectToCommentPage(film.id)">Laisser un commentaire</button>
          <!--Boutton pour rajout qu panier-->
          <button @click="ajouterAuPanier(film.id)">Ajouter au panier</button>
        </ul>

      </div>
    </div>
  </div>
</template>


<style>
.film-list-container {
  max-width: 1200px; /* Limite la largeur maximale */
  margin: 0 auto; /* Centre le conteneur */
  padding: 20px;
}

.films {
  display: flex;
  flex-wrap: wrap; /* Permet aux cartes de passer à la ligne suivante si nécessaire */
  gap: 20px; /* Espacement entre les cartes */
  justify-content: center; /* Centre les cartes horizontalement */
}

.film-card {
  flex: 0 1 300px; /* Les cartes peuvent rétrécir jusqu'à 300px et grandir si nécessaire */
  background: #fff; /* Arrière-plan blanc */
  box-shadow: 0 2px 5px rgba(0,0,0,0.1); /* Ombre subtile pour la profondeur */
  padding: 20px;
  border-radius: 5px; /* Bordures arrondies */
}

.film-card h3 {
  margin-top: 0;
}

.film-photos img {
  max-width: 100%; /* Assure que les images ne dépassent pas de leurs conteneurs */
  height: auto; /* Maintient le ratio aspect des images */
  margin-top: 10px; /* Espacement entre le texte et les images */
  border-radius: 5px; /* Bordures arrondies pour les images */
}

/* Ajoutez des styles supplémentaires ici selon vos préférences */
</style>

