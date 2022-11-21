package gunproject.gunshop.dto.RestApiDto;

import gunproject.gunshop.domain.Address;
import gunproject.gunshop.domain.Order;
import gunproject.gunshop.domain.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class OrderDto {

    private Long orderId;
    private String memberName;
    private Address address;
    private LocalDate orderDate;
    private OrderStatus orderStatus;

    //엔티티를 ResponseDto로 변환해주는 생성자
    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.memberName = order.getMember().getName();
        this.address = order.getDelivery().getAddress();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
    }
}
