package com.example.file_system.accounts;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import com.example.file_system.dbService.DBException;
import com.example.file_system.dbService.DBService;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private static final DBService loginToProfile = new DBService();
    private static final Map<String, HttpSession> loginToSession = new HashMap<>();

    public void AddLoginToProfile (UserProfile userProfile) throws DBException {
        loginToProfile.addUser(new UserProfile(userProfile.getNickName(), userProfile.getPassword(), userProfile.getEmail()));
    }

    public void AddLoginToSession(UserProfile userProfile, HttpSession httpSession){
        loginToSession.put(userProfile.getNickName(), httpSession);
    }

    public void DeleteSession(String userName){
        loginToSession.remove(userName);
    }

    public boolean IsUserNotExist(String nickName) throws DBException {
        return loginToProfile.getUser(nickName) == null;
    }

    public HttpSession GetSession(String login){
        return loginToSession.get(login);
    }

    public String GetUserName(String sessionId){
        return loginToSession
                .entrySet()
                .stream()
                .filter(s -> s.getValue().getId().equals(sessionId))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public boolean IsSessionExist(String nickName){
        return loginToSession.get(nickName) != null;
    }

    public UserProfile GetUserProfile(String nickname) throws DBException {
        return loginToProfile.getUser(nickname);
    }
}
