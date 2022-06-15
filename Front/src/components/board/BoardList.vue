<template>
  <div class="container">
    <div>
      <!-- <b-card no-body style="height: 500px"> -->
      <b-tabs card justified>
        <b-tab title="자유게시판">
          <b-row>
            <b-col md="10" style="display: flex; align-items: center">
              <h4 class="text-dark text-left mt-2" style="position: relative; width: 100%"><b>자유 게시글</b></h4>
            </b-col>
            <b-col md="2">
              <base-button class="col-md-11" type="primary" size="sm" v-if="login" @click="moveWrite(`free`)">글 쓰기</base-button>
            </b-col>
          </b-row>
          <b-row>
            <b-col v-if="freeArticles.length">
              <b-table-simple hover responsive>
                <b-thead class="bg-lGreen text-dark">
                  <b-tr>
                    <b-th>제목</b-th>
                    <b-th>작성자</b-th>
                    <b-th>조회수</b-th>
                    <b-th>추천수</b-th>
                  </b-tr>
                </b-thead>
                <tbody>
                  <!-- 하위 component인 ListRow에 데이터 전달(props) -->
                  <board-list-item v-for="article in freeArticles" :key="article.articleno" v-bind="article" />
                </tbody>
              </b-table-simple>
            </b-col>
          </b-row>
          <b-pagination v-model="currentPage" :total-rows="freeboardCnt" :per-page="perPage" align="center" @input="clickpage(`free`)" />

          <span>
            <b-form-select v-model="val" :options="vals" style="width: 100px; height: 40px" class="mr-2"> </b-form-select>
            <input style="width: 200px; height: 40px" type="text" id="freeinput" />
            <b-button variant="outline-primary" @click="search(`free`, `freeinput`)">검색</b-button>
          </span>
          <hr />
        </b-tab>
        <b-tab title="인기게시판">
          <b-row>
            <b-col style="display: flex; align-items: center">
              <h4 class="text-dark text-left mt-2"><b>인기 게시글</b></h4>
            </b-col>
            <b-col md="2">
              <base-button class="col-md-11" type="secondary" size="sm" v-if="login" disabled>글 쓰기</base-button>
            </b-col>
          </b-row>
          <b-row>
            <b-col v-if="bestArticles.length">
              <b-table-simple hover responsive>
                <b-thead class="bg-lGreen text-dark">
                  <b-tr>
                    <b-th>제목</b-th>
                    <b-th>작성자</b-th>
                    <b-th>조회수</b-th>
                    <b-th>추천수</b-th>
                  </b-tr>
                </b-thead>
                <tbody>
                  <!-- 하위 component인 ListRow에 데이터 전달(props) -->
                  <board-list-item v-for="article in bestArticles" :key="article.articleno" v-bind="article" />
                </tbody>
              </b-table-simple>
            </b-col>
          </b-row>
          <b-pagination v-model="currentPage" :total-rows="freeboardCnt" :per-page="perPage" align="center" @input="clickpage" />

          <span>
            <b-form-select v-model="val" :options="vals" style="width: 100px; height: 35px"> </b-form-select>
            <input type="text" id="bestinput" />
            <b-button variant="outline-primary" @click="search(`buy`, `bestinput`)">검색</b-button>
          </span>
          <hr
        /></b-tab>
        <b-tab title="교육게시판">
          <b-row>
            <b-col md="10" style="display: flex; align-items: center">
              <h4 class="text-dark text-left mt-2"><b>교육 게시글</b></h4>
            </b-col>
            <b-col md="2">
              <base-button class="col-md-11" type="primary" size="sm" v-if="login" @click="moveWrite(`edu`)">글 쓰기</base-button>
            </b-col>
          </b-row>
          <b-row>
            <b-col v-if="eduArticles.length">
              <b-table-simple hover responsive>
                <b-thead class="bg-lGreen text-dark">
                  <b-tr>
                    <b-th>제목</b-th>
                    <b-th>작성자</b-th>
                    <b-th>조회수</b-th>
                    <b-th>추천수</b-th>
                  </b-tr>
                </b-thead>
                <tbody>
                  <!-- 하위 component인 ListRow에 데이터 전달(props) -->
                  <board-list-item v-for="article in eduArticles" :key="article.articleno" v-bind="article" />
                </tbody>
              </b-table-simple>
            </b-col>
          </b-row>
          <b-pagination v-model="currentPage" :total-rows="eduboardCnt" :per-page="perPage" align="center" @input="clickpage(`edu`)" />
          <span>
            <b-form-select v-model="val" :options="vals" style="width: 100px; height: 35px"> </b-form-select>
            <input type="text" id="eduinput" />
            <b-button variant="outline-primary" @click="search(`edu`, `eduinput`)">검색</b-button>
          </span>
          <hr />
        </b-tab>
        <b-tab title="공구게시판">
          <b-row>
            <b-col style="display: flex; align-items: center">
              <h4 class="text-dark text-left mt-2"><b>공구 게시글</b></h4>
            </b-col>
            <b-col md="2">
              <base-button class="col-md-11" type="primary" size="sm" v-if="login" @click="moveWrite(`buy`)">글 쓰기</base-button>
            </b-col>
          </b-row>
          <b-row>
            <b-col v-if="buyArticles.length">
              <b-table-simple hover responsive>
                <b-thead class="bg-lGreen text-dark">
                  <b-tr>
                    <b-th>제목</b-th>
                    <b-th>작성자</b-th>
                    <b-th>조회수</b-th>
                    <b-th>추천수</b-th>
                  </b-tr>
                </b-thead>
                <tbody>
                  <!-- 하위 component인 ListRow에 데이터 전달(props) -->
                  <board-list-item v-for="article in buyArticles" :key="article.articleno" v-bind="article" />
                </tbody>
              </b-table-simple>
            </b-col>
          </b-row>
          <b-pagination v-model="currentPage" :total-rows="buyboardCnt" :per-page="perPage" align="center" @input="clickpage(`buy`)" />
          <span>
            <b-form-select v-model="val" :options="vals" style="width: 100px; height: 35px"> </b-form-select>
            <input type="text" id="buyinput" />
            <b-button variant="outline-primary" @click="search(`buy`, `buyinput`)">검색</b-button>
          </span>
          <hr
        /></b-tab>
      </b-tabs>
      <!-- </b-card> -->
    </div>
  </div>
