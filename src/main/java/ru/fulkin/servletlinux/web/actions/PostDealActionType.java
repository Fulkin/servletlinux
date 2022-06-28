package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.model.client.Deal;
import ru.fulkin.servletlinux.model.client.Product;
import ru.fulkin.servletlinux.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDealActionType implements ActionType {

    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse resp, ClientService clientService) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int idProduct = Integer.valueOf(req.getParameter("product_item"));
        int amount = Integer.valueOf(req.getParameter("amount"));
        Product product = clientService.getProduct(idProduct);
        int remnant = product.getRemnant();
        if (remnant < amount) {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Amount > product remnant');");
            out.println("window.location = '/servlet/clients';");
            out.println("</script>");
            return;
        }
        LocalDateTime date = LocalDateTime.parse(req.getParameter("date"));
        remnant = remnant - amount;
        product.setRemnant(remnant);
        String[] clientsItems = req.getParameterValues("clients_item");
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < clientsItems.length; i++) {
            clients.add(clientService.get(Integer.valueOf(clientsItems[i])));
        }

        Deal deal = new Deal(date, amount, product, clients);

        clientService.saveDeal(deal);
        resp.sendRedirect("clients");
    }
}
