<template>
  <div>
    <br />
    <div>
      <h4>댓글</h4>
    </div>
    <b-card class="ml-4 mr-4">
      <div v-for="item in comment" :key="item" style="text-align: left">
        <hr />
        <b-avatar class="mr-3"></b-avatar
        ><span class="mr-auto">{{ item.author }}</span>
        <p class="mt-3">{{ item.content }}</p>
        <b-col class="md-6" v-if="login && user.id == item.author">
          <div class="text-right">
            <b-button
              v-b-modal.modifyComment
              class="col-md-2"
              variant="outline-info"
              size="sm"
              @click="moveModifyComment"
              >댓글 수정</b-button
            >
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
              <template #modal-footer="{ cancel }">
                <b-button
                  class="col-md-2"
                  size="sm"
                  variant="primary"
                  @click="update(item.id)"
                >
                  등록
                </b-button>
                <b-button
                  class="col-md-2"
                  size="sm"
                  variant="light"
                  @click="cancel()"
                >
                  취소
                </b-button>
              </template>
            </b-modal>
            <b-button
              variant="outline-danger"
              size="sm"
              @click="deleteComment(item.id)"
              class="col-md-2"
              >댓글 삭제</b-button
            >
          </div>
        </b-col>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      content: "",
      author: "",
    };
  },

  props: ["comment", "postId"],

  computed: {
    login() {
      return this.$store.state.token;
    },
    user() {
      return this.$store.state.user;
    },
  },

  methods: {
    update(id) {
      let data = {
        id: id,
        author: this.user.id,
        content: this.content,
        post_id: this.postId,
      };

      var url = "http://localhost:8080/comment/" + id;
      axios({
        url: url,
        method: "put",
        data: data,
      })
        .then(() => {
          alert("댓글이 수정 됐습니다");
          window.location.reload();
        })
        .catch(() => {
          console.log("댓글 수정에 오류가 발생했습니다");
        });
    },

    deleteComment(id) {
      var url = "http://localhost:8080/comment/" + id;
      if (confirm("정말로 삭제?")) {
        axios({
          url: url,
          method: "delete",
        })
          .then(() => {
            alert("글이 삭제됐습니다");
            window.location.reload();
          })
          .catch(() => {
            console.log("글 삭제에 오류가 발생했습니다");
          });
      }
    },
  },
};
</script>
