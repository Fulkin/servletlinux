package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductsActionType implements ActionType {
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse resp, ClientService clientService) throws ServletException, IOException {
        req.setAttribute("products", clientService.getAllProducts());
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
