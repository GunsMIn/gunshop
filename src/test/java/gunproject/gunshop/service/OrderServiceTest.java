package gunproject.gunshop.service;

import gunproject.gunshop.domain.*;
import gunproject.gunshop.domain.item.Book;
import gunproject.gunshop.domain.item.Item;
import gunproject.gunshop.exception.NotEnoughStockException;
import gunproject.gunshop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    @Test
    public void 상품주문() {
        Member member = createMember();
        Book book = createBook("jpa에 관하여", 20000, 100);

        Long orderId = orderService.order(member.getId(), book.getId(), 2);
        Optional<Order> opOrder = orderRepository.findById(orderId);
        Order order = opOrder.get();

        assertEquals(order.getStatus(), OrderStatus.ORDER);
        assertEquals(order.getTotalPrice(), 20000 * 2); // 가격  *  수량
        assertEquals(book.getStockQuantity(),98); // 주문을 하면 재고가 빠져야한다.
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("김건우");
        member.setAddress(new Address("서울","올림픽로","21902"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }


    @Test
    public void 상품주문_재고수량초과() {
        assertThrows(NotEnoughStockException.class, () -> {
            Member member = createMember();
            Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
            int orderCount = 11; //재고보다 많은 수량
            //When
            orderService.order(member.getId(), item.getId(), orderCount);
        });
    }

}
