package gunproject.gunshop.repository;

import gunproject.gunshop.domain.Order;
import gunproject.gunshop.repository.customRepository.OrderCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Long>, OrderCustomRepository { // Spring data jpa를 사용하지않은 repository

}

