package khmerhowto.Repository.Model;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @ManyToOne
    private User user;
    @RestResource(exported = true)
    @ManyToOne
    private Content content;
    private int timestamp;


    public Feedback() {
    }

    public Feedback(int id, String text, User user, Content content, int timestamp) {
        this.id=id;
        this.text=text;
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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
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
