package khmerhowto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * AdminController
 */
@Controller
public class AdminController {
    @GetMapping("/admin/feedback")
    String manageFeedBack(){
        return "admin/admin-feedback";
    }
    @GetMapping("/admin/question")
    String manageQuestion(){
        return "admin/admin-question";
    }
    @GetMapping("/admin/user")
    String manageUser(){
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
}