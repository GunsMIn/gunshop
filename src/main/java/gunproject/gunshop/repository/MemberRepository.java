package gunproject.gunshop.repository;

import gunproject.gunshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

     //service단에서 멤버의 이름 중복체크하려고 만든 repository메소드
     List<Member> findByName(String name);

     //loginId로 회원 찾기
     Optional<Member> findByLoginId(String loginId);

}
