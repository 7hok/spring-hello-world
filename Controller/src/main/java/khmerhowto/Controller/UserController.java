package khmerhowto.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import khmerhowto.Repository.CommentRepository;
import khmerhowto.Repository.ContentRepository;
import khmerhowto.Repository.UserRepository;
import khmerhowto.Repository.Model.Comment;
import khmerhowto.Repository.Model.Content;
import khmerhowto.Repository.Model.User;
import khmerhowto.Service.ServiceImplement.UserServiceImp;
import khmerhowto.globalFunction.GlobalFunctionHelper;

/**
 * UserController
 */
@Controller
public class UserController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    CommentRepository commentRepository;
    @PersistenceContext
    EntityManager em;

   
    @PostMapping("/user/update/lastread")
    @ResponseBody
    @Transactional
    String updateLastClick (){
       try {
        User _user =   GlobalFunctionHelper.getCurrentUser();
        Integer user_id = _user.getId();
        User user = userRepo.getOne(user_id);
        user.setLastNotificationClick(LocalDateTime.now());
        userRepo.save(user);
        _user.setLastNotificationClick(LocalDateTime.now());
        return "work";
       } catch (Exception e) {
           System.out.println(e);
        System.out.println("FAIL");
           return "fail";
       }

    }
    @PostMapping(value="/notification/get")
    public  ResponseEntity<Map<String, Object>> getNotification(@PageableDefault(size = 30)Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        /**
         * DIRECTLY WITH REPOSITORY
         */
        try {
           User user =  GlobalFunctionHelper.getCurrentUser();
           System.out.println(user.getUserRole());
           if(user.getUserRole().contains("CLIENT")){
            Page<Content> pages = contentRepository.findAllByOrderByIdDesc(pageable);
            List<Content> contents = pages.getContent();
            response.put("body", contents);
            response.put("type",0);
            System.out.println("client");
           }else{
               /**1 = admin & 0 = client*/
            Page<Comment> pages = commentRepository.findAllByStatusOrderByIdDesc(1,pageable);
            List<Comment> comments = pages.getContent();
            response.put("body", comments);
            response.put("type",1);

            System.out.println("admin");

           }
           System.out.println(response);
           return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

       
    }
}
