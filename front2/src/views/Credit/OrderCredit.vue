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
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6">
            <!-- 크레딧 주문 카드 -->
            <div class="card mb-3 p-3" style="border: 1px solid #28a745; border-radius: 10px;">
              <div class="card-body">
                <h5 class="card-title">크레딧 주문하기</h5>
                <!-- 크레딧 수 입력 -->
                <div class="form-group">
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
                <p class="mt-3">총 금액: <strong>{{ totalPrice }}원</strong></p>
                <!-- 주문 버튼 -->
                <button class="btn btn-success mt-3" @click="placeOrder">주문하기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- 모달 (주문 완료 알림) -->
      <div class="modal fade" id="orderModal" tabindex="-1" aria-labelledby="orderModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="orderModalLabel">주문 완료</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              크레딧 주문이 완료되었습니다!
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue';
  import DefaultNavbar from "@/examples/navbars/NavbarDefault.vue";
  
  // 크레딧 가격 상수
  const CREDIT_PRICE = 900;
  // 사용자가 입력하는 크레딧 개수
  const creditCount = ref(1); // 기본값을 1로 설정
  
  // 총 금액 계산
  const totalPrice = computed(() => creditCount.value * CREDIT_PRICE);
  
  // 주문하기 버튼 클릭 시 모달 띄우기
  function placeOrder() {
    // Bootstrap 모달 열기
    const orderModal = new bootstrap.Modal(document.getElementById('orderModal'));
    orderModal.show();
  }
  </script>
  
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
  .card-text {
    font-size: 1rem;
  }
  .form-control {
    padding: 0.5rem;
    font-size: 1rem;
    border-radius: 5px;
    border: 1px solid #ced4da;
  }
  .btn {
    padding: 0.5rem 1rem;
    font-size: 1rem;
    border-radius: 5px;
  }
  .modal-content {
    padding: 1.5rem;
  }
  </style>
  