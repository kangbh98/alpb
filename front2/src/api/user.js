import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
    await local.post(`/users/login`, param).then(success).catch(fail);
}

async function findById(useridx, success, fail) {
    local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
    await local.get(`/users/info/${useridx}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
    local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
    await local.post(`/users/refresh`, user).then(success).catch(fail);
}

async function logout(useridx, success, fail) {
    await local.get(`/users/logout/${useridx}`).then(success).catch(fail);
}

export { userConfirm, findById, tokenRegeneration, logout };
