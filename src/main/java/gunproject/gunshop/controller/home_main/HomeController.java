package gunproject.gunshop.controller.home_main;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.service.MemberService;
import gunproject.gunshop.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MemberService memberService;


    @RequestMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,Model model) {

        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }




    /**********************************************  리펙토링 전 쿠키 홈 화면  *******************************************************/
    // 회원가입 로그인 홈 화면
    //@RequestMapping("/")
    public String goHome(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

        if (memberId == null) {
            return "home";
        }
        //로그인 성공 시
        Member member = memberService.findOne(memberId);
        if (member == null) { // 예전 회원은 디비에 없을 수 도 있다
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

}
