package khmerhowto.Service.ServiceImplement;

import khmerhowto.Repository.ContentRequestRepository;
import khmerhowto.Repository.Model.ContentRequest;
import khmerhowto.Service.ContentRequestService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContentRequestServiceImp implements ContentRequestService {
    @Autowired
    private ContentRequestRepository contentRequestRepository;
    @Override
    public Page<ContentRequest> findAll(Pageable pageable) {
        return contentRequestRepository.findByStatus(1,pageable);
    }
    public Page<ContentRequest> findByDate(String date,Pageable pageable){
        LocalDateTime start_date = LocalDateTime.of(LocalDate.parse(date),LocalTime.of(0,0,0));
        LocalDateTime end_date = LocalDateTime.of(LocalDate.parse(date),LocalTime.of(23,59,59));
        System.out.println(start_date.toString()+end_date.toString());
        Page<ContentRequest> pages = contentRequestRepository.findByDate(start_date,end_date,pageable);
        return pages;
    }
}
