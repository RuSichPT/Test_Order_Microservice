package com.github.RuSichPT.TestOrderMicroservice.filters;

import com.github.RuSichPT.TestOrderMicroservice.entities.Session;
import com.github.RuSichPT.TestOrderMicroservice.services.SessionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@WebFilter("/new")
public class OrderFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFilter.class);
    @Autowired
    SessionServiceImpl sessionServiceImpl;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.info("My filter start:");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String sessionId = request.getHeader("SESSION-ID");
        LOGGER.info("Request with session id=" + sessionId);

        if (sessionId == null)
        {
            sendError("Invalid session", response);
            LOGGER.info("My filter end!");
            return;
        }

        Session session = sessionServiceImpl.select(sessionId);
        if (session == null)
        {
            sendError("Unknown session", response);
            LOGGER.info("My filter end!");
            return;
        }

        LOGGER.info("Session id=" + sessionId+" found");
        if (session.checkTimeout())
        {
            sendError("Session expired", response);
            LOGGER.info("My filter end!");
            return;
        }

        LOGGER.info("My filter end!");

        //Разрешить request продвигаться дальше. (Перейти данный Filter).
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void sendError(String message, HttpServletResponse response) throws IOException {
        LOGGER.info(message);
        response.setStatus(SC_BAD_REQUEST);
        response.getWriter().write(message);
    }
}
