package khmerhowto.Service.ServiceImplement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        return feedBackRepository.findByStatus(1,pageable);
    }

    public Page<Feedback> findByDate(String date,Pageable pageable){
        LocalDateTime start_date = LocalDateTime.of(LocalDate.parse(date),LocalTime.of(0,0,0));
        LocalDateTime end_date = LocalDateTime.of(LocalDate.parse(date),LocalTime.of(23,59,59));
        Page<Feedback> pages = feedBackRepository.findByDate(start_date,end_date,pageable);
        return pages;
    }
}