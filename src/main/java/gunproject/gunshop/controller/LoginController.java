package gunproject.gunshop.controller;

import gunproject.gunshop.domain.Member;
import gunproject.gunshop.dto.LoginForm;
import gunproject.gunshop.service.LoginService;
import gunproject.gunshop.dto.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form,
                        @RequestParam(defaultValue = "/") String redirectURI,
                        BindingResult result, HttpServletRequest request){

        if(result.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember==null){
            result.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        //로그인 성공 시 session 처리
        HttpSession session = request.getSession(true); // 세션이 없다면 새로운 세션 생성
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        return "redirect:" +redirectURI; // 인터셉터 적용 인증체크
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {//
        HttpSession session = request.getSession(false);
        /*request.getSession(false)
            세션이 있으면 기존 세션을 반환한다.
            세션이 없으면 새로운 세션을 생성하지 않는다. null 을 반환한다*/
        if (session != null) {// 세션이 존재 할 때
            session.invalidate();
        }
        return "redirect:/";
    }


    /**************************************  쿠키 로그인(리펙토링 전)  *********************************************/

    //로그인 쿠키 처리
    //@PostMapping("/login")
    public String doLogin(@ModelAttribute @Validated LoginForm loginForm, BindingResult result,
                          HttpServletResponse response) {

        if (result.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        log.info("로그인 된 회원 : {}",loginMember);

        if (loginMember == null) { // 로그인 실패시 글로벌 오류 구간
            result.reject("loginFail","아이디 또는 비밀번호가 맞지않습니다");
            return "login/loginForm";
        }

        /*로그인 성공처리가 되었을 시 쿠키 처리*/
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        //만들어진 쿠키를 response에 넣어서 보내줘야한다.
        response.addCookie(idCookie);
        //응답에서 쿠키를 받은 클라이언트는 웹브라우저의 종료전까지 서버에 id를 계속해서 보내줄 것이다.

        return "redirect:/";
    }

    //@PostMapping("/logout")
    public String doLogOut(HttpServletResponse response) {
        //로그아웃도 응답 쿠키로 생성 ! Max-Age=0 를 확인할 수 있다. 해당 쿠키는 즉시 종료된다
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }


}
