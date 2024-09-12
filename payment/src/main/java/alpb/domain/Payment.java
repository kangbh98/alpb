package alpb.domain;

import alpb.PaymentApplication;
import alpb.domain.PaymentApproved;
import alpb.domain.payment_status;
import alpb.domain.PaymentCancelled;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
//<<< DDD / Aggregate Root
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    
    private Long orderId;

    private Integer product;

    private payment_status paymentStatus;

    @PostPersist
    public void onPostPersist() {
        PaymentApproved paymentApproved = new PaymentApproved(this);
        if(this.paymentStatus == payment_status.결제성공){
            paymentApproved.setUserId(this.userId);
            paymentApproved.setProduct(this.product);
            paymentApproved.publishAfterCommit();
        }
    }

    @PreRemove
    public void onPreRemove() {
        PaymentCancelled paymentCancelled = new PaymentCancelled(this);
        paymentCancelled.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void payment(OrderPlaced orderPlaced) {
  
        Payment payment = new Payment();
        payment.setOrderId(orderPlaced.getOrderId());
        payment.setProduct(orderPlaced.getProduct());
        payment.setUserId(orderPlaced.getUserId());

        // 주문량이 백개를 초과하면 결제 실패 및 주문 성공에 대한 보상 트랜잭션 처리 이벤트 실행
        // 주문량이 백개 미만이면 결제 성공 ( 추후 결제 모듈 연결 시 결제 모듈의 메시지 수신 후 이벤트 발행) 
        if(orderPlaced.getProduct()>100){
            payment.setPaymentStatus(payment_status.결제실패);
            // 보상 처리
            payment.paymentCancelled(orderPlaced.getOrderId());
        }else if(orderPlaced.getProduct()<=100){
            payment.setPaymentStatus(payment_status.결제성공);
        }
        repository().save(payment);
    }

    public void paymentCancelled(Long orderId) {
        PaymentCancelled paymentCancelled = new PaymentCancelled(this);
        // 주문 번호에 해당 하는 주문을 취소하는 이벤트 발행
        paymentCancelled.setId(orderId);
        paymentCancelled.publish();
    }
}

