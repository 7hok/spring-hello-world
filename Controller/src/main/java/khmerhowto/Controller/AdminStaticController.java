package khmerhowto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * AdminStaticController
 */
@Controller
public class AdminStaticController {

    @GetMapping("/admin/feedback")
    String manageFeedBack(Model model){
        model.addAttribute("CURRENT_PAGE", "feedback");
        return "admin/admin-feedback-static";
    }
    @GetMapping("/admin/question")
    String manageQuestion(Model model){
        
        model.addAttribute("CURRENT_PAGE", "question");
        return "admin/admin-question-static";
    }
    @GetMapping("/admin/user")
    String manageUser(Model model){

       

        model.addAttribute("CURRENT_PAGE", "user");


        return "admin/admin-user-static";
    }

    @GetMapping("/admin/customize")
    String customizeInfoStatic(Model model){
        model.addAttribute("CURRENT_PAGE", "setting");
        return "admin/admin-customize-user-static";
    }

    @GetMapping("/admin")
    String home(Model model){
        model.addAttribute("CURRENT_PAGE", "home");
        return "admin/admin-home";
    }

    @GetMapping("/admin/category")
    String category(Model model){
        model.addAttribute("CURRENT_PAGE", "category");
        
        return "admin/admin-category-static";
    }

    @GetMapping("/admin/article")
    String manageArticle(Model model){
        model.addAttribute("CURRENT_PAGE", "article");
        return "admin/admin-article-static";
    }

   
}