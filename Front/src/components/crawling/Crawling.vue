<template>
  <div class="container">
      <div style="background-color: #f4f1e9;">
        <div class="p-5">
          <b-row md="12" >
            <b-col md="4">
              <base-alert style="background-color:rgb(223, 171, 149);">
                <h5 style="color: black"><b>이런 건 어때요?</b></h5>
              </base-alert>
              <div style="height:100px; width:100%">
                <b-carousel
                  id="carousel-no-animation"
                  style="text-shadow: 0px 0px 2px #000"
                  :interval="2000"
                  indicators
                >
                  <b-carousel-slide
                    img-src="https://i.ibb.co/XDfGCPk/banner-1.jpg"
                  ></b-carousel-slide>
                  <b-carousel-slide
                    img-src="https://i.ibb.co/tZFzTrD/banner-2.jpg"
                  ></b-carousel-slide>
                  <b-carousel-slide
                    img-src="https://i.ibb.co/D4fyxyT/banner-3.jpg"
                  ></b-carousel-slide>
                  <b-carousel-slide
                    img-src="https://i.ibb.co/rHpCFRy/banner-4.jpg"
                  ></b-carousel-slide>
                </b-carousel>
              </div>
            </b-col>
            
            <b-col md="4">
              <base-alert style="background-color:rgb(223, 171, 149);">
                <h5 style="color: black"><b>오늘의 부동산</b></h5>
              </base-alert>
              <div style="overflow-y: scroll; height: 300px;">
                <div v-for="item in crawlingComp" :key="item">
                  <div>
                    <b-card
                      bg-variant="#2b463C"
                      
                    >
                      <b-card-text class="text-left">
                        <a :href="item.hrefLink" style="color: #2b463c">
                        <b>{{ item.title }}</b></a
                        >
                        <br />
                        <!-- {{item.content}} -->
                      </b-card-text>
                    </b-card>
                  </div>
                </div>
              </div>
            </b-col>
  
            <b-col md="4">
              <base-alert style="background-color:rgb(223, 171, 149);">
                <h5 style="color: black"><b>공지사항</b></h5>
              </base-alert>
  
              <b-table-simple responsive style="background-color:white">
                <!-- <b-thead>
                  <b-tr>
                    <b-th>글 번호</b-th>
                    <b-th>제목</b-th>
                  </b-tr>
                </b-thead> -->
                <b-tbody>
                  <b-tr v-for="item in articles" :key="item">
                    <b-td>
                      <router-link :to="`/announcement/detail/${item.id}`">{{
                        item.id
                      }}</router-link>
                    </b-td>
                    <b-td>{{ item.title }}</b-td>
                  </b-tr>
                </b-tbody>
              </b-table-simple>
            </b-col>
          </b-row>
        </div>
      </div>
    </div>
</template>
<script>
import axios from "axios";
const cheerio = require("cheerio");
export default {
    data() {
        return {
            result: "",
            crawlingComp: [],
            articles: [],
            printArticles: [],
        };
    },
    created() {
        axios.get("http://localhost:8080/announcement/list").then((response) => {
            this.articles = [...this.articles, ...response.data];
            for (var i = 0; i < this.articles.length; i++) {
                let tmp = {
                    글번호: this.articles[i].id,
                    제목: this.articles[i].title,
                };
                this.printArticles.push(tmp);
            }
        });
        const crawlingComp = [];
        axios.get("http://localhost:8080/crawling").then((response) => {
            this.result += response.data;
            const $ = cheerio.load(this.result);
            $("li dl").each(function (i, elem) {
                crawlingComp[i] = {
                    hrefLink: $(elem).find("a").attr()["href"],
                    title: $(elem).find("a").text().trim(),
                    imgSrc: $(elem).find("img").attr("src"),
                    content: $(elem).find("dd .lede").text().trim(),
                    writing: $(elem).find("dd .writing").text().trim(),
                };
                if (crawlingComp[i].title.length > 25) {
                  crawlingComp[i].title = crawlingComp[i].title.substr(0, 25) + "...";
                }
            });
            this.crawlingComp = crawlingComp;
        });
    },
};
</script>
<style></style>
