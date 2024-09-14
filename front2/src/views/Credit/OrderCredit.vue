<script setup>
import { ref, computed } from 'vue';
import axios from 'axios'; // axios 추가
import { useRouter } from 'vue-router';
import DefaultNavbar from "@/examples/navbars/NavbarDefault.vue";
import { useUserStore } from "@/stores/user"; // Pinia 스토어 가져오기
import { jwtDecode } from "jwt-decode";


// 크레딧 가격 상수
const CREDIT_PRICE = 900;
const creditCount = ref(1); // 기본값을 1로 설정
const totalPrice = computed(() => creditCount.value * CREDIT_PRICE);

// 모달 상태 관리
const showPaymentModal = ref(false);
const isProcessingPayment = ref(false);
const paymentSuccess = ref(false);
const paymentFailed = ref(false);
const router = useRouter();
const userStore = useUserStore();

// 결제 진행 모달 열기
function openPaymentModal() {
  showPaymentModal.value = true;
}

// 서버에 주문 정보를 보내는 함수
async function sendOrderRequest() {
  // 토큰에서 userId를 가져오기
  const token = sessionStorage.getItem("accessToken");
  if (!token) {
    console.error("No token found. Please login first.");
    return;
  }

  const decodedToken = jwtDecode(token); // jwtDecode를 사용해 토큰을 디코딩
  const userId = decodedToken.userId; // 디코딩된 토큰에서 userId 추출

  const orderData = {
    product: creditCount.value,
    userId: userId, // 토큰에서 추출한 userId 사용
  };

    // 크레딧 개수가 100개를 초과할 경우 결제 취소
    if (creditCount.value > 100) {
    paymentFailed.value = true; // 결제 취소 모달 표시
    isProcessingPayment.value = false; // 결제 중 모달 닫기
    return; // 더 이상 주문을 진행하지 않음
  }

  try {
    const response = await axios.post('https://8080-kangbh98-alpb-83f38oajbmf.ws-us116.gitpod.io/orders', orderData); // 정확한 서버 URL 사용
    if (response.status === 201) { // 201: Created
      console.log('Order created successfully:', response.data);
      paymentSuccess.value = true; // 결제 성공 모달 표시
    } else {
      console.error('Failed to create order:', response);
      paymentFailed.value = true; // 결제 실패 모달 표시
    }
  } catch (error) {
    console.error('Error creating order:', error);
    paymentFailed.value = true; // 결제 실패 모달 표시
  } finally {
    isProcessingPayment.value = false; // 결제 중 모달 닫기
  }
}

// 결제 시작: 결제 진행 모달에서 결제 중 모달로 전환
function startProcessing() {
  showPaymentModal.value = false; // 결제 진행 모달 닫기
  isProcessingPayment.value = true; // 결제 중 모달 열기

  // 주문 정보 전송
  sendOrderRequest();
}

// 모달 닫기 함수
function closeModal() {
  showPaymentModal.value = false;
  isProcessingPayment.value = false;
  paymentSuccess.value = false;
  paymentFailed.value = false;
}

// 페이지 이동 함수
function goToCheckCredit() {
  closeModal();
  router.push('/check/credit');
}

function retryOrder() {
  closeModal();
  router.push('/order/credit');
}

function goToHome() {
  closeModal();
  router.push('/pages/landing-pages/contact-us');
}
</script>


