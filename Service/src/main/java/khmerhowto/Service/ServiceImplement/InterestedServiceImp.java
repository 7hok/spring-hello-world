package khmerhowto.Service.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import khmerhowto.Repository.FeedbackRepository;
import khmerhowto.Repository.InterestRepository;
import khmerhowto.Repository.Model.Feedback;
import khmerhowto.Service.InterestedService;

/**
 * FeedBackServiceImp
 */
@Service
public class InterestedServiceImp implements InterestedService{
    
    @Autowired
    private InterestRepository interestRepository;

    @Override
    public Integer getTotalLike(Integer id) {

        return interestRepository.getTotalLike(id);
    }
}