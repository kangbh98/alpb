package alpb.domain;

import alpb.OrderApplication;
import alpb.domain.OrderCancelled;
import alpb.domain.OrderPlaced;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
// <<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Long userId;

    private Integer product;

    @Embedded
    private order_status orderStatus;

    @PostPersist
    public void onPostPersist() {
        // Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.setUserId(this.userId);
        orderPlaced.setOrderId(this.orderId);
        orderPlaced.setProduct(this.product);
        orderPlaced.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
                OrderRepository.class);
        return orderRepository;
    }

    // 보상처리 이벤트 수신 후, 주문 상태 주문 진행중에서 주문 실패로 변경
    public static void cancelOrder(PaymentCancelled paymentCancelled) {

        repository().findById(paymentCancelled.getId()).ifPresent(order -> {
            order.setOrderStatus(order_status.주문실패);
            repository().save(order);
        });
    }
}

