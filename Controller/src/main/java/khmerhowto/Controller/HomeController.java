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
}