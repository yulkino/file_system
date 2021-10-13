package com.example.file_system.servlets;

import com.example.file_system.accounts.AccountService;
import com.example.file_system.accounts.UserProfile;
import com.example.file_system.dbService.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("loginpage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        String pass = req.getParameter("pass");
        if(nickname.equals("") || pass.equals("")){
            req.setAttribute("error", "Make sure that you have filled required fields");
            req.getRequestDispatcher("loginpage.jsp").forward(req, resp);
            return;
        }
        UserProfile userProfile;
        try {
            userProfile = accountService.GetUserProfile(nickname);
            if(accountService.IsUserNotExist(nickname)){
                req.setAttribute("error", "User not found");
                req.getRequestDispatcher("loginpage.jsp").forward(req, resp);
                return;
            }

            if(!userProfile.getPassword().equals(pass)){
                req.setAttribute("error", "Make sure that you entered right password");
                req.getRequestDispatcher("loginpage.jsp").forward(req, resp);
                return;
            }

            accountService.AddLoginToSession(userProfile,req.getSession());
        } catch (DBException e) {
            e.printStackTrace();
        }

        String path = "http://localhost:8080/file_system_war_exploded/?path=D:/Users/" + nickname +"&nickname=" + nickname;
        resp.sendRedirect(path);
    }
}
