package gunproject.gunshop.service;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.dto.RestApiDto.DeleteMemberResponse;
import gunproject.gunshop.dto.RestApiDto.UpdateMemberRequest;
import gunproject.gunshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
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


    @Transactional
    public void update(Long id, UpdateMemberRequest updateMemberRequest) {
        log.info("변경된 유저의 정보: {}", updateMemberRequest);
        Optional<Member> memberOptional = memberRepository.findById(id);
        Member originMember = memberOptional.get();
        //변경감지 dirty cash로 변경
        originMember.setLoginId(updateMemberRequest.getLoginId());
        originMember.setPassword(updateMemberRequest.getPassword());
        originMember.setName(updateMemberRequest.getName());
        originMember.setAddress(updateMemberRequest.getAddress());
    }

    @Transactional
    public int deleteMember(Long id) {
        log.info("삭제할 id : {}",id);
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
            return 1; // 삭제 성공시 1반환
        }
            //삭제할 회원이 없을 시 0반환
            return 0;     

    }
}
