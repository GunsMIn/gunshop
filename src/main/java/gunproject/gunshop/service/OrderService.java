package gunproject.gunshop.service;

import gunproject.gunshop.controllerRestApi.ExceptionHandler.OrderException;
import gunproject.gunshop.domain.*;
import gunproject.gunshop.domain.item.Item;
import gunproject.gunshop.dto.RestApiDto.OrderDto;
import gunproject.gunshop.repository.ItemRepository;
import gunproject.gunshop.repository.MemberRepository;
import gunproject.gunshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Long order(Long memberId,Long itemId,int count) {
        //주문에 필요한 member와 item
        Optional<Member> opMember = memberRepository.findById(memberId);
        Member member = opMember.orElse(new Member());
        Optional<Item> opItem = itemRepository.findById(itemId);
        Item item = opItem.get();

        //우선 orderItem 먼저 생성!
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        //order 생성하려면 delivery도 필요함
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY); // 배달 준비 상태
        delivery.setAddress(member.getAddress()); // 여기서 delivery에 값을 넣어준다!!!!!!!!!!1!!

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        //이렇게 만들어 준 order를 save()해야함
        orderRepository.save(order);
        return order.getId();
    }

    public void cancel(Long id) {
        Optional<Order> orderOptional = orderRepository.findOne(id);
        Order order = orderOptional.orElseThrow(() -> new RuntimeException("취소할 주문건이 없습니다."));
        order.cancelOrder();
    }


    //검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }

    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    public Order findOne(Long id) {
        Optional<Order> optionalOrder = orderRepository.findOne(id);
        Order order = optionalOrder.orElseThrow(() -> new OrderException("해당 "+id+"번의 ITEM은 존재하지 않습니다"));
        return order;
    }
}
