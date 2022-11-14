package gunproject.gunshop.service;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;


    /**
     * return이 null이면 로그인 실패인 로직
     * */
    public Member login(String loginId,String password) {
        Optional<Member> opMember = memberRepository.findByLoginId(loginId);
        Member member = opMember.orElseThrow(() -> new IllegalArgumentException("해당 아이디의 회원은 존재하지않습니다"));
        if (password.equals(member.getPassword())) { // password 일치
            return member;
        }
        return null;
    }

}
