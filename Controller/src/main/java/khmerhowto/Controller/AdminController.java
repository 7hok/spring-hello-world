package khmerhowto.Controller;

import khmerhowto.Repository.Model.ContentRequest;
import khmerhowto.Repository.Model.User;
import khmerhowto.Service.ContentRequestService;
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
    @Autowired
    private ContentRequestService contentRequestService;
    @GetMapping("/admin/feedback")
    String manageFeedBack(Model model){
        model.addAttribute("CURRENT_PAGE", "feedback");
        return "admin/admin-feedback";
    }
    @GetMapping("/admin/question")
    String manageQuestion(@PageableDefault(size = 10)Pageable pageable,Model model){
        Page<ContentRequest>page = contentRequestService.findAll(pageable);
        List<ContentRequest>questions = page.getContent();
        model.addAttribute("page",page);
        model.addAttribute("questions",questions);
        model.addAttribute("CURRENT_PAGE", "question");
        return "admin/admin-question";
    }
    @GetMapping("/admin/user")
    String manageUser(@ModelAttribute("User")User user, @PageableDefault(size = 10)Pageable pageable, Model model){
        Page<User> page =userService.findAll(pageable);
        List<User> users = page.getContent();
        model.addAttribute("page",page);
        model.addAttribute("users",users);
        model.addAttribute("CURRENT_PAGE", "user");

        return "admin/admin-user";
    }
    @GetMapping("/admin")
    String home(Model model){
        model.addAttribute("CURRENT_PAGE", "home");
        return "admin/admin-home";
    }

    @GetMapping("/admin/category")
    String category(Model model){
        model.addAttribute("CURRENT_PAGE", "category");
        return "admin/admin-category";
    }

    @GetMapping("/admin/customize")
    String customizeInfo(Model model){
        model.addAttribute("CURRENT_PAGE", "setting");
        return "admin/admin-customize-user";
    }


    @GetMapping("/admin/article")
    String manageArticle(Model model){
        model.addAttribute("CURRENT_PAGE", "article");
        return "admin/admin-article";
    }

    @GetMapping("/admin/article/insert")
    String insertArticle(){
        return "admin/admin-article-insert";
    }
}