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


       /* //given
        Member member = new Member();
        member.setName("김건우");
        Member member2 = new Member();
        member2.setName("김건우");

        //when
        memberService.join(member);
        try {
            memberService.join(member2);//예외가 발생해야한다!!!!!!
        }catch (IllegalStateException e){
            return;
        }
        //then
        Assertions.fail("이미 존재하는 회원입니다.");*/

    }
}