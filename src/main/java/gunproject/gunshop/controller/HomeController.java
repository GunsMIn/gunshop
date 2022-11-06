package gunproject.gunshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {


    @RequestMapping("/")
    public String goHome() {
        log.info("gunwoo,s shop의 홈 컨트럴러");
        return "home";
    }

}
