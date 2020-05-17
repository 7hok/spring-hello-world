package khmerhowto.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * LoginController
 */
@Controller
public class LoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    /*

    @GetMapping("/ery")
    @ResponseBody
    public String name(HttpServletRequest request) {
        System.out.println(authenticationManager);
        autoLogin("userName", "password", request);
        return authenticationManager.toString();

    }*/
}