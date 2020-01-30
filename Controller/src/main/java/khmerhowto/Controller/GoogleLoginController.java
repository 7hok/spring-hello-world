package khmerhowto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class GoogleLoginController {

    @GetMapping("/log")
    public Principal user(Principal principal){
         
        return principal;
    }

    @GetMapping("/ck")
    public String ckEdit(){
        return "ckedit";
    }
}
