import { createRouter, createWebHistory} from "vue-router";
import Films from "@/components/FilmsListUser.vue";
import ForumCommentaire from "@/components/ForumCommentaire.vue";
import AdminFilmList from "@/components/AdminFilmList.vue";
import UsersList from "@/components/UsersList.vue";
import PanierUtilisateur from "@/components/PanierUtilisateur.vue";
import Connexion from "@/components/Connexion.vue";
import Inscription from "@/components/Inscription.vue";
import FilmsListUser from "@/components/FilmsListUser.vue";
import Logout from "@/components/Logout.vue";
import ForumAjoutFilm from "@/components/ForumAjoutFilm.vue";
import useAuthStore from "@/store/authStore.js";
import Locations from "@/components/Locations.vue";


const routes = [

    {
        path: "/",
        name: "Accueil",
        component: FilmsListUser
    },


    {
        path: "/ForumCommentaire/:movieId/:userId",
        name: "ForumCommentaire",
        component: ForumCommentaire,
        props: true

    },
    {
        path: "/adminFilmList",
        name: "AdminFilmList",
        component: AdminFilmList,
        props: true,
        meta: { requiresAdmin: true }

    },
    {
        path: "/usersList",
        name: "UsersList",
        component: UsersList,
        props: true,
        meta: { requiresAdmin: true }
    },
    {
        path: "/panier",
        name: "PanierUtilisateur",
        component: PanierUtilisateur,
        props: true

    },
    {
        path: "/connexion",
        name: "Connexion",
        component: Connexion
    },
    {
        path: "/inscription",
        name: "Inscription",
        component: Inscription

    },
    {
        path: "/logout",
        name: "Logout",
        component: Logout,
    },
    {
        path: "/ajoutFilm",
        name: "ForumAjoutFilm",
        component: ForumAjoutFilm,
        meta: { requiresAdmin: true }
    },
    {
        path: "/locations",
        name: "Locations",
        component: Locations
    }

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    const { state } = useAuthStore();
    if (to.meta.requiresAdmin && !(state.user.is_admin)) {
        return next({ path: '/' }); // Rediriger vers une route sécurisée pour les non-admins
    }
    next();
});

export default router;
