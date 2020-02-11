package khmerhowto.Controller;

import khmerhowto.Repository.Model.User;
import khmerhowto.globalFunction.GlobalFunctionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import khmerhowto.Service.CommentService;
import khmerhowto.Service.ServiceImplement.CategoryServiceImp;
import khmerhowto.Service.ServiceImplement.InterestedServiceImp;


@Controller
public class DbTest {
//    @Autowired
//    CommentService cmt;
//    @Autowired
//    InterestedServiceImp interestedServiceImp;
//    @Autowired
//    private CategoryServiceImp categoryService;
//
//    @GetMapping("/detail/{id}")
//    public String testDetail(ModelMap modelMap, @PathVariable Integer id) {
//        modelMap.addAttribute("id", id);
//        if (GlobalFunctionHelper.getCurrentUser() == null) {
//            modelMap.addAttribute("currentUser", new User());
//        } else {
//            modelMap.addAttribute("currentUser", GlobalFunctionHelper.getCurrentUser());
//
//        }
//
//        modelMap.addAttribute("totalCmt", cmt.getTotalComment(id));
//        modelMap.addAttribute("like", interestedServiceImp.getTotalLike(id));
//        return "Content-Detail";
//    }
//
//    @GetMapping("/search/{str}")
//    public String search(@PathVariable("str") String body, @RequestParam String type, ModelMap map) {
//        map.addAttribute("type", type);
//
//        map.addAttribute("body", body);
//        return "search-body";
//    }
//
//    @GetMapping("/s")
//    public String ckEdit1() {
//        return "sign-up";
//    }
//
//    @GetMapping("/d")
//    public String ckEdit2() {
//        return "Content-Detail";
//    }
//
//
//
//
//    @GetMapping("/testing-style")
//    public String test() {
//        return "test";
//    }

}
