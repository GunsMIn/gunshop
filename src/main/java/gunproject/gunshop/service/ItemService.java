package gunproject.gunshop.service;

import gunproject.gunshop.domain.item.Item;
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





}
