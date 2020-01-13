package khmerhowto.Controller;

import khmerhowto.Repository.Model.User;
import khmerhowto.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * AdminController
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/admin/feedback")
    String manageFeedBack(){
        return "admin/admin-feedback";
    }
    @GetMapping("/admin/question")
    String manageQuestion(){
        return "admin/admin-question";
    }
    @GetMapping("/admin/user")
    String manageUser(@ModelAttribute("User")User user, @PageableDefault(size = 10)Pageable pageable, Model model){
        Page<User> page =userService.findAll(pageable);
        List<User> users = page.getContent();
        model.addAttribute("page",page);
        model.addAttribute("users",users);


        return "admin/admin-user";
    }
    @GetMapping("/admin")
    String home(){
        return "admin/admin-home";
    }

    @GetMapping("/admin/category")
    String category(){
        return "admin/admin-category";
    }

    @GetMapping("/admin/customize")
    String customizeInfo(){
        return "admin/admin-customize-user";
    }


    @GetMapping("/admin/article")
    String manageArticle(){
        return "admin/admin-article";
    }

    @GetMapping("/admin/article/insert")
    String insertArticle(){
        return "admin/admin-article-insert";
    }
}