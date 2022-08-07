package com.github.RuSichPT.TestOrderMicroservice.servlets;

import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import com.github.RuSichPT.TestOrderMicroservice.services.Command;
import com.github.RuSichPT.TestOrderMicroservice.services.ConverterXML;
import com.github.RuSichPT.TestOrderMicroservice.services.OrderServiceImpl;
import com.github.RuSichPT.TestOrderMicroservice.services.ParserXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
        resp.setContentType("text/xml");
        String id = req.getParameter("id");

        if (id == null) {
            resp.sendError(SC_NOT_FOUND);
            return;
        }
        Order order = orderService.select(Integer.parseInt(id));

        if (order == null) {
            resp.sendError(SC_NOT_FOUND);
            return;
        }

        PrintWriter printWriter = resp.getWriter();

        printWriter.println(ConverterXML.convert(order));
        printWriter.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Document document = createDocumentFromReq(req);
            ParserXML parserXML = new ParserXML(document);

            if (parserXML.getCommand() != Command.CREATE)
            {
                resp.sendError(SC_BAD_REQUEST);
                throw new ServletException("wrong command, expected " + Command.CREATE);
            }
            Order order = parserXML.getOrder();
            orderService.insert(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String id = req.getParameter("id");

        if (id == null) {
            resp.sendError(SC_NOT_FOUND);
            return;
        }

        try {
            Document document = createDocumentFromReq(req);
            ParserXML parserXML = new ParserXML(document);

            if (parserXML.getCommand() != Command.UPDATE)
            {
                resp.sendError(SC_BAD_REQUEST);
                throw new ServletException("wrong command, expected " + Command.UPDATE);
            }
            Order order = parserXML.getOrder();
            orderService.update(Integer.parseInt(id), order);
        } catch (ResponseStatusException e)
        {
            resp.sendError(SC_NOT_FOUND);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String id = req.getParameter("id");

        if (id == null) {
            resp.sendError(SC_NOT_FOUND);
            return;
        }

        orderService.delete(Integer.parseInt(id));
    }

    private Document createDocumentFromReq(HttpServletRequest req) throws IOException, ParserConfigurationException, SAXException {
        BufferedReader reader = req.getReader();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(reader));
        reader.close();

        return doc;
    }
}
