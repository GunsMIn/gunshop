package gunproject.gunshop.dto.RestApiDto;

import gunproject.gunshop.domain.Address;
import gunproject.gunshop.domain.Order;
import gunproject.gunshop.domain.OrderItem;
import gunproject.gunshop.domain.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class OrderDto {

    private Long orderId;
    private String memberName;
    private Address address;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems; // 1 대 다 관계 또한 dto로 바꿔줘야한다.

    //엔티티를 ResponseDto로 변환해주는 생성자
    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.memberName = order.getMember().getName();
        this.address = order.getDelivery().getAddress();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem)).collect(Collectors.toList());
    }

    @Builder
    public OrderDto(Long orderId, String memberName, Address address, LocalDate orderDate, OrderStatus orderStatus, List<OrderItemDto> orderItems) {
        this.orderId = orderId;
        this.memberName = memberName;
        this.address = address;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }
}
