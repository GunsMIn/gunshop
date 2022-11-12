package gunproject.gunshop.controller;

import gunproject.gunshop.domain.Address;
import gunproject.gunshop.domain.Member;
import gunproject.gunshop.dto.MemberForm;
import gunproject.gunshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    //회원가입 페이지로 이동하는 핸들러
    @GetMapping("/members/new")
    public String goMemberForm(@ModelAttribute MemberForm memberForm) {
        log.info("memberForm getMapping");

        return "/members/createMemberForm";
    }

    //회원 등록 컨틀롤러
    @PostMapping("/members/new")
    public String joinMember(@ModelAttribute @Validated MemberForm memberForm, BindingResult result) {
        //validation에 검증에 걸렸을 때 다시 원복하기위해서
        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode()));
        Long joinedMember = memberService.join(member);
        log.info("등록된 회원의 id는 {}번 입니다.", joinedMember);
        return "redirect:/";
    }

    //회원 리스트 컨트롤러
    @GetMapping("/members")
    public String goMemberList(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
