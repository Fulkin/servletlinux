package ru.fulkin.servletlinux.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class WebUtil {
    private WebUtil() {
    }

    public static int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
