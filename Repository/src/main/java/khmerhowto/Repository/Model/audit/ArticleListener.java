package khmerhowto.Repository.Model.audit;

import javax.persistence.PostPersist;

import com.corundumstudio.socketio.SocketIOServer;

import org.springframework.beans.factory.annotation.Autowired;

import khmerhowto.Repository.Model.Content;

/**
 * ArticleListener
 */
public class ArticleListener {

    @Autowired
    SocketIOServer socket;

    @PostPersist
    void postPersist(Content content){
        socket.getBroadcastOperations().sendEvent("client",content);
    }
}