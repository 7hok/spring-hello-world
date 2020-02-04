package khmerhowto.Repository.Model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @RestResource(exported = false)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @RestResource(exported = false)
    private Content content;
    private String text;
    private Integer status;
    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
        status = 1;
    }
    public Comment() {
    }

    public Comment(int id, User user, Content content, String text, Integer status, LocalDateTime timestamp) {
        this.id=id;
        this.user=user;
        this.content=content;
        this.text=text;
        this.status=status;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", content=" + content +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
