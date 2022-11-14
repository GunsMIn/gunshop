package gunproject.gunshop.controller;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.dto.LoginForm;
import gunproject.gunshop.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    //로그인 페이지 단순 이동
    @GetMapping("/login")
    public String goLogin(@ModelAttribute LoginForm loginForm) {
        return "login/loginForm";
    }

    //로그인 쿠키 처리
    @PostMapping("/login")
    public String doLogin(@ModelAttribute @Validated LoginForm loginForm, BindingResult result, HttpServletResponse response) {

        if (result.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        log.info("로그인 된 회원 : {}",loginMember);

        if (loginMember == null) {
            result.reject("loginFail","아이디 또는 비밀번호가 맞지않습니다");
            return "login/loginForm";
        }

        /*로그인 성공처리가 되었을 시 쿠키 처리*/
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        //만들어진 쿠키를 response에 넣어서 보내줘야한다.
        response.addCookie(idCookie);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String doLogOut(HttpServletResponse response) {
        //로그아웃도 응답 쿠키로 생성 ! Max-Age=0 를 확인할 수 있다. 해당 쿠키는 즉시 종료된다
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