<template>
  <div class="container position-sticky z-index-sticky top-0">
    <div class="row">
      <div class="col-12">
        <!-- DefaultNavbar 컴포넌트 사용 -->
        <DefaultNavbar style="height: 70px; background-color: white; border-radius: 10px;" />
      </div>
    </div>
  </div>

  <section class="py-2">
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
      <div class="row justify-content-center">
        <div class="col-lg-10 d-flex justify-content-center">
          <!-- 크레딧 주문 카드 -->
          <div class="card" style="border: 3px solid #28a745; border-radius: 10px; width: 400px;">
            <div class="card-body text-center">
              <h5 class="card-title">크레딧 구매하기</h5>
              <!-- 크레딧 수 입력 -->
              <div class="form-group text-start">
                <label for="creditCount">크레딧 개수</label>
                <input 
                  type="number" 
                  class="form-control" 
                  id="creditCount" 
                  v-model="creditCount" 
                  min="1"
                  placeholder="크레딧 개수를 입력하세요" />
              </div>
              <!-- 총 금액 -->
              <p class="mt-3 text-end">총 금액: <strong>{{ totalPrice }}원</strong></p>
              <!-- 주문 버튼 -->
              <button class="btn btn-success mt-3" @click="openPaymentModal">구매하기</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 결제 진행 모달 -->
    <div v-if="showPaymentModal" class="modal-overlay" @click.self="closeModal">
      <div class="card mb-3 p-3" style="background-color:white; border-radius: 10px; width: 340px; height: 250px;">
        <div class="modal-header">
          <h5 class="modal-title">결제 진행</h5>
          <button type="button" class="btn-close" @click="closeModal">X</button>
        </div>
        <div class="modal-body">
          <p>구매한 크레딧 개수: <strong>{{ creditCount }}</strong>개</p>
          <p>결제 금액: <strong>{{ totalPrice }}원</strong></p>
        </div>
        <div class="modal-footer justify-content-center">
          <button type="button" class="btn btn-primary mx-2" style="background-color: #28a745; width: 120px;" @click="startProcessing">결제</button>
          <button type="button" class="btn btn-primary mx-2" style="width: 120px;" @click="closeModal">취소</button>
        </div>
      </div>
    </div>

    <!-- 결제 중 모달 -->
    <div v-if="isProcessingPayment" class="modal-overlay">
      <div class="card mb-3 p-3" style="background-color:white; border-radius: 10px; width: 340px; height: 150px;">
        <div class="modal-body text-center">
          <p>결제 중...</p>
          <div class="progress">
            <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" style="width: 100%;"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 결제 완료 모달 -->
    <div v-if="paymentSuccess" class="modal-overlay">
      <div class="card mb-3 p-3 text-center" style="background-color:white; border-radius: 10px; width: 340px; height: 200px;">
        <div class="modal-body">
          <i class="bi bi-check-circle" style="font-size: 50px; color: #28a745;"></i>
          <p class="mt-3" style="font-size: 1.25rem; font-weight: bold;">결제가 완료되었습니다!</p>
        </div>
        <div class="modal-footer justify-content-center">
          <button class="btn btn-primary" style="background-color: #28a745;" @click="goToCheckCredit">확인</button>
        </div>
      </div>
    </div>

  <!-- 결제 취소 모달 -->
  <div v-if="paymentFailed" class="modal-overlay">
    <div class="card mb-3 p-3 text-center" style="background-color:white; border-radius: 10px; width: 340px; height: 200px;">
      <div class="modal-body">
        <i class="bi bi-x-circle" style="font-size: 50px; color: #dc3545;"></i>
        <!-- 텍스트 크기와 굵기 조정 -->
        <p class="mt-3" style="font-size: 1.25rem; font-weight: bold;">결제가 취소되었습니다.</p>
      </div>
      <div class="modal-footer justify-content-center" style="display: flex; justify-content: center; gap: 10px;">
        <!-- 버튼들 한 줄로 정렬 -->
        <button class="btn btn-primary" @click="retryOrder" style="width: 149px;">다시 구매하기</button>
        <button class="btn btn-secondary" @click="goToHome" style="width: 149px;">홈으로 돌아가기</button>
      </div>
    </div>
  </div>

  </section>
</template>

<style scoped>
.container {
  max-width: 960px;
}
.card {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}
.card-title {
  font-size: 1.25rem;
  font-weight: bold;
}
.form-control {
  padding: 0.5rem;
  font-size: 1rem;
  border-radius: 5px;
  border: 1px solid #ced4da;
}
.form-control:focus {
  outline: none; /* 기본 outline 제거 */
  border: 1px solid #ced4da; /* 테두리 유지 */
  box-shadow: none; /* 선택 시 상자가 강조되지 않도록 설정 */
}
.btn {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  border-radius: 5px;
  width: 100px;
}

/* 모달 스타일링 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.modal-header, .modal-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.modal-footer {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.modal-body {
  margin: 20px 0;
}

.bi-check-circle, .bi-x-circle {
  font-size: 50px;
  margin-bottom: 10px;
}
</style>
