package com.github.RuSichPT.TestOrderMicroservice.order;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    private List<OrderItem> orderItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerComment() {
        return customerComment;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void fillProperty(Node property)
    {
        String content = property.getTextContent();
        switch (property.getNodeName())
        {
            case "id" -> setId(Integer.parseInt(content));
            case "orderStatusId" -> setOrderStatusId(Integer.parseInt(content));
            case "customerName" -> setCustomerName(content);
            case "customerPhone" -> setCustomerPhone(content);
            case "customerComment" -> setCustomerComment(content);
            case "items" ->
            {
                NodeList items = property.getChildNodes();

                ArrayList<OrderItem> arrayList = new ArrayList<>();
                for (int c0 = 0; c0 < items.getLength(); c0++)
                {
                    Node item = items.item(c0);
                    if (item.getNodeType() != Node.TEXT_NODE)
                    {
                        NodeList properties = item.getChildNodes();
                        OrderItem orderItem = new OrderItem();
                        for (int c1 = 0; c1 < properties.getLength(); c1++)
                        {
                            Node propOrderItem = properties.item(c1);
                            if (propOrderItem.getNodeType() != Node.TEXT_NODE)
                            {
                                orderItem.fillProperty(propOrderItem);
                            }
                        }
                        arrayList.add(orderItem);
                    }
                }
                setOrderItems(arrayList);
            }
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatusId=" + orderStatusId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerComment='" + customerComment + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
