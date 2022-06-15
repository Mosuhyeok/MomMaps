import { getAllList, sidoList, gugunList, dongList, houseList, gethouseCnt, houseDetail, getAvg } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidonames: [],
    gugunnames: [],
    dongnames: [],
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    houseCnt: 0,
    dongCode: null,
    houseDetails: [],
    houseAvgs: [],

    houseNearby: [], // 집 근처 시설들

    houseallList: [],
    chart: [],
  },

  mutations: {
    CREAT_SET_CHART: (state) => {
      state.chart = state.houseDetails;
      //const tmp = state.houseDetails;
      //state.houseDetails = [];
      //state.houseDetails = tmp;
    },
    CREAL_HOUSE_DETAILS: (state) => {
      state.houseDetails = [];
    },
    CLEAR_HOUSES: (state) => {
      state.houses = [];
    },
    CLEAR_HOUSEAVGS: (state) => {
      state.houseAvgs = [];
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도 선택" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군 선택" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동 선택" }];
    },
    CLEAR_SIDO_NAME: (state) => {
      state.sidonames = [];
    },
    CLEAR_GUGUN_NAME: (state) => {
      state.gugunnames = [];
    },
    CLEAR_DONG_NAME: (state) => {
      state.dongnames = [];
    },
    SET_SIDO_LIST: (state, sidos) => {
      state.sidonames = [];
      state.sidos = [];
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
        state.sidonames.push(sido.sidoName);
      });
      //console.log("시도네임: " + state.sidoNames);
    },
    SET_GUGUN_LIST: (state, guguns) => {
      state.gugunnames = [];
      state.guguns = [];
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
        state.gugunnames.push(gugun.gugunName);
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      state.dongnames = [];
      state.dongs = [];
      dongs.forEach((dong) => {
        if (!(dong.dongName == null)) {
          state.dongs.push({ value: dong.dongCode, text: dong.dongName });
          state.dongnames.push(dong.dongName);
        }
      });
      console.log(state.dongnames[0]);
    },
    SET_HOUSE_LIST: (state, data) => {
      state.dongCode = data.dong;
      state.houses = data.data;
    },
    GET_HOUSE_CNT: (state, houseCnt) => {
      state.houseCnt = houseCnt;
    },
    GET_HOUSE_DETAIL: (state, houseDetails) => {
      state.houseDetails = [];
      houseDetails.forEach((detail) => {
        console.log("디테일: " + detail);
        const arr = detail.dealAmount.split(",");
        const amount = arr[0] + arr[1];

        state.houseDetails.push({
          aptCode: detail.aptCode,
          aptName: detail.apartmentName,
          dealAmount: amount,
          dealYear: detail.dealYear,
          dealMonth: detail.dealMonth,
          dealDay: detail.dealDay,
          area: detail.area,
          floor: detail.floor,
          lat: detail.lat,
          lng: detail.lng,
        });
      });
    },
    GET_HOUST_AVG: (state, houseAvgs) => {
      state.houseAvgs = [];

      houseAvgs.forEach((houseavg) => {
        state.houseAvgs.push({ aptName: houseavg.aptName, avg: houseavg.avg });
      });
    },

    SET_HOUSE_NEARBY: (state, data) => {
      state.houseNearby = data;
    },
    GET_ALL_LIST: (state, houseallList) => {
      state.houseallList = houseallList;
    },
  },

  actions: {
    getNearby: ({ commit }, data) => {
      console.log("data:" + data);
      commit("SET_HOUSE_NEARBY", data);
    },
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidocode) => {
      const params = {
        sido: sidocode,
      };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, guguncode) => {
      const params = {
        gugun: guguncode,
      };
      console.log("동세팅");
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    searchHouseList: ({ commit }, search) => {
      console.log("page:" + search.page);
      const params = {
        dong: search.dongCode,
        page: search.page,
      };
      const par_dong = {
        dong: search.dongCode,
      };
      houseList(
        params,
        ({ data }) => {
          const newdata = {
            data: data,
            dong: search.dongCode,
          };
          commit("SET_HOUSE_LIST", newdata);
        },
        (error) => {
          console.log(error);
        }
      );
      gethouseCnt(
        par_dong,
        ({ data }) => {
          commit("GET_HOUSE_CNT", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseDetail: ({ commit }, house) => {
      console.log(house.apartmentName);
      console.log(house.dongCode);
      const params = {
        aptName: house.apartmentName,
        dong: house.dongCode,
      };
      houseDetail(
        params,
        ({ data }) => {
          commit("GET_HOUSE_DETAIL", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseAvg: ({ commit }, dong) => {
      const params = {
        dong: dong,
      };
      getAvg(
        params,
        ({ data }) => {
          commit("GET_HOUST_AVG", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAllList: ({ commit }) => {
      getAllList(
        ({ data }) => {
          commit("GET_ALL_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
