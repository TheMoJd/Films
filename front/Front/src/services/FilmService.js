import axios from 'axios';

const API_URL = 'http://localhost:8080/films'; // Modifiez l'URL de l'API pour correspondre à l'endpoint des films

class FilmService {
    getAllFilms() {
        return axios.get(API_URL);
    }

    getFilmById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    createFilm(film) { // Changez le paramètre de 'lieu' à 'film' pour mieux refléter son but
        return axios.post(API_URL, film);
    }

    updateFilm(id, film) { // Ici aussi, changez 'lieu' en 'film'
        return axios.put(`${API_URL}/${id}`, film);
    }

    deleteFilm(id) { // Corrigez le nom de la méthode pour qu'il soit cohérent avec les opérations sur les films
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new FilmService();
