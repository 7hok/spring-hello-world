package khmerhowto.Repository.Model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class History {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Content content;
    private LocalDateTime timestamp;

    @PrePersist
    private void prePersist(){
        this.timestamp = LocalDateTime.now();
    }

    public History() {
    }
    public History(User user, Content content) {
       
        this.user=user;
        this.content=content;
    }

    public History(int id, User user, Content content, LocalDateTime timestamp) {
        this.id=id;
        this.user=user;
        this.content=content;
        this.timestamp=timestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", user=" + user +
                ", content=" + content +
                ", timestamp=" + timestamp +
                '}';
    }
}
