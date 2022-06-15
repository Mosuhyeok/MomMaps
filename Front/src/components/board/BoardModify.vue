<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <b-row>
      <b-col>
        <b-alert show><h3>게시글 수정</h3></b-alert>
      </b-col>
    </b-row>

    <!--=글작성 로직-->
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form-group id="userid-group" label="작성자:" label-for="userid">
          <b-form-input
            id="userid"
            v-model="article.author"
            type="text"
            required
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="content"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="article.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1" @click="update"
          >글수정</b-button
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";
export default {
  name: "BoardWrite",

  data() {
    return {
      article: {},
    };
  },
  created() {
    axios
      .get(`http://localhost:8080/post/${this.$route.params.id}`)
      .then(({ data }) => {
        this.article = data.postDto;
        this.comment = [...this.comment, ...data.commentDtos];
      });
  },
  methods: {
    moveHome: function () {
      this.$router.push({ name: "home" });
    },
    moveList: function () {
      // 이거 추가
      this.$router.push({ name: "boardList" });
    },
    update: function () {
      var post = this.article;
      var url = "http://localhost:8080/post/" + this.$route.params.id;
      axios({
        url: url,
        method: "put",
        data: post,
      })
        .then(() => {
          alert("글이 수정됐습니다");
          this.moveList(); // 일단 List로 보내는데, 수정된 글로 보내는게 더 좋을듯.
        })
        .catch(() => {
          console.log("업데이트에 오류가 발생했습니다");
        });
    },
  },
  components: {},
};
</script>

<style></style>
