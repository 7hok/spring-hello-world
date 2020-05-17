package khmerhowto.Service.ServiceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khmerhowto.Repository.CommentRepository;
import khmerhowto.Service.CommentService;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Integer getTotalComment(Integer id) {
        return commentRepository.getTotalComment(id);
    }
     

}
