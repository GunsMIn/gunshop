package gunproject.gunshop.service;

import gunproject.gunshop.domain.item.Book;
import gunproject.gunshop.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    @DisplayName("등록 and 단건 조회")
    public void registerAndGet() {
        Item item1 = createItem1("jpa에 관하여", 20000, 10);
        Item item2 = createItems2("myBatis에 관하여", 13000, 5);


        itemService.register(item1);
        itemService.register(item2);
        Item one = itemService.findOne(item1.getId());
        Item two = itemService.findOne(item2.getId());

        assertThat(item1.getName()).isEqualTo(one.getName());
        assertThat(item2.getPrice()).isEqualTo(two.getPrice());
    }

    private Item createItem1(String string, int i, int i2) {
        Item item1 = new Book();
        item1.setName(string);
        item1.setPrice(i);
        item1.setStockQuantity(i2);
        return item1;
    }

    private Item createItems2(String string, int i, int i2) {
        Item item2 = createItem1(string, i, i2);
        return item2;
    }

    @Test
    @DisplayName("memberOptional exception test")
    public void 예외던지기() {
        Item item1 = createItem1("jpa에 관하여", 20000, 10);

        Item one = itemService.findOne(2L);
        assertThat(one.getPrice()).isEqualTo(13000);

        //여기가 예외 터지는 부분 현재 내 디비에 3L(pk)의 item 존재하지 않는다
        assertThrows(IllegalStateException.class,() -> {
            Item third = itemService.findOne(3L);
        });
    }



}