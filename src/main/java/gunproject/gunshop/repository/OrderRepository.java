package gunproject.gunshop.repository;

import gunproject.gunshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    //orderRepository
}
