package alpb.infra;

import alpb.domain.Order;
import alpb.domain.order_status;
import alpb.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setOrderStatus(order_status.주문진행중);
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        if (orderRepository.existsById(id)) {
            orderDetails.setOrderId(id);
            return orderRepository.save(orderDetails);
        } else {
            return null; // 또는 예외 처리
        }
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            // 예외 처리
        }
    }

    public void cancelOrder(Long id) {
        orderRepository.findById(id).ifPresent(order -> {
            orderRepository.delete(order);
            // 추가적인 취소 로직이 필요한 경우 여기에 구현
        });
    }
}