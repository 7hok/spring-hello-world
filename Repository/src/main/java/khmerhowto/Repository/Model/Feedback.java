package khmerhowto.Repository.Model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @ManyToOne
    @RestResource(exported = false)
    private User user;
    @ManyToOne
    @RestResource(exported = false)
    private Content content;
    private LocalDateTime timestamp;
    private Integer status;


    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
        status = 1;
    }

    public Feedback() {
    }

    public Feedback(int id, String text, User user, Content content, LocalDateTime timestamp,Integer status) {
        this.id=id;
        this.text=text;
        this.user=user;
        this.content=content;
        this.timestamp=timestamp;
        this.status = status;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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


    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
         this.status = status;
    }


    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", content=" + content +
                ", timestamp=" + timestamp +
                '}';
    }
}
