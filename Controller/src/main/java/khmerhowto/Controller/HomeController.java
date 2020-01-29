package khmerhowto.Controller;


import khmerhowto.Repository.CategoryRepository;
import khmerhowto.Repository.ContentRepository;
import khmerhowto.Repository.Model.Content;

import khmerhowto.Service.ServiceImplement.CategoryServiceImpl;
import khmerhowto.Service.ServiceImplement.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


import java.util.HashMap;

import java.util.Map;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import khmerhowto.Repository.Model.Content;
import khmerhowto.Service.CommentService;
import khmerhowto.Service.ContentService;
import khmerhowto.Service.ServiceImplement.InterestedServiceImp;

import org.springframework.ui.Model;

import org.springframework.ui.ModelMap;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @Autowired
    ContentService con;
    @Autowired
    InterestedServiceImp interestedServiceImp;
    @Autowired
    CommentService cmt;
    @GetMapping("/about")
    String showAboutUs() {

        return "clients/about";
    }

    @GetMapping("/privacy")
    String showPrivacy() {
        return "clients/privacy";
    }

    @GetMapping("/example")
    String example() {
        return "myhometest";
    }

    @GetMapping(value = "/conCard/{no}")
    String contentCard(ModelMap map, @PathVariable("no") Integer i) {
        Page<Content> lst;
            lst = con.findAll(PageRequest.of(i, 3, Sort.by(Sort.Direction.DESC, "Id")));
        map.addAttribute("contents", lst.getContent());
        // map.addAttribute("totalCmt", cmt.getTotalComment(id));
        Map<Integer, Integer> numCmt = new HashMap<>();
        for (int j = 0; j <= lst.getContent().size()-1; j++) {
            numCmt.put(lst.getContent().get(j).getId(), cmt.getTotalComment(lst.getContent().get(j).getId()));

        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int j = 0; j <= lst.getContent().size()-1; j++) {
            m.put(lst.getContent().get(j).getId(), interestedServiceImp.getTotalLike(lst.getContent().get(j).getId()));

        }
        map.addAttribute("likes", m);
        map.addAttribute("cmts",numCmt);
        return "fragment/__content_card::cardList";
    }

    @GetMapping(value = "/conCard")
    String content(ModelMap map) {
        Page<Content> lst = con.findAll(PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "Id")));
        map.addAttribute("contents", lst.getContent());
        return "fragment/__content_card::contentList";
    }

    @GetMapping(value = { "/homepage", "/home" })
    String home(Model model) {
        model.addAttribute("CURRENT_PAGE", "home");
        // Page<Content> lst = con.findAll(PageRequest.of(0, 3,
        // Sort.by(Sort.Direction.DESC, "Id")));
        // model.addAttribute("contents", lst.getContent());
        // System.out.println(lst.getContent().get(0).getTitle());
        return "client-home";
    }

    @GetMapping({ "/logPage", "/" })
    String loginPage() {
        return "loginPage";
    }

    @GetMapping("/favorite/chosen")
    String favorite() {
        return "clients/CategoryChoosen";
    }

    @GetMapping("/signup")
    String signUp() {
        return "sign-up";
    }

    @GetMapping("/detail")
    String detail() {
        return "clients/contentDetail";
    }

    @GetMapping("/contentByCategory")
//<<<<<<< HEAD
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
//=======
//    String contentByCategory(Model model) {
//        model.addAttribute("CURRENT_PAGE", "category");
//        return "content-by-category";
//    }
//
//    @GetMapping("test/contentByCategory")
//    String contentByCategoryTest(Model model) {
//        model.addAttribute("CURRENT_PAGE", "category");
//>>>>>>> d4dd1a047db20884e5aa37da3505b53530ef2a3b
        return "content-by-category-test";

    }

//<<<<<<< HEAD
    @GetMapping("/category/{id}")
    String categoryLeft(Model model, @PathVariable Integer id){
        System.out.println("jab ban id: " + id);
        model.addAttribute("categories",categoryServiceImpl.findAll());
        model.addAttribute("OneCategory",categoryRepository.findByCategoryIdAndStatus());
//        model.addAttribute("contents", contentRepository.findContentsByCategoryId(id));
//        System.out.println("id in cR :"+ contentRepository.findContentsByCategoryId(id));
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
//=======
//}
//>>>>>>> d4dd1a047db20884e5aa37da3505b53530ef2a3b
