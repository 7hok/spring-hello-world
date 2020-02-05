package khmerhowto.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


import java.util.HashMap;

import java.util.Map;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import khmerhowto.Repository.CategoryRepository;
import khmerhowto.Repository.ContentRepository;
import khmerhowto.Repository.FavoriteCategoryRepository;
import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Content;
import khmerhowto.Repository.Model.FavoriteCategory;
import khmerhowto.Repository.Model.User;
import khmerhowto.Service.CommentService;
import khmerhowto.Service.ContentService;
import khmerhowto.Service.ServiceImplement.CategoryServiceImpl;
import khmerhowto.Service.ServiceImplement.ContentServiceImpl;
import khmerhowto.Service.ServiceImplement.InterestedServiceImp;
import khmerhowto.Service.ServiceImplement.UserServiceImp;
import khmerhowto.globalFunction.GlobalFunctionHelper;

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
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    FavoriteCategoryRepository favoriteCategoryRepository;
    
    @Autowired
    AuthenticationManager authenticationManager;

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
        System.out.println(lst.getContent().get(0).getThumbnail());
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
        System.out.println(lst.getContent());
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

    @GetMapping({ "/login", "/" })
    String loginPage() {
        return "oauth_login";
    }

    @GetMapping("/favorite/chosen")
    String favorite(ModelMap modelMap){
        List<Category> categories= categoryRepository.findByStatus(1);
        modelMap.addAttribute("CATEGORIES", categories);
        return "clients/CategoryChoosen";
    }
    @PostMapping("/favorite/chosen")
    String postFavorite(@RequestBody Map<String,Object> category){
         
            try {
                Integer user_id =GlobalFunctionHelper.getCurrentUser().getId();
                List<Integer> category_id =  (ArrayList<Integer>) category.get("category");
                List<FavoriteCategory> favList = new ArrayList<>();
                for (Integer integer : category_id) {
                    
                    favList.add(new FavoriteCategory(new User(user_id),new Category(integer)) );
                }

                favoriteCategoryRepository.saveAll(favList);
                return "redirect:/home";
            } catch (Exception e) {
                /**
                 * CATCH WORK WHEN USER IS NOT AUTHENTICATED
                 */
                return "redirect:/login";
            }
    }
    /**
     * 
     * @param request
     * @param modelMap
     * @return
     *  IF user == Null THEN
     *      new OBJECT => ModelMap
     *  ELSE
     *      GOOGLE REDIRECT => OBJECT => ModelMap
     *  FORM ON SUBMIT
     *      @POST => /signup
     */
    @GetMapping("/signup")
    String signUp(HttpServletRequest request,ModelMap modelMap) {
        User user = null;
        try {
           user = (User) request.getSession().getAttribute("USER");
           request.getSession().setAttribute("USER", null);
            // user = new User();  
        } catch (Exception e) {
            System.out.println(e);
           user = null;
        }
        User _user=null;
        
        if(user ==null ){
            System.out.println("user is null");
            _user = new User();
        }else{
            System.out.println("user is found");
            _user = user;
        }
        modelMap.addAttribute("USER", _user);
        return "sign-up";
    }
    /**
     * 
     * @param user
     * @return
     * AFTER 
     *      saveUser => autologin
     * 
     */
    @PostMapping("/signup")
    String registerToData(User user,HttpServletRequest httpServletRequest){
        System.out.println(user);
        Boolean stt = userServiceImp.saveUser(user);
        if(stt == true){
            GlobalFunctionHelper.autoLogin(user.getEmail(), user.getPassword(), httpServletRequest,authenticationManager);
        }else{
            return "redirect:/login";
        }
        return "redirect:/favorite/chosen";
    }
    @GetMapping("/detail")
    String detail() {
        return "clients/contentDetail";
    }

    @GetMapping("/contentByCategory")
    String contentByCategory(Model model){
        model.addAttribute("CURRENT_PAGE", "category");
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
    String category(Model model ){
        model.addAttribute("categories",categoryServiceImpl.findAll());
        return "test-cate";

    } 
    @GetMapping("/category/s/{id}")
    String categoryLeft(Model model, @PathVariable Integer id){
        System.out.println("jab ban id: " + id);
        model.addAttribute("categories",categoryServiceImpl.findAll());
        model.addAttribute("OneCategory",categoryRepository.findByIdAndStatus(id,1));

        return "content-by-category-test";
    }

    @GetMapping("/test/contentByCategory")
    String contentByCategoryTest(Model model){

        model.addAttribute("CURRENT_PAGE", "category");

            return "content-by-category-test";
            }
}
