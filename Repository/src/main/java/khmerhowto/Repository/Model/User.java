package khmerhowto.Repository.Model;


import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "kl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "profile_picture")
    private String profilePicture;
    private String sex;
    private String email;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String bio;
    private String location;
    private LocalDateTime timestamp;
    private Integer status;
    /** for save user last click on history  */
    private LocalDateTime lastNotificationClick;
    @PrePersist
    public void prePersist(){
        this.status = 1;
        this.timestamp = LocalDateTime.now();
        this.lastNotificationClick = LocalDateTime.now();
    }

    public User() {
    }
    public User(String name,String password){
        this.name = name;
        this.password = password;
    }
    public User(int id, String name, String profilePicture, String sex, String email, String phoneNumber, String bio, String location, LocalDateTime timestamp,Integer status,String password,LocalDateTime lastNotificationClick) {
        this.id=id;
        this.name=name;
        this.profilePicture=profilePicture;
        this.sex=sex;
        this.email=email;
        this.password = password;
        this.phoneNumber=phoneNumber;
        this.bio=bio;
        this.location=location;
        this.timestamp=timestamp;
        this.status = status;
        this.lastNotificationClick = lastNotificationClick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus(){
        return this.status;
    }

     /* @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param lastNotificationClick the lastNotificationClick to set
     */
    public void setLastNotificationClick(LocalDateTime lastNotificationClick) {
        this.lastNotificationClick = lastNotificationClick;
    }

    /**
     * @return the lastNotificationClick
     */
    public LocalDateTime getLastNotificationClick() {
        return lastNotificationClick;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", timestamp=" + timestamp +
                ", password=" + password+
                ", click=" + lastNotificationClick+'\''+
                '}';
    }
}
