package khmerhowto.Controller;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import khmerhowto.Repository.UserRepository;
import khmerhowto.Repository.Model.User;
import khmerhowto.globalFunction.GlobalFunctionHelper;

/**
 * UserController
 */
@Controller
public class UserController {

    @Autowired
    UserRepository userRepo;

    @PersistenceContext
    EntityManager em;

    @PostMapping("/user/update/lastread")
    @ResponseBody
    String updateLastClick (){
       try {
        Integer user_id =  GlobalFunctionHelper.getCurrentUser().getId();
        User user = em.find(User.class, user_id);
        user.setLastNotificationClick(LocalDateTime.now());;
        em.merge(user);
        return "work";
       } catch (Exception e) {
           return "fail";
       }

    }
    
}