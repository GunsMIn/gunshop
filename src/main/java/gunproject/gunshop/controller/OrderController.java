package gunproject.gunshop.controller;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.domain.Order;
import gunproject.gunshop.domain.OrderSearch;
import gunproject.gunshop.domain.item.Item;
import gunproject.gunshop.service.ItemService;
import gunproject.gunshop.service.MemberService;
import gunproject.gunshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;


    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findAll();
        List<Item> items = itemService.findAll();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String createOrder(Long memberId, Long itemId, int count) {
        Long orderId = orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    //검색 -> orderList
    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    //주문 삭제
    @PostMapping("/orders/{id}/cancel")
    public String deleteOrder(@PathVariable Long id) {
        orderService.cancel(id);
        return "redirect:/orders";
    }
}
