package khmerhowto.configuration;

import javax.annotation.PreDestroy;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SocketConfiguration
 */
@Order(1)
@org.springframework.context.annotation.Configuration
public class SocketConfiguration {

    SocketIOServer server;

    @Bean
    public SocketIOServer socketIOServer(){
        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(9009);

        /**
         * not to reopen application
         */
        SocketConfig socketConfig = config.getSocketConfig();
        socketConfig.setReuseAddress(true);

        server = new SocketIOServer(config);
        server.start();
        return server;

 
    }

    @PreDestroy
    public void closeConnection(){
        System.out.println("server stop");
        this.server.stop();
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
      return new SpringAnnotationScanner(socketServer);
    }

}
