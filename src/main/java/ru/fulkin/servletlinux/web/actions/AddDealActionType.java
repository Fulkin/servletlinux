package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDealActionType implements ActionType {
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse resp, ClientService clientService) throws ServletException, IOException {

        req.setAttribute("clients", clientService.getAllClients());
        req.setAttribute("products", clientService.getProducts());
        req.getRequestDispatcher("/dealProductForm.jsp").forward(req, resp);

    }
}
