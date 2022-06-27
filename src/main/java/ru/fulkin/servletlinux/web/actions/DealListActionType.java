package ru.fulkin.servletlinux.web.actions;

import org.slf4j.Logger;
import ru.fulkin.servletlinux.model.Client;
import ru.fulkin.servletlinux.model.Deal;
import ru.fulkin.servletlinux.service.ClientService;
import ru.fulkin.servletlinux.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

public class DealListActionType implements ActionType {
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse resp, ClientService clientService) throws ServletException, IOException {
        Client client = clientService.get(WebUtil.getId(req));
        req.setAttribute("client", client);

        Collection<Deal> dealList = clientService.getDealList(client);
        req.setAttribute("dealsToList", dealList);

        req.getRequestDispatcher("/dealList.jsp").forward(req, resp);
    }
}
