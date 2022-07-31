package com.github.RuSichPT.TestOrderMicroservice.servlets;

import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import com.github.RuSichPT.TestOrderMicroservice.services.Command;
import com.github.RuSichPT.TestOrderMicroservice.services.OrderServiceImpl;
import com.github.RuSichPT.TestOrderMicroservice.services.ParserXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/new")
public class OrderServlet extends HttpServlet {

    @Autowired
    private OrderServiceImpl orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/html");
        String id = req.getParameter("id");

        if (id == null) {
            resp.sendError(SC_NOT_FOUND);
            return;
        }
        Order order = orderService.select(Integer.parseInt(id));
        PrintWriter printWriter = resp.getWriter();

        try
        {
            printWriter.println(order.toString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            printWriter.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws RuntimeException, ServletException {

        resp.setContentType("text/html");
        try {
            BufferedReader reader = req.getReader();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(reader));

            ParserXML parserXML = new ParserXML(document);
            if (parserXML.getCommand() != Command.CREATE)
            {
                resp.sendError(SC_BAD_REQUEST);
                throw new ServletException("wrong command, expected CREATE");
            }

            orderService.insert(parserXML.getOrder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
