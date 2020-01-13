package khmerhowto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

@Controller
public class DbTest {

    @GetMapping("/c")
    public String ckEdit(){
        return "loginPage";
    }

    @GetMapping("/testing-style")
    public String test(){
        return "test";
    }
//    @Autowired
//    private DataSource dataSource;
//
//    @GetMapping("/d")
//    @ResponseBody
//    public String d() throws Exception {
//
//        dataSource.getConnection();
//        return "work";
//    }

}
