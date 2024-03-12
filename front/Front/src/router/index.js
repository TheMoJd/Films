import { createRouter, createWebHistory} from "vue-router";
import Films from "@/components/FilmsList.vue";

const routes = [

    {
        path: "/",
        name: "Films",
        component: Films
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
