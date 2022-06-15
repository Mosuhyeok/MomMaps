<template>
  <div class="mt-5">
    <div class="bg-deepgreen">
      <div class="container">
        <div class="d-flex justify-content-center">
          <div class="p-5 w-50 text-center">
            <h5 class="text-white text-capitalize">24/7 WITH YOU</h5>
            <h1 class="text-white text-capitalize fw-bold">FOLLOW US </h1>
            <h6 class="text-white">(강)호모싸피엔스</h6>
          </div>
        </div>
      </div>
    </div>
    <div class="bg-other">
      <div class="container p-2">
        <div class="container-fuiled">
          <!-- row -->
          <div class="row">
            <!-- Privacy Policy link and Terms Condition link column -->
            <div class="col-sm p-3 text-white text-center">
              <div class="col">
                <a href="" class="text-decoration-none text-white pe-3">Privacy Policy </a>
                <a href="" class="text-decoration-none text-white ps-3"> Terms Condition</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
export default {
  name: "FooterView",
  setup() {
    //Copyright Date
    const date = ref(new Date().getFullYear());
    return {
      date,
    };
  },
};
</script>

<style scoped>
.bg-other {
  background-color: #2b463C;
}
.bg-deepgreen {
  background-color: #2b463C;
}
</style>