</template>

<script>
import BoardListItem from "@/components/board/item/BoardListItem";
import axios from "axios";
import { mapActions, mapState, mapMutations } from "vuex";

const boardStore = "boardStore";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  computed: {
    ...mapState(boardStore, ["freeArticles", "freeboardCnt", "bestArticles", "bestboardCnt", "eduArticles", "eduboardCnt", "buyArticles", "buyboardCnt"]),
    login() {
      return this.$store.state.token;
    },
  },
  data() {
    return {
      articles: [],
      val: "제목",
      vals: ["제목", "글쓴이"],
      currentPage: 1,
      perPage: 5,
    };
  },
  created() {
    var page = 0;
    this.freeboardcount();
    this.freeboardpage(page);
    this.bestboardcount();
    this.bestboardpage(page);
    this.eduboardcount();
    this.eduboardpage(page);
    this.buyboardcount();
    this.buyboardpage(page);
  },
  methods: {
    ...mapActions(boardStore, ["freeboardpage", "freeboardcount", "bestboardpage", "bestboardcount", "eduboardpage", "eduboardcount", "buyboardpage", "buyboardcount"]),
    ...mapMutations(boardStore, ["GET_FREE_BOARD_PAGE", "GET_BEST_BOARD_PAGE", "GET_BUY_BOARD_PAGE", "GET_EDU_BOARD_PAGE"]),
    moveWrite: function (type) {
      this.$router.push({ name: "boardRegister", params: { type: type } });
    },
    reload: function () {
      window.location.reload();
    },
    search: function (postType, name) {
      let title = document.getElementById(name).value;
      let type = this.val;

      if (type == null) {
        alert("검색 타입을 정해주세요");
      } else if (type == "글쓴이") {
        this.searchByAuthor(postType, title);
      } else {
        this.searchByTitle(postType, title);
      }
    },

    searchByTitle: function (type, title) {
      var url = "http://localhost:8080/post/searchByTitle/" + type + "/" + title;

      axios({
        url: url,
        method: "get",
      })
        .then((response) => {
          if (response.data.length == 0) {
            alert("해당하는 제목의 게시글이 없습니다");
            return;
          }
          this.articles = [];
          this.articles = [...this.articles, ...response.data];

          if (type == "free") {
            this.GET_FREE_BOARD_PAGE(this.articles);
          }
          if (type == "edu") {
            this.GET_EDU_BOARD_PAGE(this.articles);
          }
          if (type == "buy") {
            this.GET_BUY_BOARD_PAGE(this.articles);
          }
        })
        .catch(() => {
          alert("오류가 발생했습니다");
        });
    },

    searchByAuthor: function (type, author) {
      var url = "http://localhost:8080/post/searchByAuthor/" + type + "/" + author;
      axios({
        url: url,
        method: "get",
      })
        .then((response) => {
          if (response.data.length == 0) {
            alert("해당하는 유저가 쓴 글이 없습니다");
            return;
          }

          this.articles = [];
          this.articles = [...this.articles, ...response.data];

          if (type == "free") {
            this.GET_FREE_BOARD_PAGE(this.articles);
          }
          if (type == "edu") {
            this.GET_EDU_BOARD_PAGE(this.articles);
          }
          if (type == "buy") {
            this.GET_BUY_BOARD_PAGE(this.articles);
          }
        })
        .catch(() => {
          alert("오류가 발생했습니다");
        });
    },
    clickpage(type) {
      var page = (this.currentPage - 1) * this.perPage;
      this.freeboardcount();
      this.freeboardpage(page);
      if (type == "free") {
        this.freeboardcount();
        this.freeboardpage(page);
      }
      if (type == "edu") {
        this.eduboardcount();
        this.eduboardpage(page);
      }
      if (type == "buy") {
        this.buyboardcount();
        this.buyboardpage(page);
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
