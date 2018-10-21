package com.damon.media.starter.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;

/***
 * @author Damon S.
 */
@WebServlet(name = "MediaServlet", urlPatterns = "/")
public class MediaServlet extends HttpServlet {

    private void ddd() {
        this.getServletConfig().getInitParameter("");
        Cookie cookie = new Cookie("Id", "1234567");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30);
    }

}
