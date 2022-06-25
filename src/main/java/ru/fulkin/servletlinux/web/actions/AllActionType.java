package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllActionType implements ActionType {
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse resp, ClientService clientService) throws ServletException, IOException {
        req.setAttribute("clients", clientService.getAllPerson());
        req.getRequestDispatcher("/clients.jsp").forward(req, resp);
    }
}
