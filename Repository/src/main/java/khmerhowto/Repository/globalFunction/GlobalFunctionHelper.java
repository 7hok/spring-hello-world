package khmerhowto.Repository.globalFunction;

import khmerhowto.Repository.Model.User;
import khmerhowto.Repository.configurationmodel.MyUser;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class GlobalFunctionHelper {


    public static User getCurrentUser() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = ((MyUser) userDetails).getCurrentUser();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}
