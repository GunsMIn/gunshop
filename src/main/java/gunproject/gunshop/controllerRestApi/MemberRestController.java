package gunproject.gunshop.controllerRestApi;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.dto.RestApiDto.*;
import gunproject.gunshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberRestController {

    private final MemberService memberService;


    @GetMapping("/{id}")
    public ResponseEntity<SelectMemberDto> get(@PathVariable Long id) {
        Member member = memberService.findOne(id);
        SelectMemberDto selectMemberDto = Member.transSelectDto(member);
        return ResponseEntity.ok().body(selectMemberDto);
    }

    @PostMapping
    public ResponseEntity<CreateMemberResponse> add(@RequestBody CreateMemberRequest createMemberDto) {
        Member member = createMemberDto.toEntity();
        Long joinId = memberService.join(member);
        Member joinedMember = memberService.findOne(joinId);
        CreateMemberResponse createMemberResponse = Member.of(joinedMember);
        return ResponseEntity.ok().body(createMemberResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateMemberResponse> update(@PathVariable Long id,@RequestBody UpdateMemberRequest updateMemberRequest) {
        log.info("----변경된 유저의 정보: {}", updateMemberRequest);
        memberService.update(id, updateMemberRequest);
        Member changedMember = memberService.findOne(id);

        UpdateMemberResponse updateMemberResponse = Member.transUpdateDto(changedMember);
        return ResponseEntity.ok().body(updateMemberResponse);
    }



}
