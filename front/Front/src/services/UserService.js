import axios from 'axios';

const API_URL = 'http://localhost:8080/users';

class UserService {
    // Méthode pour s'identifier
    login(userDto) {
        return axios.post(`${API_URL}/login`, userDto);
    }

    // Méthode pour récupérer tous les utilisateurs
    getAllUsers() {
        return axios.get(API_URL);
    }

    // Méthode pour récupérer un utilisateur par son ID
    getUserById(userId) {
        return axios.get(`${API_URL}/${userId}`);
    }

    // Méthode pour créer un nouvel utilisateur
    createUser(userDto) {
        return axios.post(API_URL, userDto);
    }

    // Méthode pour mettre à jour un utilisateur
    updateUser(userId, userDto) {
        return axios.put(`${API_URL}/update/${userId}`, userDto);
    }

    // Méthode pour désactiver (ou supprimer) un utilisateur
    deactivateUser(userId) {
        // Supposons qu'il existe un endpoint spécifique pour la désactivation
        return axios.put(`${API_URL}/deactivate/${userId}`);
    }
}

export default new UserService();
