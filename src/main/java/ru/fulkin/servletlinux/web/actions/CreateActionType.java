package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateActionType implements ActionType {

    @Override
    public void doAction(HttpServletRequest req,
                         HttpServletResponse resp,
                         ClientService clientService) throws ServletException, IOException {
        Client client = new Client("", "", "", "");
        req.setAttribute("client", client);
        req.getRequestDispatcher("/clientForm.jsp").forward(req, resp);
    }
}
