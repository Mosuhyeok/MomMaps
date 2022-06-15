<template>
  <b-sidebar id="sidebar-backdrop" :backdrop-variant="variant" backdrop bg-variant="white">
    <template>
      <div class="p-3">
        <nav class="mb-3">
          <b-nav vertical>
            <h4 class="mt-2" style="color: black"><b>검색 분류</b></h4>
            <br />
            <router-link :to="{ name: 'house' }" style="color: black">
              <base-alert show type="secondary">
                <h6><b>주택 검색하기</b></h6>
              </base-alert>
            </router-link>
            <router-link :to="{ name: 'test' }" style="color: black">
              <base-alert show type="secondary">
                <h6><b>주변 상가 검색하기</b></h6>
              </base-alert>
            </router-link>
            <router-link :to="{ name: 'book' }" style="color: black">
              <base-alert show type="secondary">
                <h6><b>전국 서점/학교 검색</b></h6>
              </base-alert>
            </router-link>
          </b-nav>
        </nav>
      </div>
    </template>
  </b-sidebar>
</template>

<script>
export default {
  name: "sidebar",
};
</script>

<style></style>
