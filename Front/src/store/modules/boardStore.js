import {
  getfreeboardpage,
  getfreeboardcnt,
  getbestboardpage,
  getbestboardcnt,
  geteduboardpage,
  geteduboardcnt,
  getbuyboardpage,
  getbuyboardcnt,
} from "@/api/board.js";

const boardStore = {
  namespaced: true,
  state: {
    freeboardCnt: 0,
    bestboardCnt: 0,
    buyboardCnt: 0,
    eduboardCnt: 0,
    freeArticles: [], // 자유게시판
    bestArticles: [], // 베스트게시판
    buyArticles: [], // 공구 게시판
    eduArticles: [], // 교육 게시판
  },
  mutations: {
    GET_FREE_BOARD_CNT: (state, cnt) => {
      state.freeboardCnt = cnt;
    },
    GET_FREE_BOARD_PAGE: (state, articles) => {
      state.freeArticles = [];
      articles.forEach((article) => {
        state.freeArticles.push({
          id: article.id,
          author: article.author,
          title: article.title,
          content: article.content,
          view: article.view,
          recommend: article.recommend,
        });
      });
    },
    GET_BEST_BOARD_CNT: (state, cnt) => {
      state.bestboardCnt = cnt;
    },
    GET_BEST_BOARD_PAGE: (state, articles) => {
      state.bestArticles = [];
      articles.forEach((article) => {
        state.bestArticles.push({
          id: article.id,
          author: article.author,
          title: article.title,
          content: article.content,
          view: article.view,
          recommend: article.recommend,
        });
      });
    },
    GET_BUY_BOARD_CNT: (state, cnt) => {
      state.buyboardCnt = cnt;
    },
    GET_BUY_BOARD_PAGE: (state, articles) => {
      state.buyArticles = [];
      articles.forEach((article) => {
        state.buyArticles.push({
          id: article.id,
          author: article.author,
          title: article.title,
          content: article.content,
          view: article.view,
          recommend: article.recommend,
        });
      });
    },
    GET_EDU_BOARD_CNT: (state, cnt) => {
      state.eduboardCnt = cnt;
    },
    GET_EDU_BOARD_PAGE: (state, articles) => {
      state.eduArticles = [];
      articles.forEach((article) => {
        state.eduArticles.push({
          id: article.id,
          author: article.author,
          title: article.title,
          content: article.content,
          view: article.view,
          recommend: article.recommend,
        });
      });
    },
  },
  actions: {
    freeboardcount: ({ commit }) => {
      getfreeboardcnt(
        ({ data }) => {
          commit("GET_FREE_BOARD_CNT", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
    freeboardpage: ({ commit }, page) => {
      const params = {
        page: page,
      };
      // 자유게시판
      getfreeboardpage(
        params,
        ({ data }) => {
          commit("GET_FREE_BOARD_PAGE", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
    bestboardcount: ({ commit }) => {
      getbestboardcnt(
        ({ data }) => {
          commit("GET_BEST_BOARD_CNT", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
    bestboardpage: ({ commit }, page) => {
      const params = {
        page: page,
      };
      // 자유게시판
      getbestboardpage(
        params,
        ({ data }) => {
          commit("GET_BEST_BOARD_PAGE", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
    eduboardcount: ({ commit }) => {
      geteduboardcnt(
        ({ data }) => {
          commit("GET_EDU_BOARD_CNT", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
    eduboardpage: ({ commit }, page) => {
      const params = {
        page: page,
      };
      // 자유게시판
      geteduboardpage(
        params,
        ({ data }) => {
          commit("GET_EDU_BOARD_PAGE", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
    buyboardcount: ({ commit }) => {
      getbuyboardcnt(
        ({ data }) => {
          commit("GET_BUY_BOARD_CNT", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
    buyboardpage: ({ commit }, page) => {
      const params = {
        page: page,
      };
      // 자유게시판
      getbuyboardpage(
        params,
        ({ data }) => {
          commit("GET_BUY_BOARD_PAGE", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
  },
};

export default boardStore;
