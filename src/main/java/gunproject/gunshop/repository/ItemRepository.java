package gunproject.gunshop.repository;

import gunproject.gunshop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
    //itemRepository
}
