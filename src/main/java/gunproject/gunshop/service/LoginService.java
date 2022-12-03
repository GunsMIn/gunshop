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

        Optional<Member> firstStepMember = memberRepository.findByLoginId(loginId);
        Member member = firstStepMember.filter(m -> m.getPassword().equals(password))
                .orElse(null);
        return member;
    }

}
