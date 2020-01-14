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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    String manageFeedBack(){
        return "admin/admin-feedback";
    }
    @GetMapping("/admin/question")
    String manageQuestion(@PageableDefault(size = 10)Pageable pageable,Model model){
        Page<ContentRequest>page = contentRequestService.findAll(pageable);
        List<ContentRequest>questions = page.getContent();
        model.addAttribute("page",page);
        model.addAttribute("questions",questions);

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

    @GetMapping("/admin/customize/{id}")
    String customizeInfo(@PathVariable("id")Integer id,Model model){
        model.addAttribute("user",userService.findById(id));
        return "admin/admin-customize-user";
    }
    @PostMapping("/admin/customize/{id}")
    String updateInfo(@PathVariable("id")Integer id,@ModelAttribute User user, BindingResult bindingResult,Model model){
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
    String manageArticle(){
        return "admin/admin-article";
    }

    @GetMapping("/admin/article/insert")
    String insertArticle(){
        return "admin/admin-article-insert";
    }
}