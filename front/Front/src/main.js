import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router/index.js"
import { library } from '@fortawesome/fontawesome-svg-core'
import {
    faShoppingCart,
    faUsers,
    faUser,
    faFilm,
    faHome,
    faComment,
    faTrash,
    faEdit,
    faPlus,
    faMinus,
    faSearch,
    faUserPlus,
    faUserMinus,
    faUserEdit,
    faUserSlash,
    faUserCheck,
    faUserTimes,
    faTimes
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import './assets/style.css'
import 'bootstrap/dist/css/bootstrap.css'
import "../node_modules/bootswatch/dist/Flatly/bootstrap.min.css"

// Ajouter les icônes à la bibliothèque FontAwesome
library.add(faShoppingCart, faTimes,faUsers, faUser, faFilm, faHome, faComment, faTrash, faEdit, faPlus, faMinus, faSearch, faUserPlus, faUserMinus, faUserEdit, faUserSlash, faUserCheck, faUserTimes)

// Créer l'instance de l'application
const app = createApp(App)

// Enregistrer FontAwesomeIcon comme un composant global
app.component('font-awesome-icon', FontAwesomeIcon)

// Utiliser le routeur
app.use(router)

// Monter l'application Vue
app.mount('#app')
