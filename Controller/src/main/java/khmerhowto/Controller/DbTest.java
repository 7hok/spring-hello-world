package khmerhowto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import groovyjarjarpicocli.CommandLine.Model;
import khmerhowto.Service.CommentService;
import khmerhowto.Service.ServiceImplement.CategoryServiceImp;
import khmerhowto.Service.ServiceImplement.InterestedServiceImp;

 

@Controller
public class DbTest {
    @Autowired
    CommentService cmt;
    @Autowired
    InterestedServiceImp interestedServiceImp;
    @Autowired
    private CategoryServiceImp categoryService;
    @GetMapping("/detail/{id}")
    public String testDetail(ModelMap modelMap, @PathVariable Integer id) {
        System.out.println("jab ban id content detail hz: "+id);
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("totalCmt", cmt.getTotalComment(id));
        modelMap.addAttribute("like", interestedServiceImp.getTotalLike(id));
        return "clients/contentDetail";
    }

    @GetMapping("/search/{str}")
    public String search(@PathVariable("str") String body,@RequestParam String type,ModelMap map) {
        map.addAttribute("type", type);
 
        map.addAttribute("body", body);
        return "search-body";
    }

    @GetMapping("/s")
    public String ckEdit1() {
        return "sign-up";
    }

    @GetMapping("/d")
    public String ckEdit2() {
        return "clients/contentDetail";
    }

    @GetMapping("/admin/article/insert")
    String insertArticle(ModelMap map) {
        map.addAttribute("categories",categoryService.findCategoryByStatus(1));
        return "admin/admin-article-insert";
    }


    @GetMapping("/testing-style")
    public String test() {
        return "test";
    }

}
