package com.example.file_system.servlets;

import com.example.file_system.accounts.AccountService;
import com.example.file_system.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class SignOutServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        accountService.DeleteSession(sessionId);
        String pathURL = "http://localhost:8080/file_system_war_exploded/login";
        resp.sendRedirect(pathURL);
    }
}
