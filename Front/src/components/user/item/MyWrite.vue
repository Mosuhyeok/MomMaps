<template>
  <div>
    <b-row>
      <b-col md="4">
        <p>{{post_id}}</p>
      </b-col>
      <b-col md="8">
        <p><router-link
          :to="{ name: 'boardDetail', params: { articleno: post_id } }"
          >{{ title }}</router-link
        ></p>
      </b-col>
    </b-row>
    <hr>
  </div>
</template>

<script>
export default {
  name: "MyWrite",

  props: {
    post_id: Number,
    title: String,
  },
};
</script>

<style scoped>
p {
  color:black;
}
</style>