<template>
  <div>
    <div class="bg-white">
      <div>
        <!-- Offer -->
        <div class="d-flex justify-content-center">
          <div class="p-5 w-50 text-center">
            <h1 class="text-deepgreen fw-bold mt-4">아이를 위한 최고의 집을 찾아줄게요.</h1>
            <h6 class="text-deepgreen">믿고 맡겨주세요!</h6>
          </div>
        </div>
        <!-- Services -->
        <div class="container">
          <section class="section section-lg pt-lg-0">
            <div class="container">
              <div class="row justify-content-center">
                <div class="col-lg-12">
                  <div class="row row-grid">
                    <div class="col-lg-4">
                      <a href="/house">
                        <card class="border-0" hover shadow body-classes="py-5">
                          <icon name="ni ni-building" type="info" rounded class="mb-4"> </icon>
                          <h5 class="text-black text-uppercase">주택 검색</h5>
                          <hr>
                          <p class="mt-3" style="color:black;">
                            <b>특정 지역의 주택을 원하시나요? <br> 클릭하여 확인해보세요!</b>
                          </p>
                        </card>
                      </a>
                    </div>
                    <div class="col-lg-4">
                      <a href="/test">
                        <card class="border-0" hover shadow body-classes="py-5">
                          <icon name="ni ni-map-big" type="success" rounded class="mb-4"> </icon>
                          <h5 class="text-black text-uppercase">상권 검색</h5>
                          <hr>
                          <p class="mt-3" style="color:black;">
                            <b>주변 상권이 궁금하신가요? <br> 클릭하여 확인해보세요!</b>
                          </p>
                        </card>
                      </a>
                    </div>
                    <div class="col-lg-4">
                      <a href="/board">
                        <card class="border-0" hover shadow body-classes="py-5">
                          <icon name="ni ni-chat-round" type="warning" rounded class="mb-4"> </icon>
                          <h5 class="text-black text-uppercase">커뮤니티</h5>
                          <hr>
                          <p class="mt-3" style="color:black;">
                            <b>다양한 의견을 나누어보세요! <br> 알찬 정보가 가득!</b>
                          </p>
                        </card>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
        </div>
      </div>
    </div>
    <br>
  </div>
  <!-- Coffee Rates -->
</template>

<script>
import { reactive, toRefs } from "vue";
export default {
  name: "SectionView",
  setup() {
    let caffee;
    try {
      //Check availability
      caffee = reactive({
        caffeebg1: require("@/assets/caffee2.jpg"),
        caffeebg: require("@/assets/caffee3.jpg"),
      });
    } catch (error) {
      console.error(error);
    }

    return {
      ...toRefs(caffee),
    };
  },
};
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css2?family=Dancing+Script&family=Lato:wght@300&family=Varela+Round&display=swap");
.mt-global {
  padding-top: 500px;
}
.yellow-hover:hover {
  background-color: #534164;
  color: #2a2a2a;
}
.dark-hover:hover {
  background-color: #2a2a2a;
  color: #534164;
}
.text-2A {
  color: #2a2a2a;
}
.bg-deepgreen {
  background: #2b463C;
}
.text-deepgreen {
  color: #2b463C;
}
.Computercard {
  width: 60%;
  margin-left: 19%;
}
ul li {
  font-family: "Dancing Script", cursive;
  font-family: "Lato", sans-serif;
  font-family: "Varela Round", sans-serif;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}
.rounded1 {
  border-radius: 30px;
}
.bg-other1 {
  background-color: #2b463C;
}
.h-small {
  background-size: cover;
}
.card-title {
  color: #2b463C;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}
</style>
