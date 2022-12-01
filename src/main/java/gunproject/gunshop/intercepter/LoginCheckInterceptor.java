package gunproject.gunshop.intercepter;


import gunproject.gunshop.dto.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI(); // 미인증 회원을 다시 redirect보내려는 목적이 존재
        log.info("로그인 세션 인증 체크 URI : {}",requestURI);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER)==null) {//SessionConst.LOGIN_MEMBER ="loginMember"
           log.info("비회원 사용자 입니다.");
           //비회원 사용자면 로그인 화면으로 보내주자
            response.sendRedirect("/login?redirectURI="+requestURI);
            return false;//true 이면 다음으로 진행하고, false 이면 더는 진행하지 않는다
        }

        return true;
    }
}
