package ru.fulkin.servletlinux.web.actions;

import ru.fulkin.servletlinux.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionType {
    void doAction(HttpServletRequest req,
                  HttpServletResponse resp,
                  ClientService clientService) throws ServletException, IOException;
}
