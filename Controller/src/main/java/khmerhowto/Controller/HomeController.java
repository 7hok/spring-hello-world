package khmerhowto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @GetMapping("/example")
    String example(){
        return "myhometest";
    }

    @GetMapping("/homepage")
    String home(){
        return "client-home";
    }

    @GetMapping("/logPage")
    String loginPage(){
        return "loginPage";
    }

    @GetMapping("/signup")
    String signUp(){
        return "sign-up";
    }

    @GetMapping("/detail")
    String detail(){
        return "clients/contentDetail";
    }

    @GetMapping("/contentByCategory")
    String contentByCategory(){
        return "content-by-category";
    }


}