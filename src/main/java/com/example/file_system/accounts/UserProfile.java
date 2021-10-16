package com.example.file_system.accounts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserProfile  implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", unique = true, updatable = false)
    private String nickName;

    @Column(name = "user_password", updatable = false)
    private String password;

    @Column(name = "user_email", updatable = false)
    private String email;

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile(String nickName, String password, String email) {
        this.id = -1;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }

    public long getId(){
        return id;
    }

    public String getNickName(){
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
