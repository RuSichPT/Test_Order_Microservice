package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserXML
{
    private Order order;
    private Command command;

    public Order getOrder() {
        return order;
    }

    public Command getCommand() {
        return command;
    }

    public ParserXML(Document doc) throws RuntimeException
    {
        order = new Order();
        NodeList commandNodeList = doc.getElementsByTagName("command");

        try
        {
            command = Command.toCommand(commandNodeList.item(0).getTextContent());

            NodeList orderNodeList = doc.getElementsByTagName("order");

            for (int c0 = 0; c0 < orderNodeList.getLength(); c0++)
            {
                Node orderNode = orderNodeList.item(c0);
                if (orderNode.getNodeType() != Node.TEXT_NODE)
                {
                    NodeList properties = orderNode.getChildNodes();
                    for (int c1 = 0; c1 < properties.getLength(); c1++)
                    {
                        Node property = properties.item(c1);
                        if (property.getNodeType() != Node.TEXT_NODE)
                        {
                            order.fillProperty(property);
                        }
                    }
                }
            }
        } catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }


}
