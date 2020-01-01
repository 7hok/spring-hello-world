package khmerhowto.Repository.Model;


import javax.persistence.*;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Category category;

    private String thumbnail;
    private String title;
    private String body;
    private String writer;
    private String status;
    private int timestamp;

    public Content() {
    }

    public Content(int id, Category category, String thumbnail, String title, String body, String writer, String status, int timestamp) {
        this.id=id;
        this.category=category;
        this.thumbnail=thumbnail;
        this.title=title;
        this.body=body;
        this.writer=writer;
        this.status=status;
        this.timestamp=timestamp;
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
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
                ", writer='" + writer + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}


