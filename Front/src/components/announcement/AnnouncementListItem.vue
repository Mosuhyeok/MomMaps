<template>
  <b-tr>
    <b-td>{{ id }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'announcementDetail', params: { articleno: id } }"
        >{{ title }}</router-link
      >
    </b-th>
    <b-td>{{ author }}</b-td>
  </b-tr>
</template>

<script>
export default {
  name: "AnnouncementListItem",

  props: {
    id: Number,
    author: String,
    title: String,
  },
};
</script>

<style></style>
