package com.github.RuSichPT.TestOrderMicroservice.order;

import java.util.List;

public class Order {
    private long id;
    private int order_status_id;
    private String customer_name;
    private String customer_phone;
    private String customer_comment;

    private List<OrderItem> orderItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderStatusId() {
        return order_status_id;
    }

    public void setOrderStatusId(int order_status_id) {
        this.order_status_id = order_status_id;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public void setCustomerName(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomerPhone() {
        return customer_phone;
    }

    public void setCustomerPhone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomerComment() {
        return customer_comment;
    }

    public void setCustomerComment(String customer_comment) {
        this.customer_comment = customer_comment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
