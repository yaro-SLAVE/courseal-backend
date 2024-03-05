package online.courseal.courseal_backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "usertag"),
        @UniqueConstraint(columnNames = "email")
    })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "usertag")
    private String userTag;
    @Column(name = "username")
    private String userName;
    private String password;
    private String email;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "can_create_courses")
    private boolean canCreate;

    public User(){}

    public User(String userTag, String userName, String password, String email, LocalDateTime dateCreated, Boolean canCreate){
        this.userTag = userTag;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
        this.canCreate = canCreate;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getUserTag(){
        return this.userTag;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }

    public LocalDateTime getDateCreated(){
        return this.dateCreated;
    }

    public boolean getCanCreate(){
        return this.canCreate;
    }

    public void setUserTag(String userTag){
        this.userTag = userTag;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setDateCreated(LocalDateTime dateCreated){
        this.dateCreated = dateCreated;
    }

    public void setCanCreate(boolean canCreate){
        this.canCreate = canCreate;
    }
}
