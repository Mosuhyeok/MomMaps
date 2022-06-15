import { apiInstance } from "./index.js";

const api = apiInstance();

function getbooklist(sucess, fail) {
  api.get(`/book/list`).then(sucess).catch(fail);
}

function getschoollist(sucess, fail) {
  api.get(`/school/list`).then(sucess).catch(fail);
}

export { getbooklist, getschoollist };
