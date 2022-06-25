package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.service.ClientService;
import ru.fulkin.servletlinux.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAction implements ActionType {
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse resp, ClientService clientService) throws ServletException, IOException {
        int id = WebUtil.getId(req);
        clientService.delete(id);
        resp.sendRedirect("clients");
    }
}
