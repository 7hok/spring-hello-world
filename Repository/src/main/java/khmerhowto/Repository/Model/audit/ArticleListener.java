package khmerhowto.Repository.Model.audit;

import javax.persistence.PostPersist;

import com.corundumstudio.socketio.SocketIOServer;

import jdk.nashorn.internal.runtime.GlobalFunctions;
import khmerhowto.Repository.FavoriteCategoryRepository;
import khmerhowto.Repository.Model.FavoriteCategory;
import org.springframework.beans.factory.annotation.Autowired;

import khmerhowto.Repository.Model.Content;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

/**
 * ArticleListener
 */
public class ArticleListener {

    @Autowired
    SocketIOServer socket;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    FavoriteCategoryRepository favoriteCategoryRepository;

    public  void sendEmail() {
//        System.out.println("call email fun");
//        List<FavoriteCategory> lst= favoriteCategoryRepository.findAllByCategoryId(1);
//        String[] emailArray = new String[90];
//        SimpleMailMessage msg = new SimpleMailMessage();
//        for (int i=0;i<lst.size();i++){
//            emailArray[i]=lst.get(i).getUser().getEmail();
//
//        }
//        for (int i=0;i<lst.size();i++){
//            msg.setTo(emailArray[i]);
//            msg.setSubject("Kunloes Update");
//            msg.setText("catery that u like have been updated. Please click the link" );
//            javaMailSender.send(msg);
//
//        }
    }
    @PostPersist
    void postPersist(Content content){
        System.out.println("asdea");
//        try{
//            System.out.println(socket   );
//            socket.getBroadcastOperations().sendEvent("client",content);
//            System.out.println(content);
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
        sendEmail();
    }
}
