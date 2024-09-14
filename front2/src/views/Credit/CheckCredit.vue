<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios'; // axios를 사용하여 API 호출
import DefaultInfoCard from "@/examples/cards/infoCards/DefaultInfoCard.vue";
import DefaultNavbar from "@/examples/navbars/NavbarDefault.vue";
import { useUserStore } from "@/stores/user"; // Pinia 스토어에서 로그인 정보 및 크레딧 정보 가져옴
import { useRouter } from 'vue-router';

// Vue Router와 Pinia 스토어 초기화
const router = useRouter();
const userStore = useUserStore();

// 사용자의 크레딧 수를 저장할 ref
const creditCount = ref(0);

// 사용자 정보를 가져오는 새로운 함수
const fetchUserCreditInfo = async () => {
  try {
    const userIdx = userStore.userInfo.userIdx; // 현재 로그인한 사용자의 userIdx
    const token = sessionStorage.getItem("accessToken"); // 토큰 가져오기

    // 토큰이 유효한지 확인
    if (!token) {
      console.error('No token found in sessionStorage');
      return;
    }

    console.log('Sending request with token:', token); // 토큰을 확인하는 로그 추가

    // 새로운 API 호출
    const response = await axios.get(`https://8080-kangbh98-alpb-83f38oajbmf.ws-us116.gitpod.io/users/info/${userIdx}`, {
      headers: {
        'Authorization': `Bearer ${token}` // Authorization 헤더에 Bearer 토큰 추가
      }
    });

    if (response.status === 200) {
      const userInfo = response.data.userInfo; // 가져온 사용자 정보
      creditCount.value = userInfo.credit; // 크레딧 정보 업데이트
      console.log('User credit info fetched successfully:', userInfo); // 성공적으로 가져온 사용자 정보 로그
    } else {
      console.error('Failed to fetch user credit info:', response);
    }
  } catch (error) {
    console.error('Error fetching user credit info:', error); // 오류 로그 추가
  }
};

// 컴포넌트가 마운트될 때 사용자 정보를 가져옴
onMounted(() => {
  fetchUserCreditInfo(); // 사용자 정보 가져오는 함수 호출
});

function goToOrderCredit() {
  // 크레딧 구매 페이지로 이동
  router.push('/order/credit');
}
</script>

<template>
  <div class="container-fluid d-flex flex-column justify-content-between vh-100">
    <div class="row">
      <div class="col-12">
        <!-- DefaultNavbar 컴포넌트 사용 -->
        <DefaultNavbar style="height: 70px; background-color: white; border-radius: 10px;" />
      </div>
    </div>
    <section class="d-flex flex-grow-1 align-items-center justify-content-center">
      <!-- 본문 가운데 정렬 -->
      <div class="container d-flex justify-content-center align-items-center">
        <div class="row justify-content-center align-items-center">
          <!-- 크레딧 정보 카드 -->
          <div class="col-lg-10 d-flex flex-column align-items-center">
            <div class="row justify-content-center">
              <!-- 크레딧 정보 카드 -->
              <DefaultInfoCard
                class="text-center mx-auto"
                color="info"
                icon="payments"
                title="My Credits"
                :description="`현재 보유 크레딧: ${creditCount}개`"
                style="white-space: nowrap;"
              />
            </div>
            <div class="row justify-content-center mt-4">
              <!-- 크레딧 구매 페이지로 이동하는 버튼 -->
              <DefaultInfoCard
                class="text-center mx-auto"
                color="success"
                icon="add_circle"
                title="Need More Credits?"
                description="크레딧이 필요하시면 아래 버튼을 눌러주세요."
                style="white-space: nowrap;"
              />
              <button
                class="btn btn-success mt-3 mx-auto"
                @click="goToOrderCredit"
              >
                크레딧 구매하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.container {
  max-width: 960px;
}
.vh-100 {
  height: 100vh;
}
.flex-grow-1 {
  flex-grow: 1;
}
</style>

