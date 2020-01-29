package khmerhowto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import javax.servlet.http.HttpServletRequest;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Controller
public class GoogleLoginController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/log")
    @ResponseBody
    public Principal user(Principal principal){
        // System.out.println(((UserDetails)principal).getUsername());
       System.out.println( GlobalFunctionHelper.getCurrentUser());;
        return principal;
    }
    @GetMapping("/ck")
    public String ckEdit(){
        return "ckedit";
    }
    @PostMapping(value="/auth/code")
    @ResponseBody
    public String postMethodName(@RequestParam("code") String code,HttpServletRequest request) {
        //TODO: process POST request
        try {
             GoogleTokenResponse tokenResponse =
          new GoogleAuthorizationCodeTokenRequest(
              new NetHttpTransport(),
              JacksonFactory.getDefaultInstance(),
              "https://oauth2.googleapis.com/token",
              "836845369110-sqgfnf0i6lkpl8jb28bvprvf935tarlq.apps.googleusercontent.com",
              "lxv4I6XzJLZbgclsKDbXohEx",
              code,
              "http://localhost:8080").execute();

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
            /**
             * Email 100% getting because of verified from login
             * After getting email from sending access token
             * check whether email is registered 
             * IF true
             *     AutoLogin
             * ELSE
             *     Redirected Register
             */
            String email = payload.getEmail();
            Boolean userChecked = checkUserByEmail(email, request);
            if(userChecked == true ){
                return "redirect:/home";
            }else{
                User user = new User();
                user.setEmail(payload.getEmail());
                user.setName((String) payload.get("name"));
                user.setProfilePicture((String) payload.get("picture"));
                request.getSession().setAttribute("USER", user);
                return "redirect:/signup";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
          
        
        return "";
    }

    private Boolean checkUserByEmail(String email,HttpServletRequest request){
        User user = userRepository.findByEmail(email);
        if(user == null ){
            return false;
        }else{
            new LoginController().autoLogin(email, user.getPassword(), request);
            return true;
        }
        
    }
}
