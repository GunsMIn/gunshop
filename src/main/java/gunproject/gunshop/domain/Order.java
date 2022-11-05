package gunproject.gunshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    /**
     * 한 번 주문시 여러 상품을 주문할 수 있으므로 주문과 주문상품( OrderItem )은 일대다 관계
     * 다. 주문은 상품을 주문한 회원과 배송 정보, 주문 날짜, 주문 상태( status )를 가지고 있다. 주문 상태는 열
     * 거형을 사용했는데 주문( ORDER ), 취소( CANCEL )을 표현할 수 있다
     */

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id") // fk를 갖고있는 쪽이 @JoinColumn
    private Member member;

    @OneToOne(fetch = LAZY,cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(value = STRING)
    private OrderStatus status;

    /**
     * 연관관계메소드
     */
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void setOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /**
     * order 생성 메소드
     * 회원 , 배송 , 주문상품이 필요하다
     * 그리고 이 3개는 서로 order와 연관관계가 설정되어있어서 연관관계메소드를 만들어두었다
     * 여기 이 생성메소드에서는 연관관계메소드를 사용할 것 이다.
     * */

    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItem) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem item : orderItem) {
            order.setOrderItems(item);
        }
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);
        return order;
    }

    //주문 취소
    public void cancelOrder() {
        //1.우선 배송 상태 확인
        //2.주문의 상태를 cancel로만들어 줘야함!
        //3.orderItems에서 취소
        if (this.delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송이 시작되었습니다");
        }
        this.status = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
    //주문 총 금액
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotal();
        }
        return totalPrice;
    }


}
