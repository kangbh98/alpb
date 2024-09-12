# 🌍KT Trip(여행 아카이빙 어플리케이션)

## 📝 서비스 시나리오

### 📌 기능적 요구사항

1. 사용자는 카카오맵 기반으로 특정 장소의 정보(장소 기본 정보, 리뷰 등)를 조회할 수 있다.
2. 사용자는 특정 장소를 계획에 추가할 수 있다.
3. 사용자는 날짜 기준 계획을 삭제할 수 있다.
4. 사용자는 본인이 등록한 장소들을 계획 페이지를 통해 확인할 수 있다.
5. 계획이 만들어지고 1크레딧을 사용하면 계획 기반 만화가 만들어진다.
6. 크레딧이 없으면 만화 제작을 할 수 없다.
7. 사용자는 만들어진 만화를 삭제할 수 있다.
8. 사용자는 계획 기반으로 만들어진 만화를 확인할 수 있다.
9. 사용자는 특정 장소에 대한 리뷰를 남길 수 있다.
10. 사용자는 이미 만든 리뷰를 수정/삭제할 수 있다.
11. 사용자는 회원가입을 할 수 있다.
12. 사용자는 로그인/아웃을 할 수 있다.
13. 회원은 크레딧을 주문하여 구매할 수 있다.
14. 크레딧 주문을 하면 자동으로 결제가 진행된다.
15. 결제가 완료되면 크레딧 개수가 증가한다.
16. 결제가 취소되면 주문이 취소된다.
17. 크레딧을 사용하면 크레딧 개수가 차감된다.
18. 사용자는 특정 장소에 대한 예상 평점을 확인할 수 있다.

### 🔍 비기능적 요구사항

1. **트랜잭션**
    1. 크레딧이 없으면 다이어리 생성이 불가해야 한다.
2. **장애격리**
    1. 계획, 다이어리, 주문 및 결제 기능은 각각 서로의 장애 여부와 무관하게 작동해야 한다.

## 📊 분석/설계

### 🎯 AS-IS 조직 (Horizontally-Aligned)

