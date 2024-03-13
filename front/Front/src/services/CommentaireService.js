import axios from 'axios';

const API_URL = 'http://localhost:8080/comments'; // URL de l'API correctement ajustée pour les commentaires

class CommentaireService {


    getCommentaireByMovieId(id) { // Méthode renommée pour récupérer un commentaire par son ID
        return axios.get(`${API_URL}/${id}`);
    }

    createCommentaire(commentaire) { // Changement de paramètre pour refléter la création d'un commentaire
        return axios.post(API_URL, commentaire);
    }

    updateCommentaire(id, commentaire) { // Méthode ajustée pour la mise à jour d'un commentaire
        return axios.put(`${API_URL}/${id}`, commentaire);
    }

    deleteCommentaire(id) { // Méthode renommée pour refléter la suppression d'un commentaire
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new CommentaireService(); // Ajustement du nom de l'instance exportée pour correspondre à la classe
