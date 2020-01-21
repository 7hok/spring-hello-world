package khmerhowto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @GetMapping("/example")
    String example(){
        return "myhometest";
    }

    @GetMapping(value = {"/homepage","/home"})
    String home(Model model){
        model.addAttribute("CURRENT_PAGE", "home");
        return "client-home";
    }

    @GetMapping({"/logPage","/"})
    String loginPage(){
        return "loginPage";
    }
    @GetMapping("/favorite/chosen")
    String favorite(){
        return "clients/CategoryChoosen";
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
    String contentByCategory(Model model){
        model.addAttribute("CURRENT_PAGE", "category");
        return "content-by-category";
    }

    @GetMapping("test/contentByCategory")
    String contentByCategoryTest(Model model){
        model.addAttribute("CURRENT_PAGE", "category");
        return "content-by-category-test";
    }


}