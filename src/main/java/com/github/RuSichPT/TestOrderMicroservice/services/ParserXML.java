//package com.github.RuSichPT.TestOrderMicroservice.services;
//
//import com.github.RuSichPT.TestOrderMicroservice.order.Order;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;
//
//public class ParserXML
//{
//    private Order order;
//    private Command command;
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public ParserXML(String path)
//    {
//        try {
//            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc = db.parse(new File(path));
//
//            NodeList orderNodeList = doc.getElementsByTagName("order");
//
//            for (int i = 0; i < orderNodeList.getLength(); i++)
//            {
//                Node orderNode = orderNodeList.item(i);
//                orderNode.
//            }
//
//            Node root = doc.getDocumentElement();
//            NodeList messages = root.getChildNodes();
//
//            for (int i = 0; i < messages.getLength(); i++)
//            {
//                Node message = messages.item(i);
//                if (message.getNodeType() != Node.TEXT_NODE)
//                {
//                    NodeList commands = message.getChildNodes();
//                    for (int j = 0; j < properties.getLength(); j++)
//                    {
//                        Node property = properties.item(j);
//                        if (property.getNodeType() != Node.TEXT_NODE)
//                        {
//                            patient.fillProperty(property);
//                        }
//                    }
//                    patients.add(patient);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
