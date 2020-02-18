package khmerhowto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khmerhowto.Repository.UserRepository;
import khmerhowto.Repository.Model.User;
import khmerhowto.configurationmodel.MyUser;
import khmerhowto.globalFunction.GlobalFunctionHelper;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Controller
public class GoogleLoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/log")
    @ResponseBody
    public Principal user(Principal principal){ 
        System.out.println( GlobalFunctionHelper.getCurrentUser()); 
        return principal;
    }
    /**
     * 
     * 
     * Email 100% getting because of verified from login
     * After getting email from sending access token
     * check whether email is registered 
     * IF true
     *     AutoLogin
     * ELSE
     *     Redirected Register        
     * @param code
     * @param request
     * @return
     */
   
    @PostMapping(value="/auth/code")
    public  ResponseEntity<Map<String, Object>> postMethodName(@RequestParam("code") String code,HttpServletRequest request) {
      
        Map<String, Object> response = new HashMap<>();  
        try {
             GoogleTokenResponse tokenResponse =
          new GoogleAuthorizationCodeTokenRequest(
              new NetHttpTransport(),
              JacksonFactory.getDefaultInstance(),
              "https://oauth2.googleapis.com/token",
              "836845369110-sqgfnf0i6lkpl8jb28bvprvf935tarlq.apps.googleusercontent.com",
              "lxv4I6XzJLZbgclsKDbXohEx",
              code,
              "https://kunloes.com").execute();

            String accessToken = tokenResponse.getAccessToken();  
            System.out.println(accessToken);

            GoogleIdToken idToken = tokenResponse.parseIdToken();
            GoogleIdToken.Payload payload = idToken.getPayload();
            // System.out.println(payload.toString());
            // System.out.println("ME" + payload.getEmail());
            /**
             * String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");
             * 
             */
            String email = payload.getEmail();
            Boolean userChecked = checkUserByEmailIfTrueLoggedIn(email, request);
            
            
            if(userChecked == true ){
                System.out.println("EX User Login");
                response.put("redirect", "/home");
            }else{
                User user = new User();
                user.setEmail(payload.getEmail());
                user.setName((String) payload.get("name"));
                user.setProfilePicture((String) payload.get("picture"));
                request.getSession().setAttribute("USER", user);
                System.out.println("new User Login");
                response.put("redirect", "/signup");
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            response.put("redirect", "fail");
        }
       
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
   
    private Boolean checkUserByEmailIfTrueLoggedIn(String email,HttpServletRequest request){
        User user = userRepository.findByEmail(email);
        if(user == null ){
            return false;
        }else{
            Boolean loginStatus = autoLogin(email, user.getPassword(), request);
            return loginStatus == true ? true : false;
        }
        
    }

    protected Boolean autoLogin(String userName,String password,HttpServletRequest request){
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userName,password);
            authToken.setDetails(new WebAuthenticationDetails(request));
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            return false;
        }
        return true;
 
    }
}
