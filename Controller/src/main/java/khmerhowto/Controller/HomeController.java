package khmerhowto.Controller;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.corundumstudio.socketio.SocketIOServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.HashMap;

import java.util.Map;
import java.util.logging.Handler;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import khmerhowto.Repository.CategoryRepository;
import khmerhowto.Repository.ContentRepository;
import khmerhowto.Repository.FavoriteCategoryRepository;
import khmerhowto.Repository.HistoryRepository;
import khmerhowto.Repository.UserRepository;
import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Content;
import khmerhowto.Repository.Model.FavoriteCategory;
import khmerhowto.Repository.Model.History;
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
@SessionAttributes("user")
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
    HistoryRepository historyRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * PROCESS 
     *      IF USER LOGGED IN 
     *          SAVE HISTORY CLICK 
     *          SUGGEST RELATED COMMON READ ARTICLE
     *      ELSE 
     *          SUGGEST POPUALAR POST
     * @param modelMap
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public String testDetail(ModelMap modelMap, @PathVariable Integer id) {
        modelMap.addAttribute("id", id);
        Page<Content> click_pages = null;
        if (GlobalFunctionHelper.getCurrentUser() == null) {
            modelMap.addAttribute("currentUser", new User());

            click_pages = contentRepository.findPopularContent(new PageRequest(0, 3));
            modelMap.addAttribute("RECOMMEND_ARTICLE", click_pages.getContent());
            } else {
            User cur_user =  GlobalFunctionHelper.getCurrentUser();
            if(interestedServiceImp.findByuserIdAndContentId(cur_user.getId(),id).size()==0){
                modelMap.addAttribute("liked","0");
            }
            else {
                modelMap.addAttribute("liked","1");
            }
            try {
                saveHistory(cur_user, new Content(id));
            } catch (Exception e) {
            }
            modelMap.addAttribute("currentUser",cur_user);
            click_pages = contentRepository.findContentBasedOnUserHistoryClick(cur_user.getId(),new PageRequest(0, 3));
            modelMap.addAttribute("RECOMMEND_ARTICLE", click_pages.getContent());
        }

        modelMap.addAttribute("totalCmt", cmt.getTotalComment(id));
        modelMap.addAttribute("like", interestedServiceImp.getTotalLike(id));
        return "clients/content-detail";
    }

    /*
    * SEARCH ARTICLE BY BODY OF CONTENT
    * */
    @GetMapping("/search/{str}")
    public String search(@PathVariable("str") String body, @RequestParam String type, ModelMap map) {
        map.addAttribute("type", type);

        map.addAttribute("body", body);
        return "search-body";
    }
    Boolean saveHistory(User user, Content content) {
        try {
            History history = new History(user, content);
            historyRepository.save(history);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @GetMapping("/about")
    String showAboutUs() {
        saveHistory(new User(1), new Content(1));
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

    /*
    * REQUEST CONTENT AND USE THYMELEAF TO GENERATE CARD BY PAGE NUMBER
    * GET 3 CONTENTS
    * */
    @GetMapping(value = "/conCard/{no}")
    String contentCard(ModelMap map, @PathVariable("no") Integer i) {
        Page<Content> lst;
        String b;
//        List<Content> list=new ArrayList<>();
        lst = con.findAll(PageRequest.of(i, 3, Sort.by(Sort.Direction.DESC, "Id")));
//         try {
//             list.addAll(lst.getContent());
//
//            for (int l = 0; l < list.size(); l++) {
//                b = list.get(l).getBody().replaceAll("<[^\\P{Graph}>]+(?: [^>]*)?>", "");
//                list.get(l).setBody(b);
//            }
//        }
//        catch(Exception e){
//
//        }
        map.addAttribute("contents",lst.getContent());

        Map<Integer, Integer> numCmt = new HashMap<>();
        for (int j = 0; j <= lst.getContent().size() - 1; j++) {
            numCmt.put(lst.getContent().get(j).getId(), cmt.getTotalComment(lst.getContent().get(j).getId()));
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int j = 0; j <= lst.getContent().size() - 1; j++) {
            m.put(lst.getContent().get(j).getId(), interestedServiceImp.getTotalLike(lst.getContent().get(j).getId()));
        }
        map.addAttribute("likes", m);
        map.addAttribute("cmts", numCmt);
        return "fragment/__content_card::cardList";
    }

    /*
    *   TO GENERATE CONTENTS CARD ON HOME PAGE
    *   USE THYMELEAF WITH FRAGMENT
    *   QUERY 3 CONTENT ON 1 PAGE
    * */

    @GetMapping(value = "/conCard")
    String content(ModelMap map) {
        Page<Content> lst = con.findAll(PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "Id")));
        List<Content> list=new ArrayList<>();
        String b;
        for(Integer i=0;i<lst.getContent().size();i++){
          list.add((Content) lst.getContent().get(i));
          b= list.get(i).getBody().replaceAll("<[^\\P{Graph}>]+(?: [^>]*)?>", "");
          list.get(i).setBody(b);
            System.out.println(b);
        }
        map.addAttribute("contents", list);

        return "fragment/__content_card::contentList";
    }

    @GetMapping(value = { "/homepage", "/home" ,"/"})
    String home(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<Content> pages = contentRepository.findPopularContent(new PageRequest(0, 3));
        List<Category> categories = categoryRepository.findByStatus(1);
        model.addAttribute("CURRENT_PAGE", "home");
        model.addAttribute("POPULAR_POST", pages.getContent());
        model.addAttribute("CATEGORIES", categories);
        User cur_user = GlobalFunctionHelper.getCurrentUser();
        if(cur_user == null){
            
        }else{
            Page<Content> click_pages = contentRepository.findContentBasedOnFavoriteCategory(cur_user.getId(),new PageRequest(0, 3));
            model.addAttribute("FAVORITE_CATEGORY_TOP_ARTICLE", click_pages.getContent());
        }
        return "client-home";
    }

    @GetMapping({ "/login" })
    String loginPage() {
        try {
            GlobalFunctionHelper.getCurrentUser().getId();
            return "redirect:/home";
        } catch (Exception ex) {
            return "oauth_login";
        }

    }

    @GetMapping("/favorite/chosen")
    String favorite(ModelMap modelMap) {
        List<Category> categories = categoryRepository.findByStatus(1);
        modelMap.addAttribute("CATEGORIES", categories);
        return "clients/CategoryChoosen";
    }

    @PostMapping("/favorite/chosen")
    String postFavorite(@RequestBody Map<String, Object> category) {

        try {
            Integer user_id = GlobalFunctionHelper.getCurrentUser().getId();
            List<Integer> category_id = (ArrayList<Integer>) category.get("category");
            List<FavoriteCategory> favList = new ArrayList<>();
            for (Integer integer : category_id) {

                favList.add(new FavoriteCategory(new User(user_id), new Category(integer)));
            }

            favoriteCategoryRepository.saveAll(favList);
            // return "redirect:/home";
        } catch (Exception e) {
            /**
             * CATCH WORK WHEN USER IS NOT AUTHENTICATED
             */
            System.out.println(e.getStackTrace());
            // return "redirect:/login";
        }
            return "redirect:/home";
        
    }

    /**
     * 
     * @param request
     * @param modelMap
     * @return 
     * IF user == Null THEN 
     *      new OBJECT => ModelMap ELSE GOOGLE REDIRECT =>
     * ELSE        
     *      OBJECT => ModelMap FORM ON SUBMIT
     * @POST => /signup
     */
    @GetMapping("/signup")
    String signUp(HttpServletRequest request, ModelMap modelMap) {
        User user = null;
        try {
            user = (User) request.getSession().getAttribute("USER");
            request.getSession().setAttribute("USER", null);
            // user = new User();
        } catch (Exception e) {
            System.out.println(e);
            user = null;
        }
        User _user = null;

        if (user == null) {
            System.out.println("user is null");
            _user = new User();
        } else {
            System.out.println("user is found");
            _user = user;
        }
        modelMap.addAttribute("USER", _user);
        return "sign-up";
    }

    /**
     * 
     * @param user
     * @return AFTER saveUser => autologin
     * 
     */
    @PostMapping("/signup")
    String registerToData(User user, HttpServletRequest httpServletRequest) {

        if(user.getBio()==null){
            user.setBio("");
        }
        if (user.getLocation()==null){
            user.setLocation("");
        }
        Boolean stt = userServiceImp.saveUser(user);
        if (stt == true) {
            GlobalFunctionHelper.autoLogin(user.getEmail(), user.getPassword(), httpServletRequest,
                    authenticationManager);
        } else {
            return "redirect:/login";
        }
        return "redirect:/favorite/chosen";
    }

    @GetMapping("/customize")
    String customizeUser(Model model) {
        User user = GlobalFunctionHelper.getCurrentUser();
        model.addAttribute("user", user);
        return "clients/userCustomize";
    }

    @PostMapping("/customize")
    String customizeUser(@ModelAttribute("user") User user, @RequestParam("files") MultipartFile file) {
        if (file.isEmpty()) {

        } else {

            try {
                user.setProfilePicture("/images/"+GlobalFunctionHelper.uploaded(file));
            } catch (Exception e) {
               
                e.printStackTrace();
            }

        }
        userServiceImp.saveUser(user);
        return "redirect:/home";
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
    private ContentRepository contentRepository;

    /*
    *   TO GET CONTENTS BY CATEGORY
    * */
    @GetMapping("/category")
    String category(Model model ){
        model.addAttribute("CURRENT_PAGE", "category");
        model.addAttribute("categories",categoryServiceImpl.findAll());
        return "content-by-category";

    }
    @GetMapping("/category/s/{id}")
    String categoryLeft(Model model, @PathVariable Integer id){
        model.addAttribute("categories",categoryServiceImpl.findAll());
        model.addAttribute("OneCategory",categoryRepository.findByIdAndStatus(id,1));

        return "content-by-category-test";
    }




}
