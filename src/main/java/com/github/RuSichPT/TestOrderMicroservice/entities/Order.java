package com.github.RuSichPT.TestOrderMicroservice.entities;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement // For ConverterXML
@XmlAccessorType(XmlAccessType.FIELD) // or @XmlType(propOrder = {"id", "orderStatusId", "patientId", "customerComment", "orderItems"})
public class Order {
    private int id;
    private int orderStatusId;

    private int patientId;
    private String customerComment;
    private List<OrderItem> orderItems;

    private Patient patient;

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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void fillProperty(Node property)
    {
        String content = property.getTextContent();
        switch (property.getNodeName())
        {
            case "id" -> setId(Integer.parseInt(content));
            case "orderStatusId" -> setOrderStatusId(Integer.parseInt(content));
            case "patientId" -> setPatientId(Integer.parseInt(content));
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
                ", patientId=" + patientId +
                ", customerComment='" + customerComment + '\'' +
                ", orderItems=" + orderItems +
                ", patient=" + patient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && orderStatusId == order.orderStatusId && patientId == order.patientId && Objects.equals(customerComment, order.customerComment) && Objects.equals(orderItems, order.orderItems) && Objects.equals(patient, order.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatusId, patientId, customerComment, orderItems, patient);
    }
}
