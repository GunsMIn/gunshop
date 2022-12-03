package gunproject.gunshop.repository.customRepository;

import gunproject.gunshop.domain.Order;
import gunproject.gunshop.domain.OrderSearch;

import java.util.List;

public interface OrderCustomRepository {

     List<Order> findAll();

     List<Order> findAllByString(OrderSearch orderSearch);

     List<Order> findAllByCriteria(OrderSearch orderSearch);
}
