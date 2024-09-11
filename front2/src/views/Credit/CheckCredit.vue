<script setup>
import { ref, onMounted } from 'vue';
import DefaultInfoCard from "@/examples/cards/infoCards/DefaultInfoCard.vue";
import CenteredBlogCard from "@/examples/cards/blogCards/CenteredBlogCard.vue";
import { useUserStore } from "@/stores/user"; // 가정: 유저 스토어에서 로그인 정보 및 크레딧 정보 가져옴
import { useRouter } from 'vue-router';

const userStore = useUserStore();
const router = useRouter();

// 사용자의 크레딧 수를 저장할 ref
const creditCount = ref(0);

onMounted(() => {
  // 가정: 유저 스토어에서 크레딧 정보를 가져오는 함수
  creditCount.value = userStore.userInfo.creditCount;
});

function goToOrderCredit() {
  // 크레딧 구매 페이지로 이동
  router.push('/order/credit');
}
</script>

<template>
  <section class="py-2">
    <div class="container">
      <div class="row align-items-center">
        <!-- 좌측에 크레딧 정보 카드 -->
        <div class="col-lg-6">
          <div class="row justify-content-start">
            <!-- 크레딧 정보 카드 -->
            <DefaultInfoCard 
              color="info" 
              icon="payments" 
              title="My Credits" 
              :description="`현재 보유 크레딧: ${creditCount}개`" 
            />
          </div>
          <div class="row justify-content-start mt-4">
            <!-- 크레딧 구매 페이지로 이동하는 버튼 -->
            <DefaultInfoCard 
              color="success" 
              icon="add_circle" 
              title="Need More Credits?"
              description="크레딧이 필요하시면 아래 버튼을 눌러주세요."
            />
            <button 
              class="btn btn-success mt-3" 
              @click="goToOrderCredit"
            >
              크레딧 구매하기
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
