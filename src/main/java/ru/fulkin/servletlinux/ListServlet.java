package ru.fulkin.servletlinux;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ListServlet extends HttpServlet {
    private static List<String> listName = Arrays.asList(
            "Alex",
            "Steven",
            "Maria",
            "Vlad",
            "Alexsey",
            "name",
            "name",
            "name24",
            "name125",
            "name1351",
            "name3141"
            );


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("names", listName);
        req.getRequestDispatcher("/listname.jsp").forward(req, resp);
    }
}