[![1-as-is.png](https://i.postimg.cc/wjKTFMS8/1-as-is.png)](https://postimg.cc/PP4j5XX2)

### 🎯 TO-BE 조직 (Vertically-Aligned)

[![2-to-be.png](https://i.postimg.cc/WbsP4rL4/2-to-be.png)](https://postimg.cc/JtYF6shw)

### 💡 Event Storming 결과

- **이벤트 도출**
    
    [![3-event-stroming.png](https://i.postimg.cc/W4FBywr5/3-event-stroming.png)](https://postimg.cc/wtz4yJwN)
    
- **부적격 이벤트 탈락**
    
    [![4-event-out.png](https://i.postimg.cc/59qZD0YW/4-event-out.png)](https://postimg.cc/BtbYKJBm)
    
- **액터, 커멘드, 폴리시 푸착 및 정리**
    
    [![5-actor-command-policy.png](https://i.postimg.cc/KjPQVJZh/5-actor-command-policy.png)](https://postimg.cc/m1rQ13zm)
    
- **어그리게잇으로 묶기**
    
    [![6-aggregate.png](https://i.postimg.cc/J7ZTG4jY/6-aggregate.png)](https://postimg.cc/0r5G4vW7)
    
- **바운디드 컨텍스트 묶기**
    
    [![7-bounded-context.png](https://i.postimg.cc/HstB9ZZQ/7-bounded-context.png)](https://postimg.cc/SJJ6yrps)
    
- **컨텍스트 매핑**
    
    [![8-mapping.png](https://i.postimg.cc/XYHLTZ0q/8-mapping.png)](https://postimg.cc/hXmVmtvR)
    
- **완성된 1차 모형**
    
    [![9-1st-model.png](https://i.postimg.cc/Bvjpsqqg/9-1st-model.png)](https://postimg.cc/GB1GxnT8)
    
### ✅ 요구사항 검증

- **기능적 요구사항 검증**
    1. 사용자는 카카오맵 기반으로 특정 장소의 정보(장소 기본 정보, 리뷰 등)를 조회할 수 있다. (O)
    2. 사용자는 특정 장소를 계획에 추가할 수 있다. (O)
    3. 사용자는 날짜 기준 계획을 삭제할 수 있다. (O)
    4. 사용자는 본인이 등록한 장소들을 계획 페이지를 통해 확인할 수 있다. (O)
    5. 계획이 만들어지고 1크레딧을 사용하면 계획 기반 만화가 만들어진다. (X)
    6. 크레딧이 없으면 만화 제작을 할 수 없다. (O)
    7. 사용자는 만들어진 만화를 삭제할 수 있다. (O)
    8. 사용자는 계획 기반으로 만들어진 만화를 확인할 수 있다. (X)
    9. 사용자는 특정 장소에 대한 리뷰를 남길 수 있다. (O)
    10. 사용자는 이미 만든 리뷰를 수정/삭제할 수 있다. (O)
    11. 사용자는 회원가입을 할 수 있다. (O)
    12. 사용자는 로그인/아웃을 할 수 있다. (O)
    13. 회원은 크레딧을 주문하여 구매할 수 있다. (O)
    14. 크레딧 주문을 하면 자동으로 결제가 진행된다. (O)
    15. 결제가 완료되면 크레딧 개수가 증가한다. (O)
    16. 결제가 취소되면 주문이 취소된다. (O)
    17. 크레딧을 사용하면 크레딧 개수가 차감된다. (O)
    18. 사용자는 특정 장소에 대한 예상 평점을 확인할 수 있다.(X)

- **모델 수정 및 최종본**
    
    [![10-final-model.png](https://i.postimg.cc/Y011X0tX/10-final-model.png)](https://postimg.cc/m1gzkbzM)

## 🚀 구현

### 🔄 Saga&Compensation

1. Diary
    - 다이어리 생성(Saga)
        - 트랜젝션 흐름
            - 다이어리 생성 시, user 서비스로 카프카를 통해 Pub/Sub 방식으로 이벤트를 발행 및 이벤트를 수신함
            - PolicyHandler에서 user aggregate 의 decrease 메소드를 호출해 크레딧을 차감함
            - 크레딧 차감이 완료되면 전체 Saga가 완료됨
                
                [![1.png](https://i.postimg.cc/rss1Srjx/1.png)](https://postimg.cc/0KgKvrLy)
                
                [![2.png](https://i.postimg.cc/rz6DK0P8/2.png)](https://postimg.cc/n9kFWzx5)
                
2. Payment
    - 결제 성공(Saga)
        - 트랜젝션 흐름
            - 사용자가 credit에 대해서 수량을 정하고 주문을 정하면,카프카를 통해 결제 서비스로 이벤트를 발행함
            - 결제 성공 시, 카프카를 통해 user 서비스로 이벤트를 발행함
            - user서비스의 PolicyHandler에서 user aggregate 의 increase 메소드를 호출해 크레딧을 증가함
            - 크레딧 지급이 완료되면 전체 Saga가 완료됨
                
                [![3.png](https://i.postimg.cc/xjXgJw47/3.png)](https://postimg.cc/4nTpjSZb)
                
                [![4.png](https://i.postimg.cc/zX0fsQJz/4.png)](https://postimg.cc/fJSZXBtp)
                
    - 결제 취소(Compensation)
        - 결제서비스에서 결제 실패 시 주문이 취소되고, 안내 메시지와 구매 페이지 및 홈페이지로 이동할 수 있는 버튼이 포함된 모달을 표시함
            - 현재 임의로 “주문 크레딧 개수 100개 초과 시 결제 취소” 조건을 설정해놓음
            - 추후에는 외부 결제 모델을 연동하여 결제 성공 여부에 따라 분기를 설정할 예정
            
            [![5.png](https://i.postimg.cc/g272J6X1/5.png)](https://postimg.cc/214D9yxx)
            
            [![6.png](https://i.postimg.cc/j2Rs9Hmn/6.png)](https://postimg.cc/xJFDNz09)
            
            [![7.png](https://i.postimg.cc/8kbkvYfR/7.png)](https://postimg.cc/jCLr0ZX2)
3. 사용자 예상 평점 계산 AI 모델과의 데이터통신
	      -사용자가 카카오맵 API에서 해당 장소의 마커에 대해 조회 했을때, 백엔드를 통해 다른 Azure 가상 머신에 존재하는 AI모델과 springRestTemplate 를 활용해 데이터 통신
        - 사용자 id 와 장소명 전송
	 ![Image](https://ifh.cc/g/5o0yas.png)         

### 🌐 Prediction
- 모델 기반 추천 알고리즘

	데이터로 한번 모델을 생성해놓고 이 모델을 통해 추천을 제공하는 방식.
	
	**장점**
	-   모델의 크기 : 데이터가 압축된 형태인 Latent Factor로 저장
	-   학습 속도 : 학습과정으로  모델을 생성하고 이를 예측할 때 사용
	-   과적합 방지 : 다양한 데이터를 통해 패턴을 학습하면 새로운  추천 가능성 올라감 
	
- MF방식 (Matrix Factorization)
![Image](https://i.imgur.com/nnR7ZlR.png)
	- [ user x item ] 형태의 평가 데이터를 두개로 쪼개서 분석하는 방식
    - 여기서 k 개의 feature는 latent factor(잠재요인)으로 user와 item이 공유하고 있는 특성.

	- user가 평가하지 않은 항목에 대해서 평점을 예측해줌.
            
-  클래스 구성
    - 모델 초기화: 추천 시스템 구현을 위해 surprise 패키지 사용
        
        ```python
        class  RecommenderSystem:
	        def __init__(self, file_path):
	                self.load_data(file_path)
	                self.train_model()
        ```
        
    - 데이터 로드: 유저-장소 평점 데이터 로드
        
        ```python
	         def load_data(self, file_path):
	                df = pd.read_csv(file_path)
	                reader = Reader(rating_scale=(0.5, 5))
	                self.data = Dataset.load_from_df(df[['userIdx', 'placeId', 'rating']], reader)
        ```
        
    - 모델 학습: matrix factorization 방법으로 모델 학습
        
        ```python
	         def train_model(self):
	                trainset = self.data.build_full_trainset()
	                self.model = SVD()
	                self.model.fit(trainset)
        ```
        
    - 평점 예측: 유저가 평가하지 않은 장소에 대한 평점 예측
        
        ```python
	         def predict_rating(self, user_idx, place_id):
	                prediction = self.model.predict(user_idx, place_id)
	                return prediction.est
        ```
        


### 🌐 Gateway

1. gateway 스프링부트 App을 추가 후 application.yaml내에 각 마이크로 서비스의 routes 를 추가하고 gateway 서버의 포트를 8080 으로 설정함
    1. 로그인 시 JWTtoken을 지급하고, 이를 통해 프론트엔드로 전송 후 세션스토리지에 저장함
    2. 유저 ms 를 제외한 다른 ms 구조에서 대해서는 인증 인터셉터를 적용하여 인증/인가를 게이트웨이 단에서 관리함
    
    ```yaml
    spring:
      profiles: default
      cloud:
        gateway:
    #<<< API Gateway / Routes
          routes:
            - id: user
              uri: http://localhost:8082
              predicates:
                - Path=/users/**, 
            - id: plan
              uri: http://localhost:8083
              predicates:
                - Path=/plans/**, 
              filters:
                  - JwtAuthenticationFilter  # JWT 검증을 위한 Custom GatewayFilter
            - id: review
              uri: http://localhost:8084
              predicates:
                - Path=/reviews/**, 
              filters:
                  - JwtAuthenticationFilter  # JWT 검증을 위한 Custom GatewayFilter
            - id: diary
              uri: http://localhost:8085
              predicates:
                - Path=/cartoons/**, 
              filters:
                  - JwtAuthenticationFilter  # JWT 검증을 위한 Custom GatewayFilter
            - id: order
              uri: http://localhost:8086
              predicates:
                - Path=/orders/**, 
              filters:
                  - JwtAuthenticationFilter  # JWT 검증을 위한 Custom GatewayFilter
            - id: payment
              uri: http://localhost:8087
              predicates:
                - Path=/payments/**, 
              filters:
                  - JwtAuthenticationFilter  # JWT 검증을 위한 Custom GatewayFilter
            - id: read
              uri: http://localhost:8088
              predicates:
                - Path=, 
              filters:
                  - JwtAuthenticationFilter  # JWT 검증을 위한 Custom GatewayFilter
            - id: frontend
              uri: http://localhost:8080
              predicates:
                - Path=/**
              filters:
                  - JwtAuthenticationFilter  # JWT 검증을 위한 Custom GatewayFilter
    #>>> API Gateway / Routes
          globalcors:
            corsConfigurations:
              '[/**]':
                allowedOrigins:
                  - "*"
                allowedMethods:
                  - "*"
                allowedHeaders:
                  - "*"
                allowCredentials: true
    
    jwt:
      salt: ssafy-screte-key-20240404-ssafy-screte-key-20240404-ssafy-screte-key-20240404
      access-token:
        expiretime: 20000
      refresh-token:
        expiretime: 40000
    
    server:
      port: 8080
    ```
    
### ⚡️ CQRS

- 추가한 계획의 장소 이름, 주소, 전화번호, 다이어리 정보 등을 고객이 조회할 수 있도록 CQRS로 구현함
- Plan, Diary의 개별 Aggregate Status 를 통합 조회하여 성능 Issue 를 사전에 예방할 수 있음
- 비동기식으로 처리되어 발행된 이벤트 기반 Kafka 를 통해 수신/처리 되어 별도 Table 에 관리함
-🗺️ Plan
    - Table Modeling
        
        [![1.png](https://i.postimg.cc/jdgsgJXC/1.png)](https://postimg.cc/GHDw9t61)
        
    - PlanViewHandler를 통해 구현함
        
        [![2.png](https://i.postimg.cc/FR4mdb9C/2.png)](https://postimg.cc/XZQttCB9)
        
        [![3.png](https://i.postimg.cc/wMxpF6Rh/3.png)](https://postimg.cc/MMLNpJDT)
        
- Diary
    - Table Modeling
        
        [![4.png](https://i.postimg.cc/PfKhJ80K/4.png)](https://postimg.cc/PNL0FNX8)
        
    - DiaryViewHandler를 통해 구현함
        
        [![5.png](https://i.postimg.cc/bwSjmVrF/5.png)](https://postimg.cc/bZPMJTQQ)
        
        [![6.png](https://i.postimg.cc/qR4VMkL9/6.png)](https://postimg.cc/1gCYv1bH)

### 🌐 배포

- [![1.png](https://i.postimg.cc/L8wpYNrR/1.png)](https://postimg.cc/qNsYWXJZ) 
  [![2.png](https://i.postimg.cc/W4ZPM0vZ/2.png)](https://postimg.cc/TyTSxLh2)
  - front 및 user, plan , order , payment 등 서비스를 쿠버네티스를 활용해 애저 가상머신 1. 에 배포함
  - AI 모델의 경우에는 서버 리소스가 gpu가 더 필요하는 등 추가 가상 머신을 사용하는 것이 비용에 더욱 적합할 것을 고려해 가상머신을 추가로 구축함
  ![image](https://i.imgur.com/kfi8HGK.png)
  ![image](https://i.imgur.com/pgQwyGT.png)