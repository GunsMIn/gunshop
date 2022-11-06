package gunproject.gunshop.service;

import gunproject.gunshop.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("중복이름 체크")
    public void 회원등록검사() {
        Member member = new Member();
        member.setName("박수경");
        Long joinId = memberService.join(member);

        Assertions.assertEquals(joinId,member.getId());
    }

    @Test
    public void 중복_회원_검사사() throws Exception {
        assertThrows(IllegalStateException.class,() ->{
            Member member1 = new Member("김건우");
            Member member2 = new Member("김건우2");
            memberService.join(member1);
            memberService.join(member2);
        });
    }

    @Test
    public void 회원_단건_조회() {
        Member member1 = new Member("김건우");
        memberService.join(member1);
        Member one = memberService.findOne(1L);

        assertThat(one.getName()).isEqualTo(member1.getName());
        assertThrows(IllegalStateException.class, () -> {
            memberService.findOne(2L);
        });
    }
}