package khmerhowto.Repository.Model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

//import khmerhowto.Repository.globalFunction.GlobalFunctionHelper;
import khmerhowto.Repository.Model.audit.ArticleListener;
import org.springframework.context.event.EventListener;
import org.springframework.data.rest.core.annotation.RestResource;
@EntityListeners(ArticleListener.class)
@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @RestResource(exported = false)
    private Category category;
    @ManyToOne
    @RestResource(exported = false)
    private User user;
    private String thumbnail;
    private String title;
    private String body;

    private Integer status;
    private LocalDateTime timestamp;

    public Content() {
    }

    @PrePersist
    public void prePersist() {

        timestamp = LocalDateTime.now();
        status = 1;
    }
    @PreUpdate
    public void preUpdate() {

        timestamp = LocalDateTime.now();
        status = 1;
    }

    public Content(int id, Category category, String thumbnail, String title, String body, User user, Integer status, LocalDateTime timestamp) {

        this.id = id;
        this.category = category;
        this.thumbnail = thumbnail;
        this.title = title;
        this.body = body;
        this.user = user;
        this.status = status;
        this.timestamp = timestamp;

    }

    public Content(int id) {
        this.id = id;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
        return "Content{" +
            "id=" + id +
            ", category=" + category +
            ", thumbnail='" + thumbnail + '\'' +
            ", title='" + title + '\'' +
            ", body='" + body + '\'' +
            ", user='" + user + '\'' +
            ", status='" + status + '\'' +
            ", timestamp=" + timestamp +
            '}';
    }
}


