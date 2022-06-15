<template>
  <b-modal
    id="modifyComment"
    centered
    title="댓글 수정"
    ok-title="등록"
    button-size="sm"
  >
    <div>
      <div class="card-body ml-1">
        <form class="form-horizontal">
          <div class="row">
            <div class="form-group col-sm-12">
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
          </div>
        </form>
      </div>
    </div>
    <template #modal-footer="{ ok, cancel }">
      <!-- Emulate built in modal footer ok and cancel button actions -->
      <b-button class="col-md-2" size="sm" variant="primary" @click="ok()">
        등록
      </b-button>
      <b-button class="col-md-2" size="sm" variant="light" @click="cancel()">
        취소
      </b-button>
    </template>
  </b-modal>
</template>
<script>
import axios from "axios";
export default {
  name: "CommentModify",
  props: ["commentId"],

  data() {
    return {
      content: "",
      author: "",
      modals: {
        modifyComment: true,
      },
    };
  },
  created() {
    this.author = this.$store.state.user.id;
    axios
      .get(`http://localhost:8080/comment/${this.$route.params.id}`)
      .then(({ data }) => {
        this.article = data.postDto;
        this.comment = [...this.comment, ...data.commentDtos];
      });
  },

  methods: {
    move: function () {
      this.$router.replace({
        name: "boardDetail",
        params: { articleno: this.postId },
      });
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
