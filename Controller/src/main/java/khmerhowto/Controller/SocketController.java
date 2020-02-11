package khmerhowto.Controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import khmerhowto.Repository.Model.User;

/**
 * SocketController
 */
@Component
public class SocketController {

    @Autowired
    private SocketIOServer server;
  
    @OnConnect
    public void onConnect(SocketIOClient client) {
        /*
       System.out.println("connected");
       */
    }  
    @OnDisconnect
    public void onDisConnect(SocketIOClient client) {
        /*
        System.out.println("disconnected");
        */
    }
    @OnEvent("gettingstart")
    public void name(String msg) {
        /*
        System.out.println(msg);
      
        server.getBroadcastOperations().sendEvent("client", new User("menghok","menghok"));

        */
    }
}