package com.example.file_system.servlets;

import com.example.file_system.models.Directory;

import javax.servlet.ServletConfig;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String param = req.getParameter("path");
        if (param == null)
        {
            param = new File(".").getAbsolutePath().replace("\\", "/").split("/")[0] + "/";
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

    }

}