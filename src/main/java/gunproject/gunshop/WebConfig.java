package gunproject.gunshop;


import gunproject.gunshop.intercepter.LogInterceptor;
import gunproject.gunshop.intercepter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //회원가입 로그인 로그아웃 등등 화면은 로그인사용자 인증체크 제외
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/new", "/login", "/logout",
                        "/css/**", "/*.ico", "/error");
    }
}
