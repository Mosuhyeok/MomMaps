import { getbooklist, getschoollist } from "@/api/book.js";

const BookStore = {
  namespaced: true,
  state: {
    booklists: [],
    schoollists: [],
  },
  mutations: {
    SET_BOOK_LIST: (state, booklists) => {
      state.booklists = booklists;
    },
    SET_SCHOOL_LIST: (state, schoollists) => {
      state.schoollists = schoollists;
    },
  },
  actions: {
    getBookList: ({ commit }) => {
      getbooklist(
        ({ data }) => {
          commit("SET_BOOK_LIST", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
    getSchoolList: ({ commit }) => {
      getschoollist(
        ({ data }) => {
          commit("SET_SCHOOL_LIST", data);
        },
        ({ error }) => {
          console.log(error);
        }
      );
    },
  },
};

export default BookStore;
