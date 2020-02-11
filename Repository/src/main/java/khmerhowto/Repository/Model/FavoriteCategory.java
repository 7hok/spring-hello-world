package khmerhowto.Repository.Model;

import javax.persistence.*;

@Entity
@Table(name = "Favorite_Category",uniqueConstraints={
    @UniqueConstraint(columnNames = {"user_id", "category_id"})
})
public class FavoriteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    private boolean status;

    @PrePersist
    private void prePersist(){
        this.status = true;
    }
    public FavoriteCategory() {
    }
    public FavoriteCategory( User user, Category category) {
        this.user=user;
        this.category=category;
    }
    public FavoriteCategory(int id, User user, Category category, boolean status) {
        this.id=id;
        this.user=user;
        this.category=category;
        this.status=status;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FavoriteCategory{" +
                "id=" + id +
                ", user=" + user +
                ", category=" + category +
                ", status=" + status +
                '}';
    }
}
