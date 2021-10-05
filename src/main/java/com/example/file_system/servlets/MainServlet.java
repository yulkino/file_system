package com.example.file_system.servlets;

import com.example.file_system.accounts.AccountService;
import com.example.file_system.models.Directory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        String param = req.getParameter("path");
        if(accountService.GetUserLogin(sessionId) != null && param != null) {
            if(!param.contains("D:/Users/" + accountService.GetUserLogin(sessionId))){
                param = "D:/Users/" + accountService.GetUserLogin(sessionId);
            }
            File file = new File(param);
            if (file.isFile()) {
                try (OutputStream out = resp.getOutputStream()) {
                    resp.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
                    resp.setContentType("application/octet-stream");
                    byte[] bytes = Files.readAllBytes(file.toPath());
                    resp.addHeader("Content-Length", "" + bytes.length);
                    out.write(bytes);
                }
                return;
            }
            req.setAttribute("page", new Directory(file));

            req.getRequestDispatcher("mypage.jsp").forward(req, resp);
        }else{
            String path = "http://localhost:8080/file_system_war_exploded/login";
            resp.sendRedirect(path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        //accountService.DeleteSession(req.getSession());
    }

}