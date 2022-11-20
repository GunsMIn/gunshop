package gunproject.gunshop.controllerRestApi;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.dto.RestApiDto.CreateMemberRequest;
import gunproject.gunshop.dto.RestApiDto.CreateMemberResponse;
import gunproject.gunshop.dto.RestApiDto.SelectMemberDto;
import gunproject.gunshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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


}
