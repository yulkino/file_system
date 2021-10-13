package com.example.file_system.servlets;

import com.example.file_system.accounts.AccountService;
import com.example.file_system.accounts.UserProfile;
import com.example.file_system.dbService.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("registrationpage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        if(nickname.equals("") || req.getParameter("pass").equals("")
                || req.getParameter("email").equals("")){
            req.setAttribute("error", "Make sure that you have filled required fields");
            req.getRequestDispatcher("registrationpage.jsp").forward(req, resp);
            return;
        }
        try {
            if(accountService.IsUserNotExist(nickname)){
                UserProfile up = new UserProfile(nickname, req.getParameter("pass"), req.getParameter("email"));
                accountService.AddLoginToProfile(up);
                Path path = Paths.get("D:/Users/"+ nickname);
                if(Files.notExists(path)){
                    File homeDir = new File(path.toString());
                    homeDir.mkdir();
                }
                accountService.AddLoginToSession(up, req.getSession());
                String pathURL = "http://localhost:8080/file_system_war_exploded/?path=D:/Users/" + nickname;
                resp.sendRedirect(pathURL);
            }else{
                req.setAttribute("error", "User already exist");
                req.getRequestDispatcher("registrationpage.jsp").forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
