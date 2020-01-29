package khmerhowto.Controller;

import khmerhowto.Repository.CategoryRepository;
import khmerhowto.Repository.ContentRepository;
import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Content;
import khmerhowto.Service.CategoryService;
import khmerhowto.Service.ServiceImplement.CategoryServiceImp;
import khmerhowto.Service.ServiceImplement.CategoryServiceImpl;
import khmerhowto.Service.ServiceImplement.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;



/**
 * HomeController
 */
@Controller
public class HomeController {
    @GetMapping("/about")
    String showAboutUs(){

        return "clients/about";
    }
    @GetMapping("/privacy")
    String showPrivacy(){
        return "clients/privacy";
    }
    @GetMapping("/example")
    String example(){
        return "myhometest";
    }

    @GetMapping(value = {"/homepage","/home"})
    String home(Model model){
        model.addAttribute("CURRENT_PAGE", "home");
        return "client-home";
    }

    @GetMapping({"/logPage","/"})
    String loginPage(){
        return "loginPage";
    }
    @GetMapping("/favorite/chosen")
    String favorite(){
        return "clients/CategoryChoosen";
    }

    @GetMapping("/signup")
    String signUp(){
        return "sign-up";
    }

    @GetMapping("/detail")
    String detail(){
        return "clients/contentDetail";
    }

    @GetMapping("/contentByCategory")
    String contentByCategory(Model model){
        return "content-by-category";
    }

    //Chamroeun

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    private ContentServiceImpl contentService;
    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/category")
    String category(Model model){
        model.addAttribute("categories",categoryServiceImpl.findAll());
        System.out.println(categoryServiceImpl.findAll());
        System.out.println("JPQfL"+categoryRepository.findByCategoryIdAndStatus());
        return "content-by-category-test";

    }

    @GetMapping("/category/{id}")
    String categoryLeft(Model model, @PathVariable Integer id){
        System.out.println("jab ban id: " + id);
        model.addAttribute("categories",categoryServiceImpl.findAll());
        model.addAttribute("OneCategory",categoryRepository.findByCategoryIdAndStatus());
        model.addAttribute("contents", contentRepository.findContentsByCategoryId(id));
        System.out.println("id in cR :"+ contentRepository.findContentsByCategoryId(id));
        System.out.println("Hello");
        model.addAttribute("myContent", contentRepository.findFirst2ByCategoryId(id));
        System.out.println("why u"+contentRepository.findFirst2ByCategoryId(id));


        return "content-by-category-test";
    }

@GetMapping("/test/contentByCategory")
    String contentByCategoryTest(){
//        List <Content> contents = contentService.findAll();
//        System.out.println(contents);
////        model.addAttribute("CURRENT_PAGE", "category");
//        model.addAttribute("contents", contents);
            return "content-by-category-test";
            }
            //End of Chamroeun
            }