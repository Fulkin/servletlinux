package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.model.Deal;
import ru.fulkin.servletlinux.model.Product;
import ru.fulkin.servletlinux.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

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
        int idClient = Integer.valueOf(req.getParameter("id"));
        LocalDateTime date = LocalDateTime.parse(req.getParameter("date"));
        remnant = remnant - amount;

        Deal deal = new Deal(date, amount, idProduct, idClient, remnant);

        clientService.saveDeal(deal);
        req.setAttribute("id", idClient);
        Action.getAction("deallist").getActionName().doAction(req, resp, clientService);
    }
}
