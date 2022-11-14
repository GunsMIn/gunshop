package gunproject.gunshop.domain;

import gunproject.gunshop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer orderPrice; // 주문 가격

    private Integer count; // 주문 수량

    /**
     * orderItem 생성메소드
     * item 필요
     * */
    public static OrderItem createOrderItem(Item item,int orderPrice,int count) {
        OrderItem orderItem = new OrderItem();

        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        //orderItem이 생성이되면 item의 개수는 줄어야한다.
        item.removeStock(count);
        return orderItem;
    }

    public void cancel() {
        //취소하면 item의 수량을 다시 늘려줘야한다.
        item.addStock(count);
    }

    public int getTotal() {
        return getOrderPrice() * getCount();
    }
}