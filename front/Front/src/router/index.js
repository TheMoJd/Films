import { createRouter, createWebHistory} from "vue-router";
import Films from "@/components/FilmsList.vue";
import CommentaireList from "@/components/CommentaireList.vue";
import ForumCommentaire from "@/components/ForumCommentaire.vue";

const routes = [

    {
        path: "/",
        name: "Accueil",
        component: Films
    },
    {
        path: "/commentaires",
        name: "Commentaires",
        component: CommentaireList
    },

    {
        path: "/ForumCommentaire/:movieId/:userId", // Assurez-vous que les paramètres correspondent
        name: "ForumCommentaire",
        component: ForumCommentaire,
        props: true

    }


];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
