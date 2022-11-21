package gunproject.gunshop.controllerRestApi;

import gunproject.gunshop.domain.Order;
import gunproject.gunshop.dto.RestApiDto.OrderDto;
import gunproject.gunshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtoList = orders.stream().map(o -> new OrderDto(o))
                .collect(Collectors.toList());
        log.info("조회된 주문 개수 : " + orderDtoList.size());
        return ResponseEntity.ok().body(orderDtoList);
    }


}
