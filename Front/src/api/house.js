import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(sucess, fail) {
  api.get(`/house/sido`).then(sucess).catch(fail);
}

function gugunList(params, sucess, fail) {
  api.get(`/house/gugun`, { params: params }).then(sucess).catch(fail);
}

function dongList(params, sucess, fail) {
  api.get(`/house/dong`, { params: params }).then(sucess).catch(fail);
}

function houseList(params, sucess, fail) {
  api.get(`/house/houseList`, { params: params }).then(sucess).catch(fail);
}

function gethouseCnt(par_dong, sucess, fail) {
  api.get(`/house/houseCnt`, { params: par_dong }).then(sucess).catch(fail);
}

function houseDetail(params, sucess, fail) {
  api.get(`/house/houseDetail`, { params: params }).then(sucess).catch(fail);
}

function getAvg(params, sucess, fail) {
  api.get(`/house/houseAVG`, { params: params }).then(sucess).catch(fail);
}

function getAllList(sucess, fail) {
  api.get(`/house/houseAllList`).then(sucess).catch(fail);
}

export { getAllList, sidoList, gugunList, dongList, houseList, gethouseCnt, houseDetail, getAvg };
