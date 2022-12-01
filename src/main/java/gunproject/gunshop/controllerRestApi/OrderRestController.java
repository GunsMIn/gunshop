package gunproject.gunshop.controllerRestApi;

import gunproject.gunshop.domain.Order;
import gunproject.gunshop.dto.RestApiDto.OrderDto;
import gunproject.gunshop.dto.RestApiDto.OrderItemDto;
import gunproject.gunshop.repository.OrderRepository;
import gunproject.gunshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders") @Slf4j
public class OrderRestController {
    //우선적으로 Order에서 xxxToOne관계만 생각해보고 api를 만들어볼 것이다.
    //Order는 member와 delivery(member의 address)와 다 대 1 관계이다.
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOneOrder(@PathVariable Long id) {
        Order order = orderService.findOne(id);

        OrderDto orderDto = OrderDto.builder()
                .orderId(order.getId())
                .memberName(order.getMember().getName())
                .address(order.getDelivery().getAddress())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getStatus())
                .orderItems(order.getOrderItems().stream().map(orderItem -> new OrderItemDto(orderItem)).collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderService.findAll();
        //여기서 dto로 바꿔서 조회를한다.
        List<OrderDto> orderDtoList = orders.stream().map(o -> new OrderDto(o))
                .collect(Collectors.toList());
        log.info("조회된 주문 개수 : " + orderDtoList.size());
        return ResponseEntity.ok().body(orderDtoList);
    }


}
