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
        path: "/forumCommentaire/:movie_id",
        name: "ForumCommentaire",
        component: ForumCommentaire

    }


];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
