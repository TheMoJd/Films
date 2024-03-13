<script setup>

import Navbar from "@/components/Navbar.vue";
import CommentaireList from "@/components/CommentaireList.vue";
</script>

<template>
  <Navbar/>

  <router-view/>
  <CommentaireList/>
</template>