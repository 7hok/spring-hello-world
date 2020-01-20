package khmerhowto.Service.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import khmerhowto.Repository.FeedbackRepository;
import khmerhowto.Repository.Model.Feedback;

/**
 * FeedBackServiceImp
 */
@Service
public class FeedBackServiceImp {
    
    @Autowired
    private FeedbackRepository feedBackRepository;

    public Page<Feedback> findAll(Pageable pageable) {
        return feedBackRepository.findAll(pageable);
    }
}