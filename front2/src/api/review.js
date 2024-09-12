import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function postReview(param, success, fail) {
    local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
    await local.post(`/reviews/write`, param).then(success).catch(fail);
}

async function getReview(placeName) {
    local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
    try {
        const response = await local.get(`/reviews/${placeName}`);
        return response.data; // 서버로부터 받은 데이터를 반환
    } catch (error) {
        console.error("Error fetching reviews:", error);
        throw error; // 에러를 던져서 외부에서 캐치할 수 있도록 함
    }
}

export { postReview, getReview };