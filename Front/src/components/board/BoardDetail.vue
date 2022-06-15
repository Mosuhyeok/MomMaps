<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show class="bg-lGreen mt-4">
          <h4 class="text-dark"><b>게시글 보기</b></h4>
        </b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-0">
      <b-col class="md-6">
        <div class="text-left">
          <b-button
            class="col-md-2"
            variant="outline-primary"
            size="sm"
            @click="listArticle"
            >목록</b-button
          >
        </div>
      </b-col>
      <b-col class="md-6" v-if="login">
        <div class="text-right">
          <b-button
            class="col-md-2"
            variant="outline-info"
            size="sm"
            @click="recommend"
            >추천</b-button
          >
          <b-button
            v-if="user.id == article.author"
            class="col-md-2"
            variant="outline-info"
            size="sm"
            @click="moveModifyArticle"
            >글수정</b-button
          >
          <b-button
            v-if="user.id == article.author"
            variant="outline-danger"
            size="sm"
            @click="deleteArticle"
            class="col-md-2"
            >글삭제</b-button
          >
        </div>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.id}.
          ${article.title} </h3><div><h6>${article.author}</div><div></h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <input type="hidden" :post_id="article.id" />
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <div>
      <div class="bv-example-row mt-5 mb-5">
        <div style="background-color: rgb(247, 247, 247)">
          <!-- 댓글 목록-->
          <comment-view :comment="comment" :postId="article.id"></comment-view>
          <!-- 댓글 작성-->
          <comment-input :postId="article.id"></comment-input>
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
// import moment from "moment";
import axios from "axios";
import CommentInput from "../comment/CommentInput.vue";
import CommentView from "../comment/CommentView.vue";
export default {
  name: "BoardDetail",
  components: {
    CommentInput,
    CommentView,
  },
  data() {
    return {
      article: {},
      comment: [],
    };
  },
  computed: {
    login() {
      return this.$store.state.token;
    },
    user() {
      console.log(this.$store.state.user);
      return this.$store.state.user;
    },

    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    axios
      .get(`http://localhost:8080/post/${this.$route.params.articleno}`)
      .then(({ data }) => {
        this.article = data.postDto;
        this.comment = [...this.comment, ...data.commentDtos];
      });
  },
  methods: {
    recommend: function () {
      var id = this.$route.params.articleno;
      var url = "http://localhost:8080/post/recommend/" + id;

      axios({
        url: url,
        method: "post",
      })
        .then(() => {
          alert("추천 하였습니다");
          location.reload();
        })
        .catch(() => {
          console.log("추천에 오류가 발생했습니다");
        });
    },

    moveHome: function () {
      this.$router.push({ name: "home" });
    },
    moveList: function () {
      // 이거 추가
      this.$router.push({ name: "boardList" });
    },
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { id: this.article.id },
      });
    },
    deleteArticle() {
      var id = this.$route.params.articleno;
      var url = "http://localhost:8080/post/" + id;
      if (confirm("정말로 삭제?")) {
        axios({
          url: url,
          method: "delete",
        })
          .then(() => {
            alert("글이 삭제됐습니다");
            this.moveList();
          })
          .catch(() => {
            console.log("삭제에 오류가 발생했습니다");
          });
      }
    },
  },
};
</script>

<style>
.bg-lGreen {
  background-color: #b1d182;
}
</style>
