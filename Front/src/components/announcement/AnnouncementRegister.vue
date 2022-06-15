<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show class="bg-purple text-white"
          ><h3>공지사항 작성</h3></b-alert
        >
      </b-col>
    </b-row>

    <!--=글작성 로직-->
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form-group
          id="content"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <div class="text-center">
          <b-button type="submit" variant="primary" class="m-1" @click="regist"
            >글작성</b-button
          >
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
      author: "",
      title: "",
      content: "",
    };
  },
  created() {
    this.author = this.$store.state.user.id;
  },
  methods: {
    moveHome: function () {
      this.$router.push({ name: "home" });
    },
    moveList: function () {
      // 이거 추가
      this.$router.push({ name: "announcement" });
    },
    regist: function () {
      var post = {
        author: this.author,
        title: this.title,
        content: this.content,
      };
      axios({
        url: "http://localhost:8080/announcement",
        method: "post",
        data: post,
      })
        .then(() => {
          alert("공지사항이 등록됐습니다");
          this.moveList();
        })
        .catch(() => {
          console.log("공지사항 등록에 오류가 발생했습니다");
        });
    },
  },
  components: {},
};
</script>

<style>
.bg-purple {
  background-color: #534164;
}
</style>
