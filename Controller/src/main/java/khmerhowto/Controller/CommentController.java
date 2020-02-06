package khmerhowto.Controller;

import khmerhowto.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentController {
    @Autowired
    CommentRepository repository;

//    @GetMapping("/api/comment/insert")
//    public String
}
