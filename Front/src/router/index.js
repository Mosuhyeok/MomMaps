import Vue from "vue";
import VueRouter from "vue-router";

import BoardView from "../views/BoardView";
import HomeView from "@/views/HomeView.vue";
// import LoginForm from "@/components/LoginForm.vue";
import UserRegist from "@/components/user/ModalRegist.vue";
import UserView from "../views/UserView.vue";
import UserDetail from "@/components/user/UserDetail.vue";
import ModalLogin from "@/components/user/ModalLogin.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/login",
    name: "Login",
    component: ModalLogin,
  },
  {
    path: "/board",
    name: "board",
    component: BoardView,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },

      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      // white, update 옮김.
      {
        path: "write/:type",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "update/:id",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
    ],
  },

  {
    path: "/user",
    component: UserView,
    children: [
      {
        path: "/regist",
        name: "Regist",
        component: UserRegist,
      },
      {
        path: "/detail",
        name: "Detail",
        component: UserDetail,
      },
      {
        path: "/modal",
        name: "Modal",
        component: ModalLogin,
      },
    ],
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView.vue"),
  },
  {
    path: "/housedetail",
    name: "houseDetail",
    component: () => import("@/views/HouseDetailView.vue"),
  },
  {
    path: "/test",
    name: "test",
    component: () => import("@/views/TestUI.vue"),
  },
  {
    path: "/announcement",
    name: "announcement",
    component: () => import("@/components/announcement/AnnouncementList.vue"),
  },
  {
    path: "/announcement/write",
    name: "announcementWrite",
    component: () => import("@/components/announcement/AnnouncementRegister.vue"),
  },
  {
    path: "/announcement/detail/:articleno",
    name: "announcementDetail",
    component: () => import("@/components/announcement/AnnouncementDetail.vue"),
  },
  {
    path: "/announcement/update/:articleno",
    name: "announcementModify",
    component: () => import("@/components/announcement/AnnouncementModify.vue"),
  },
  {
    path: "/book",
    name: "book",
    component: () => import("@/views/BookView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
