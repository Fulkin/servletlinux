package ru.fulkin.servletlinux.web;

import ru.fulkin.servletlinux.model.Client;
import ru.fulkin.servletlinux.service.ClientService;
import ru.fulkin.servletlinux.web.actions.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientServlet extends HttpServlet {
    private ClientService clientService = new ClientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameAction = req.getParameter("action");
        nameAction = nameAction == null ? "all" : nameAction;
        Action.getAction(nameAction).getActionName().doAction(req, resp, clientService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameAction = req.getParameter("action");
        Action.getAction(nameAction).getActionName().doAction(req, resp, clientService);

    }
}
