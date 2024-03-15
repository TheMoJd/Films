import { createRouter, createWebHistory} from "vue-router";
import Films from "@/components/FilmsList.vue";
import ForumCommentaire from "@/components/ForumCommentaire.vue";
import AdminFilmList from "@/components/AdminFilmList.vue";
import UsersList from "@/components/UsersList.vue";

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

    }

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
