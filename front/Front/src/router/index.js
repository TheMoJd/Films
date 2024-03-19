import { createRouter, createWebHistory} from "vue-router";
import Films from "@/components/FilmsListUser.vue";
import ForumCommentaire from "@/components/ForumCommentaire.vue";
import AdminFilmList from "@/components/AdminFilmList.vue";
import UsersList from "@/components/UsersList.vue";
import PanierUtilisateur from "@/components/PanierUtilisateur.vue";
import Connexion from "@/components/Connexion.vue";
import Inscription from "@/components/Inscription.vue";

const routes = [

    {
        path: "/",
        name: "Accueil",
        component: Films
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
        props: true

    },
    {
        path: "/usersList",
        name: "UsersList",
        component: UsersList,
        props: true

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

    }

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
