package com.example.file_system.accounts;

public class UserProfile {
    private final String nickName;
    private final String password;
    private final String email;

    public UserProfile(String nickName, String password, String email) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
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
}
