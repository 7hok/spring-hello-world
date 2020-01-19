package khmerhowto.Controller;

import khmerhowto.Repository.Model.ContentRequest;
import khmerhowto.Repository.Model.Feedback;
import khmerhowto.Repository.Model.User;
import khmerhowto.Repository.Model.UserRole;
import khmerhowto.Repository.UserRepository;
import khmerhowto.Repository.UserRoleRepository;
import khmerhowto.Service.ContentRequestService;
import khmerhowto.Service.RoleService;
import khmerhowto.Service.UserRoleSrvice;
import khmerhowto.Service.UserService;
import khmerhowto.Service.ServiceImplement.CategoryServiceImp;
import khmerhowto.Service.ServiceImplement.FeedBackServiceImp;

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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * AdminController
 */
@Controller
@RequestMapping("/dynas/")
public class AdminController {
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
    String manageFeedBack(@PageableDefault(size = 10)Pageable pageable,Model model){
        Page<Feedback>page = feedbackservice.findAll(pageable);
        List<Feedback>feedbacks = page.getContent();
        model.addAttribute("page",page);
        model.addAttribute("feedbacks",feedbacks);
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

        Page<User> page =null;
        page =userService.findAll(pageable);    
        List<User> users = null;
        model.addAttribute("user",user);
        if(user.getName()!=null){
            users=userService.findByName(user.getName());

        }else {
            users=page.getContent();
        }

        System.out.println(user.getName());
        model.addAttribute("page",page);
        model.addAttribute("users",users);

        model.addAttribute("CURRENT_PAGE", "user");


        return "admin/admin-user";
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
        model.addAttribute("categories",categoryService.findCategoryByStatus(1));
        return "admin/admin-category";
    }


    @GetMapping("/admin/customize/{id}")
    String customizeInfo(@PathVariable("id")Integer id,Model model){
        model.addAttribute("role",roleService.findAll());
        model.addAttribute("user",userService.findById(id));
//        model.addAttribute("userRole",userRoleSrvice.findRoleByUserId(id));
        model.addAttribute("CURRENT_PAGE", "setting");

        return "admin/admin-customize-user";
    }
    @PostMapping("/admin/customize/{id}")
    String updateInfo(@PathVariable("id")Integer id, @ModelAttribute User user, BindingResult bindingResult, Model model){

            if (bindingResult.hasErrors()){
                System.out.println("error aii");
                return "admin/admin-customize-user";
            }
            else {
                userRoleSrvice.updateRole(id,2);
                System.out.println(userRoleSrvice.findRoleByUserId(id));
                userService.updateUser(id,user);
                System.out.println("update Success aii");
                return "redirect:/admin/user";
            }
    }



    @GetMapping("/admin/article")
    String manageArticle(Model model){
        model.addAttribute("CURRENT_PAGE", "article");
        return "admin/admin-article";
    }

    @GetMapping("/admin/article/insert")
    String insertArticle(){
        return "admin/testartcle";
    }
}