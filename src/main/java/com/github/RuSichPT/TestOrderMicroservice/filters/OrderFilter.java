package com.github.RuSichPT.TestOrderMicroservice.filters;

import com.github.RuSichPT.TestOrderMicroservice.entities.Session;
import com.github.RuSichPT.TestOrderMicroservice.services.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@WebFilter
public class OrderFilter implements Filter {

    @Autowired
    SessionServiceImpl sessionServiceImpl;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String sessionId = request.getHeader("SESSION-ID");

        if (sessionId == null)
        {
            sendError("Invalid session", response);
            return;
        }

        Session session = sessionServiceImpl.select(sessionId);

        if (session == null)
        {
            sendError("Unknown session", response);
            return;
        }

        if (session.checkTimeout())
        {
            sendError("Session expired", response);
            return;
        }
        //Разрешить request продвигаться дальше. (Перейти данный Filter).
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void sendError(String message, HttpServletResponse response) throws IOException {
        System.out.println(message);
        response.setStatus(SC_BAD_REQUEST);
        response.getWriter().write(message);
    }
}
