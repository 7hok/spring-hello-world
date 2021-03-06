package khmerhowto.Controller;

import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Content;
import khmerhowto.Repository.Model.ContentRequest;

import khmerhowto.Repository.Model.Role;

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
import khmerhowto.Service.ServiceImplement.ContentServiceImp;
import khmerhowto.globalFunction.GlobalFunctionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * AdminController
 * MOST of algorithm 
 *   1.  ALL :  SERVICE -> REPOSITORY : FindByStatus1
 *   2.  FILTER : REQUEST PARAM AS Parameter
 *   3.  CONDITION 
 *   3.1      IF REQUEST PARAM = NULL
 *   3.1.1         ALL
 *   3.2      ELSE
 *   3.2.1         FILTER      
 */
@Controller
@SessionAttributes("user")
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
    @Autowired 
    private ContentServiceImp  articleService;
    @PersistenceContext
    EntityManager em;
    @GetMapping("/admin/article/edit/{id}")
    String editArticle(@PathVariable Integer id, ModelMap model) {
        List<Category> lst=categoryService.findCategoryByStatus(1);
        model.addAttribute("categories",lst);
        Content con=em.find(Content.class,id);
        model.addAttribute("cId", con.getCategory().getId());
        model.addAttribute("id", id);
        model.addAttribute("categories", categoryService.findCategoryByStatus(1));
        if (GlobalFunctionHelper.getCurrentUser() == null) {
            model.addAttribute("currentUser", new User());
        } else {
            model.addAttribute("currentUser", GlobalFunctionHelper.getCurrentUser());

        }
        return "admin/admin-article-edit";
    }

    @GetMapping("/admin/feedback")
    String manageFeedBack(@PageableDefault(size = 10)Pageable pageable,@RequestParam(value = "date" , required = false)String date ,Model model){
        Page<Feedback>page;
        if(date == null ){
            page = feedbackservice.findAll(pageable);
        }else{
            page = feedbackservice.findByDate(date,pageable);
        }
        List<Feedback>feedbacks = page.getContent();
        model.addAttribute("page",page);
        model.addAttribute("feedbacks",feedbacks);
        model.addAttribute("CURRENT_PAGE", "feedback");
        return "admin/admin-feedback";
    }
    @GetMapping("/admin/question")
    String manageQuestion(@PageableDefault(size = 10)Pageable pageable,@RequestParam(value = "date" , required = false)String date ,Model model){
        Page<ContentRequest>page;
        if(date == null ){
            page = contentRequestService.findAll(pageable);
        }else{
            page = contentRequestService.findByDate(date,pageable);
        }
        List<ContentRequest>questions = page.getContent();
        model.addAttribute("page",page);
        model.addAttribute("questions",questions);
        model.addAttribute("CURRENT_PAGE", "question");
        return "admin/admin-question";
    }
    @GetMapping("/admin/user")
    String manageUser(@RequestParam(value = "search" , required = false)String userName, @PageableDefault(size = 10)Pageable pageable, Model model){

        Page<User> page =null;
    
        if(userName!=null){
            page =userService.findByName(userName,pageable);

        }else {
    
            page = userService.findAll(pageable);
        }
        

        model.addAttribute("page",page);
        model.addAttribute("users",page.getContent());

        model.addAttribute("CURRENT_PAGE", "user");


        return "admin/admin-user";
    }

    @GetMapping("/admin/customize")
    String customizeInfoStatic(Model model){
        model.addAttribute("CURRENT_PAGE", "setting");
        return "admin/admin-customize-user";
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
        if (id==-1){
            id=GlobalFunctionHelper.getCurrentUser().getId();
        }
        model.addAttribute("role",roleService.findAll());

        model.addAttribute("user",userService.findById(id));
        System.out.println(userService.findById(id).getProfilePicture());
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

    @GetMapping("/admin/article/insert")
    String insertArticle(ModelMap map) {
        map.addAttribute("categories", categoryService.findCategoryByStatus(1));
        if (GlobalFunctionHelper.getCurrentUser() == null) {
            map.addAttribute("currentUser", new User());
        } else {
            map.addAttribute("currentUser", GlobalFunctionHelper.getCurrentUser());
            System.out.println("s"+ GlobalFunctionHelper.getCurrentUser());
        }
        return "admin/admin-article-insert";
    }

    @GetMapping("/admin/article")
    String manageArticle(@PageableDefault(size = 10)Pageable pageable,@RequestParam(value = "category_id", defaultValue ="0",required = false)Integer c_id,@RequestParam(value = "search" ,required = false) String search_text ,Model model){
        

        Page<Content>page;
        if(search_text == null && c_id ==0){
            page = articleService.findAll(pageable);
        }else{
            page = articleService.findByNameAndCategory(search_text,c_id, pageable);
        } 
      
        List<Content>articles = page.getContent();
        List<Category> categories = categoryService.findCategoryByStatus(1);
        model.addAttribute("CURRENT_PAGE", "article");
        model.addAttribute("page",page);
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);
        return "admin/admin-article";
    }

    
  
}
