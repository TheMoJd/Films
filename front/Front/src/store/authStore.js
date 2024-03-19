import { reactive, readonly } from 'vue';
import { jwtDecode } from 'jwt-decode';

const state = reactive({
    user: null
});

function setUser(decodedToken) {
    try {
        state.user = decodedToken;
    } catch (error) {
        console.error("Erreur lors du décodage du token :", error);
    }
}

export default function useAuthStore() {
    return { state: readonly(state), setUser };
}
