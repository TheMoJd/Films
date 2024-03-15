<script setup>

</script>

<template>
  <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <router-link class="nav-link" to="/"><font-awesome-icon icon="home" /></router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/adminFilmList"><font-awesome-icon icon="film" /></router-link>
          </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/usersList"> <font-awesome-icon icon="users" />
                </router-link>
            </li>

          <li class="nav-item">
            <router-link class="nav-link" to="/login"><font-awesome-icon icon="user" /></router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/panier">
              <font-awesome-icon icon="shopping-cart" />
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>

</style>