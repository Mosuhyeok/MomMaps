<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show class="bg-lGreen text-white"><h3>게시글 작성</h3></b-alert>
      </b-col>
    </b-row>

    <!--=글작성 로직-->
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form-group id="content" label="제목:" label-for="title" description="제목을 입력하세요.">
          <b-form-input id="title" v-model="title" type="text" required placeholder="제목 입력..."></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea id="content" v-model="content" placeholder="내용 입력..." rows="10" max-rows="15"></b-form-textarea>
        </b-form-group>
        <div class="text-center">
          <b-button type="submit" variant="primary" class="m-1" @click="regist">글작성</b-button>
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
        </div>
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
      tmp: this,
      author: "",
      title: "",
      content: "",
      type: "",
    };
  },
  created() {
    this.author = this.$store.state.user.id;
    this.type = this.$route.params.type;
  },
  methods: {
    moveHome: function () {
      this.$router.push({ name: "home" });
    },
    moveList: function () {
      // 이거 추가
      this.$router.push({ name: "boardList" });
    },
    regist: function () {
      var post = {
        author: this.author,
        title: this.title,
        content: this.content,
        view: 0,
        recommend: 0,
        type: this.type,
      };
      console.log(post);
      axios({
        url: "http://localhost:8080/post",
        method: "post",
        data: post,
      })
        .then(() => {
          alert("글이 등록됐습니다");
          this.moveList();
        })
        .catch(() => {
          console.log("글 동록에 오류가 발생했습니다");
        });
    },
  },
  components: {},
};
</script>

<style>
.bg-lGreen {
  background-color: #b1d182;
}
</style>
