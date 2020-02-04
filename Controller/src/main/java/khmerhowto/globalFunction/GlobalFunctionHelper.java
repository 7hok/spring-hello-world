package khmerhowto.globalFunction;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.multipart.MultipartFile;

import khmerhowto.Repository.Model.User;
import khmerhowto.configurationmodel.MyUser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class GlobalFunctionHelper {

    private static String filePath = "Controller/src/main/resources/static/images/";
    public static String uploaded(MultipartFile files) throws Exception {
        String fileName = UUID.randomUUID() + "." + files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf(".") + 1);
        Files.copy(files.getInputStream(), Paths.get(filePath,fileName));
        return fileName;
    }

    public static User getCurrentUser(){
        try{
            UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user=  ((MyUser)userDetails).getCurrentUser();
            return user;
        }catch(Exception e){
            return null;
        }
    }

    public static Boolean autoLogin(String userName,String password,HttpServletRequest request,AuthenticationManager authenticationManager){
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
