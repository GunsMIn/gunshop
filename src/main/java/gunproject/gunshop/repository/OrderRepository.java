package gunproject.gunshop.repository;

import gunproject.gunshop.domain.Order;
import gunproject.gunshop.repository.customRepository.OrderCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

//spring data JPA외에 사용자 정의 Repository까지 상속 받음
public interface OrderRepository extends JpaRepository<Order,Long>, OrderCustomRepository {

    //xxxToOne관계는 fetch join xxxToMany관계는 Batchsize이용
    @EntityGraph(attributePaths={"member","delivery"})
    Page<Order> findAll(Pageable pageable);
}

