package khmerhowto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import groovyjarjarpicocli.CommandLine.Model;
import khmerhowto.Service.CommentService;
 
import khmerhowto.Service.ServiceImplement.InterestedServiceImp;

 

@Controller
public class DbTest {
    @Autowired
    CommentService cmt;
    @Autowired
    InterestedServiceImp interestedServiceImp;

    @GetMapping("/detail/{id}")
    public String testDetail(ModelMap modelMap, @PathVariable Integer id) {
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("totalCmt", cmt.getTotalComment(id));
        modelMap.addAttribute("like", interestedServiceImp.getTotalLike(id));
        return "clients/contentDetail";
    }

    @GetMapping("/c")
    public String ckEdit() {
        return "loginPage";
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
    String insertArticle() {
        return "admin/admin-article-insert";
    }

    @GetMapping("/admin/article/edit/{id}")
    String editArticle(@PathVariable String id, ModelMap model) {
        model.addAttribute("id", id);
        return "admin/admin-article-edit";
    }

    @GetMapping("/testing-style")
    public String test() {
        return "test";
    }

}
