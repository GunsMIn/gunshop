package gunproject.gunshop.service;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //중복이름 체크
    @Transactional
    public Long join(Member member) {
        validateName(member); // 중복회원 검증 <- 이름으로 중복 검사하는 메소드
        memberRepository.save(member);
        return member.getId();
    }

    private void validateName(Member member) {
        List<Member> listByName = memberRepository.findByName(member.getName());
        if (!listByName.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    //회원 조회
    //optional<Member> orElseThrow로 꺼내기
    public Member findOne(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElseThrow(() -> {
            throw new IllegalStateException("존재 하지 않는 회원입니다.");
        });
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }


}
