package gunproject.gunshop.dto.RestApiDto;

import gunproject.gunshop.domain.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemDto {

    private String itemName;
    private Integer orderPrice; // 주문 가격
    private Integer count; // 주문 수량

    public OrderItemDto(OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}
