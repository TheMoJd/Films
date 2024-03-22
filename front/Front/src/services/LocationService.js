// noinspection LanguageDetectionInspection

import axios from 'axios';

const API_LOCATION_URL = 'http://localhost:8080/rentals'; // URL de l'API des locations

class LocationService {
    getAllLocations() {
        return axios.get(API_LOCATION_URL);
    }

    getLocationById(id) {
        return axios.get(`${API_LOCATION_URL}/${id}`);
    }

    getLocationsByUserId(user_id) {
        return axios.get(`${API_LOCATION_URL}/locationsByUserId/${user_id}`);
    }

    createLocation(locationData) {
        return axios.post(API_LOCATION_URL, locationData);
    }

    updateLocation(id, locationData) {
        return axios.put(`${API_LOCATION_URL}/${id}`, locationData);
    }

    deleteLocation(id) {
        return axios.delete(`${API_LOCATION_URL}/${id}`);
    }

    // Méthode pour ajouter une location à l'historique des locations d'un utilisateur
    // Ici, locationData pourrait contenir l'ID de l'utilisateur, l'ID du panier, et des détails sur les films loués
    addLocationForUser(user_id, panier_id, rentals) {

        return axios.post(`${API_LOCATION_URL}/add/${user_id}/{movie_id}/{rentals}`);
    }

    // Méthode pour retourner un film (mise à jour de l'état de la location)
    returnMovie(locationId, movieId) {
        // Cette implémentation dépend de la façon dont votre backend gère le retour des films
        // Par exemple, vous pourriez avoir un endpoint spécifique pour le retour des films
        return axios.post(`${API_LOCATION_URL}/return/${locationId}/movie/${movieId}`);
    }
}

export default new LocationService();
