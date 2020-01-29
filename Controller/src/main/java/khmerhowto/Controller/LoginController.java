package khmerhowto.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;

/**
 * LoginController
 */
@Controller
public class LoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    protected Boolean autoLogin(String userName,String password,HttpServletRequest request){
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userName, password);
            authToken.setDetails(new WebAuthenticationDetails(request));
            
            Authentication authentication = authenticationManager.authenticate(authToken);
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            //TODO: handle exception
            return false;
        }
        return true;
    }
}