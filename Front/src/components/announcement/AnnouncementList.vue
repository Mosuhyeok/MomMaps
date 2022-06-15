<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <br />
      <b-row>
        <b-col>
          <b-alert show class="bg-lGreen"
            ><h4 class="text-dark"><b>공지사항</b></h4></b-alert
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col v-if="articles.length">
          <b-table-simple hover responsive>
            <b-thead class="bg-lGreen text-dark">
              <b-tr>
                <b-th>글번호</b-th>
                <b-th>제목</b-th>
                <b-th>작성자</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <!-- 하위 component인 ListRow에 데이터 전달(props) -->
              <announcement-list-item
                v-for="article in articles"
                :key="article.articleno"
                v-bind="article"
              />
            </tbody>
          </b-table-simple>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right mb-5">
          <b-button
            variant="outline-primary"
            v-if="login && user.id == 'admin'"
            @click="moveWrite()"
            >글쓰기</b-button
          >
        </b-col>
      </b-row>

      <span>
        <b-form-select
          v-model="val"
          :options="vals"
          style="width: 100px; height: 35px"
        >
        </b-form-select>
        <input type="text" id="input" />
        <b-button variant="outline-primary" @click="search()">검색</b-button>
      </span>

      <br /><br /><br />
    </b-container>
    <footer-view></footer-view>
  </div>
</template>

<script>
import AnnouncementListItem from "@/components/announcement/AnnouncementListItem";
import axios from "axios";
import FooterView from "../layout/FooterView.vue";

export default {
  name: "AnnouncementList",
  components: {
    AnnouncementListItem,
    FooterView,
  },

  computed: {
    login() {
      return this.$store.state.token;
    },
    user() {
      console.log(this.$store.state.user);
      return this.$store.state.user;
    },
  },

  data() {
    return {
      val: "제목",
      vals: ["제목"],
      articles: [],
    };
  },
  created() {
    axios.get("http://localhost:8080/announcement/list").then((response) => {
      this.articles = [...this.articles, ...response.data];
    });
  },
  methods: {
    moveWrite: function () {
      this.$router.push("/announcement/write");
    },
    search: function () {
      let title = document.getElementById("input").value;

      let type = this.val;

      if (type == null) {
        alert("검색 타입을 정해주세요");
      } else {
        this.searchByTitle(title);
      }
    },

    searchByTitle: function (title) {
      var url = "http://localhost:8080/announcement/searchByTitle/" + title;
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
        })
        .catch(() => {
          console.log("제목으로 검색에 오류가 발생했습니다");
        });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.bg-lGreen {
  background-color: #b1d182;
}
</style>
