package com.github.RuSichPT.TestOrderMicroservice.entities;

import org.w3c.dom.Node;

import java.util.Objects;

public class OrderItem {
    private int id;

    private int orderId;

    private String itemName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void fillProperty(Node property)
    {
        String content = property.getTextContent();

        switch (property.getNodeName())
        {
            case "id" -> setId(Integer.parseInt(content));
            case "orderId" -> setOrderId(Integer.parseInt(content));
            case "itemName" -> setItemName(content);
        }
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", itemName='" + itemName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id && orderId == orderItem.orderId && Objects.equals(itemName, orderItem.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, itemName);
    }
}
