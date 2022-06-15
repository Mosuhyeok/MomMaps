<template>
  <!-- <c:if test="${!empty user}"> -->
  <div v-if="login">
    <div class="card-body ml-1">
      <form class="form-horizontal">
        <div class="row">
          <div class="form-group col-sm-10">
            <input type="hidden" />
            <input
              class="form-control input-sm"
              style="height: 60px"
              id="content"
              v-model="content"
              type="text"
              placeholder="댓글 입력..."
            />
          </div>
          <div class="form-group col-sm-2">
            <b-button
              type="button"
              class="col-md-10"
              variant="primary"
              @click="regist"
              >등록</b-button
            >
          </div>
        </div>
      </form>
      <!-- <p>{{ post_id }}</p>
      <p>{{ author }}</p> -->
    </div>
  </div>
  <!-- </c:if> -->
</template>
<script>
import axios from "axios";
export default {
  name: "CommentInput",
  props: ["postId"],
  computed: {
    login() {
      return this.$store.state.token;
    },
    user() {
      return this.$store.state.user;
    },
  },
  data() {
    return {
      content: "",
      author: "",
    };
  },
  created() {
    this.author = this.$store.state.user.id;
  },

  methods: {
    move: function () {
      location.reload();
    },

    regist: function () {
      var data = {
        content: this.content,
        author: this.author,
        post_id: this.postId,
      };

      axios({
        url: "http://localhost:8080/comment/",
        method: "post",
        data: data,
      })
        .then(() => {
          alert("댓글이 등록 됐습니다");
          this.move();
        })
        .catch(() => {
          console.log("댓글 등록에 오류가 발생했습니다");
        });
    },
  },
};
</script>
