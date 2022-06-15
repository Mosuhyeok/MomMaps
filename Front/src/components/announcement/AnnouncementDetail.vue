<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show class="bg-lGreen mt-4"
          ><h3 class="text-white">공지사항</h3></b-alert
        >
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
      <b-col class="md-6" v-if="login && user.id == article.author">
        <div class="text-right">
          <b-button
            class="col-md-2"
            variant="outline-info"
            size="sm"
            @click="moveModifyArticle"
            >글수정</b-button
          >
          <b-button
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
          ${article.title} </h3><div><h6>${author}</div><div></h6></div>`"
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
  </b-container>
</template>

<script>
// import moment from "moment";
import axios from "axios";
export default {
  name: "announcementBoardDetail",

  data() {
    return {
      article: {},
      author: "관리자",
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    login() {
      return this.$store.state.token;
    },
    user() {
      console.log(this.$store.state.user);
      return this.$store.state.user;
    },
  },
  created() {
    axios
      .get(`http://localhost:8080/announcement/${this.$route.params.articleno}`)
      .then(({ data }) => {
        this.article = data;
      });
  },
  methods: {
    moveHome: function () {
      this.$router.push({ name: "home" });
    },
    moveList: function () {
      // 이거 추가
      this.$router.push({ name: "announcement" });
    },
    listArticle() {
      this.$router.push({ name: "announcement" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "announcementModify",
        params: { id: this.article.id },
      });
    },
    deleteArticle() {
      var id = this.$route.params.articleno;
      var url = "http://localhost:8080/announcement/" + id;
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
            console.log("게시글 삭제에 오류가 발생했습니다");
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
