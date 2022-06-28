package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostClientActionType implements ActionType {
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse resp, ClientService clientService) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Client client = new Client(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                req.getParameter("city"),
                req.getParameter("phone"));

        clientService.save(client);
        resp.sendRedirect("clients");
    }
}
