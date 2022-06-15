<template>
  <b-tr>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardDetail', params: { articleno: id } }"
        >{{ title }} [{{ num }}]
      </router-link>
    </b-th>
    <b-td>{{ author }}</b-td>
    <b-td>{{ view }}</b-td>
    <b-td>{{ recommend }}</b-td>
  </b-tr>
</template>

<script>
import axios from "axios";
export default {
  name: "BoardListItem",
  data() {
    return {
      num: 0,
    };
  },
  props: {
    id: Number,
    author: String,
    title: String,
    view: Number,
    recommend: Number,
  },

  created() {
    axios
      .get(`http://localhost:8080/post/commentCnt/${this.id}`)
      .then(({ data }) => {
        this.num = data;
      });
  },
};
</script>

<style></style>
