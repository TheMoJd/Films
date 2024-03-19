import axios from 'axios';

const API_PANIER_URL = 'http://localhost:8080/paniers'; // Modifiez l'URL de l'API pour correspondre à l'endpoint des paniers

class PanierService {
    getAllPaniers() {
        return axios.get(API_PANIER_URL);
    }
    getPanierById(id) {
        return axios.get(`${API_PANIER_URL}/${id}`);
    }

    createPanier(panier) {
        return axios.post(API_PANIER_URL, panier);
    }

    updatePanier(id, panier) {
        return axios.put(`${API_PANIER_URL}/${id}`, panier);
    }

    deletePanier(id) {
        return axios.delete(`${API_PANIER_URL}/${id}`);
    }

    // Supposons que vous ayez des endpoints spécifiques pour ajouter ou supprimer des films d'un panier,
    // ou pour ajuster les quantités, voici comment ces appels pourraient être structurés :
    ajouterFilmAuPanier(panier_id, movie_id, quantite) {
        return axios.post(`${API_PANIER_URL}/${panier_id}/films/${movie_id}/${quantite}`);
    }

    retirerFilmDuPanier(panier_id, movie_id) {
        return axios.delete(`${API_PANIER_URL}/${panier_id}/films/${movie_id}`);
    }

    modifierQuantiteFilm(panierId, filmId, nouvelleQuantite) {
        return axios.put(`${API_PANIER_URL}/${panierId}/films/${filmId}`, { nouvelleQuantite });
    }
}

export default new PanierService();
