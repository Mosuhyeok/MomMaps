import { apiInstance } from "./index.js";

const api = apiInstance();

function getfreeboardpage(params, sucess, fail) {
  api.get(`/post/search/free`, { params: params }).then(sucess).catch(fail);
}
function getfreeboardcnt(sucess, fail) {
  api.get(`/post/boardcnt/free`).then(sucess).catch(fail);
}

function getbestboardpage(params, sucess, fail) {
  api.get(`/best`, { params: params }).then(sucess).catch(fail);
}
function getbestboardcnt(sucess, fail) {
  api.get(`/bestcnt`).then(sucess).catch(fail);
}

function geteduboardpage(params, sucess, fail) {
  api.get(`/post/search/edu`, { params: params }).then(sucess).catch(fail);
}
function geteduboardcnt(sucess, fail) {
  api.get(`/post/boardcnt/edu`).then(sucess).catch(fail);
}

function getbuyboardpage(params, sucess, fail) {
  console.log("params : ");
  console.log(params);
  api.get(`/post/search/buy`, { params: params }).then(sucess).catch(fail);
}
function getbuyboardcnt(sucess, fail) {
  api.get(`/post/boardcnt/buy`).then(sucess).catch(fail);
}

export {
  getfreeboardpage,
  getfreeboardcnt,
  getbestboardpage,
  getbestboardcnt,
  geteduboardpage,
  geteduboardcnt,
  getbuyboardpage,
  getbuyboardcnt,
};
