package gunproject.gunshop.service;

import gunproject.gunshop.domain.item.Book;
import gunproject.gunshop.domain.item.Item;
import gunproject.gunshop.dto.BookForm;
import gunproject.gunshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    //item등록
    public Long register(Item item) {
        Item registerItem = itemRepository.save(item);
        return registerItem.getId();
    }

    public Item save(BookForm form) {
        return itemRepository.save(form.toEntity());
    }


    //list
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    //단건 조회
    @Transactional(readOnly = true)
    public Item findOne(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        Item item = itemOptional.orElseThrow(() -> {
            throw new IllegalStateException("해당 아이템이 없습니다");
        });
        return item;
    }

    //변경감지(dirty checking)
    //JPA는 ENTITY를 보관할때 최초의 상채를 저장하고 있디. 이것을 스냅샷이라고 하며,
    //영속성 컨텍스트가 FLUSH되는 시점에서 스냅샷과 현재 엔티티의 상태를 비교하여 달라진 엔티티를 찾는다.
    //이후 변경된 필드들을 이용하여 쓰기지연 SQL 저장소에 Update 쿼리를 생성하여 쌓아 둔다.
    public void updateItem(Long id, BookForm updateForm) {
        Optional<Item> opItem = itemRepository.findById(id);
        Book item = (Book)opItem.orElse(new Book());
        item.setName(updateForm.getName());
        item.setPrice(updateForm.getPrice());
        item.setStockQuantity(updateForm.getStockQuantity());
        item.setAuthor(updateForm.getAuthor());
        item.setIsbn(updateForm.getIsbn());
    }




}
