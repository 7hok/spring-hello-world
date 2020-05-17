package khmerhowto.Repository.Model;

import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class ContentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String text;
    @ManyToOne
    @RestResource(exported = false)
    private User user;
    private LocalDateTime timestamp;
    private Integer status;

    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
        status = 1;
    }

    public ContentRequest() {
    }

    public ContentRequest(int id, String title, String text, User user, LocalDateTime timestamp,Integer status) {
        this.id=id;
        this.title=title;
        this.text=text;
        this.user=user;
        this.timestamp=timestamp;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer status() {
        return this.status;
    }

    public void status(Integer status){
        this.status = status;
    }
    @Override
    public String toString() {
        return "ContentRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", timestamp=" + timestamp +
                '}';
    }
}
