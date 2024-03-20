<script setup>
import useAuthStore from "@/store/authStore.js";
import { computed } from "vue";
const { state } = useAuthStore();
const user_id = computed(() => state.user ? state.user.user_id : 'Invité');

const isAdmin = computed(() => state.user && state.user.is_admin);
const isActive = computed(() => state.user && state.user.active);

</script>

<template>
  <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <router-link class="nav-link" to="/" v-if="!isAdmin"><font-awesome-icon icon="home" /></router-link>
          </li>
          <li class="nav-item" v-if="isActive && isAdmin">
            <router-link class="nav-link" to="/adminFilmList"><font-awesome-icon icon="film" /></router-link>
          </li>

          <li class="nav-item" v-if="isActive && isAdmin">
            <router-link class="nav-link" to="/usersList"> <font-awesome-icon icon="users" />
              </router-link>
          </li>

          <li class="nav-item" >
            <router-link class="nav-link" to="/connexion"  v-if="user_id==='Invité' "><font-awesome-icon icon="user" /></router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/inscription" v-if="user_id==='Invité' ">Inscription</router-link>
          </li>

          <li class="nav-item" v-if="user_id!=='Invité' && !isAdmin" >
            <router-link class="nav-link" to="/panier">
              <font-awesome-icon icon="shopping-cart" />
            </router-link>
          </li>

          <li class="nav-item">
            <router-link class="nav-link" to="/logout" v-if="user_id!=='Invité' && isActive">Logout</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>

</style>