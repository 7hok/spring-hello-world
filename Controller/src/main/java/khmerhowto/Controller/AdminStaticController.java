package khmerhowto.Controller;

import khmerhowto.Repository.Model.Role;
import khmerhowto.Repository.Model.User;
import khmerhowto.Service.ContentRequestService;
import khmerhowto.Service.RoleService;
import khmerhowto.Service.ServiceImplement.CategoryServiceImp;
import khmerhowto.Service.ServiceImplement.FeedBackServiceImp;
import khmerhowto.Service.UserRoleSrvice;
import khmerhowto.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * AdminStaticController
 */
//@Controller
public class AdminStaticController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleSrvice userRoleSrvice;
    @Autowired
    private UserService userService;
    @Autowired
    private ContentRequestService contentRequestService;
    @Autowired
    private CategoryServiceImp categoryService;
    @Autowired
    private FeedBackServiceImp feedbackservice;
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

//    @GetMapping("/admin/customize")
//    String customizeInfoStatic(Model model){
//        model.addAttribute("CURRENT_PAGE", "setting");
//        return "admin/admin-customize-user-static";
//    }

    @GetMapping("/admin")
    String home(Model model){
        model.addAttribute("CURRENT_PAGE", "home");
        return "admin/admin-home";
    }

    @GetMapping("/admin/category")
    String category(Model model){
        model.addAttribute("CURRENT_PAGE", "category");
        model.addAttribute("categories",categoryService.findCategoryByStatus(1));
        return "admin/admin-category";
    }


    @GetMapping("/admin/customize/{id}")
    String customizeInfo(@PathVariable("id")Integer id, Model model){
        model.addAttribute("role",roleService.findAll());
        model.addAttribute("user",userService.findById(id));

        model.addAttribute("CURRENT_PAGE", "setting");

        return "admin/admin-customize-user";
    }
    @PostMapping("/admin/customize/{id}")
    String updateInfo(@PathVariable("id")Integer id, @ModelAttribute User user, @ModelAttribute Role R, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            System.out.println("error aii");
            return "admin/admin-customize-user";
        }
        else {

            userService.updateUser(id,user);
            System.out.println("update Success aii");
            return "redirect:/admin/user";
        }
    }


    @GetMapping("/admin/article")
    String manageArticle(Model model){
        model.addAttribute("CURRENT_PAGE", "article");
        return "admin/admin-article-static";
    }

   
}