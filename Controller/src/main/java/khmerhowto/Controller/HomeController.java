package khmerhowto.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import khmerhowto.Repository.Model.Content;
import khmerhowto.Service.CommentService;
import khmerhowto.Service.ContentService;
import khmerhowto.Service.ServiceImplement.ContentServiceImp;
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
    String contentByCategory(Model model) {
        model.addAttribute("CURRENT_PAGE", "category");
        return "content-by-category";
    }

    @GetMapping("test/contentByCategory")
    String contentByCategoryTest(Model model) {
        model.addAttribute("CURRENT_PAGE", "category");
        return "content-by-category-test";
    }

}