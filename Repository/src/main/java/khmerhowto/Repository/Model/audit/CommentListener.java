package khmerhowto.Repository.Model.audit;

import javax.persistence.PostPersist;

import com.corundumstudio.socketio.SocketIOServer;

import org.springframework.beans.factory.annotation.Autowired;

import khmerhowto.Repository.Model.Comment;

/**
 * CommentListener
 */
public class CommentListener {
    @Autowired
    SocketIOServer socket;

    @PostPersist
    void PostPersist(Comment comment){
        socket.getBroadcastOperations().sendEvent("admin", comment);
    }
}