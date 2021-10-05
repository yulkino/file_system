package com.example.file_system.accounts;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private static Map<String, UserProfile> loginToProfile = new HashMap<String, UserProfile>();
    private static Map<String, HttpSession> loginToSession = new HashMap<String, HttpSession>();

    public void AddLoginToProfile(UserProfile userProfile){
        loginToProfile.put(userProfile.getNickName(), userProfile);
    }

    public void AddLoginToSession(UserProfile userProfile, HttpSession httpSession){
        loginToSession.put(userProfile.getNickName(), httpSession);
    }

    public void DeleteSession(String sessionId){
        loginToSession.remove(GetUserLogin(sessionId));
    }

    public boolean IsNotUserExist(String nickName){
        return !loginToProfile.containsKey(nickName);
    }

    public String GetUserLogin(String session){
        return loginToSession
                .entrySet()
                .stream()
                .filter(s -> s.getValue().getId().equals(session))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public boolean IsSessionExist(String nickName, HttpSession httpSession){
        return loginToSession.get(nickName).equals(httpSession);
    }

    public UserProfile GetUserProfile(String nickname){
        return loginToProfile.get(nickname);
    }
}
