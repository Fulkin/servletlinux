package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.model.Client;
import ru.fulkin.servletlinux.service.ClientService;
import ru.fulkin.servletlinux.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DealListActionType implements ActionType {
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse resp, ClientService clientService) throws ServletException, IOException {
        Client client = clientService.get(WebUtil.getId(req));
        req.setAttribute("client", client);
        req.setAttribute("dealsToList", clientService.getDealList(client));
        req.getRequestDispatcher("/dealList.jsp").forward(req, resp);
    }
}
