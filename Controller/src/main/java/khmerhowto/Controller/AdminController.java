package khmerhowto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * AdminController
 */
@Controller
public class AdminController {
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
}