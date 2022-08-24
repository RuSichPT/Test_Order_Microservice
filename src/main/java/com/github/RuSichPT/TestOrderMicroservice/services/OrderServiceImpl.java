package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.entities.Patient;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderItemMapper;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderMapper;
import com.github.RuSichPT.TestOrderMicroservice.entities.Order;
import com.github.RuSichPT.TestOrderMicroservice.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String root = "http://localhost:8081/patient/";

    @Autowired
    public RestTemplate restTemplate;
    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public OrderItemMapper orderItemMapper;

    @Override
    public Order insert(Order order)
    {
        Order resultOrder = null;
        Patient newPatient = order.getPatient();
        String url = root + "?firstName={v1}&midName={v2}&lastName={v3}&birthday={v4}";
        try
        {
            Patient oldPatient = restTemplate.getForObject(url, Patient.class, newPatient.getFirstName(), newPatient.getMidName(), newPatient.getLastName(), newPatient.getBirthday());
            System.out.println("old patient");
            resultOrder = insertOrder(order, oldPatient);
        }
        catch (HttpClientErrorException e)
        {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
            {
                try
                {
                    newPatient = restTemplate.postForObject(root, newPatient, Patient.class);
                    System.out.println("new patient");
                    resultOrder = insertOrder(order, newPatient);
                }
                catch (RestClientException exception)
                {
                    exception.printStackTrace();
                }
            }
            else
            {
                e.printStackTrace();
            }
        }

        return resultOrder;
    }

    @Override
    public Order select(int id)
    {
        Order order = orderMapper.selectOrder(id);

        if (order == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        try
        {
            String url = root + order.getPatientId();
            Patient patient = restTemplate.getForObject(url, Patient.class);
            System.out.println(patient.toString());
            order.setPatient(patient);
            return order;
        } catch (RestClientException e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Order update(int id, Order newOrder)
    {
        Order oldOrder = select(id);
        newOrder.setId(id);
        newOrder.setPatientId(oldOrder.getPatientId());

        String url = root + oldOrder.getPatientId();

        if (newOrder.equals(oldOrder))
        {
            return oldOrder;
        }

        try
        {
            orderMapper.updateOrder(newOrder);
            restTemplate.put(url, newOrder.getPatient(), Patient.class);
            updateOrderItems(oldOrder, newOrder);
            return select(id);
        }
        catch (RestClientException e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public void delete(int id)
    {
        orderMapper.deleteOrder(id);
    }

    private OrderItem isExistingId(List<OrderItem> orderItemList, int id)
    {
        for (OrderItem item: orderItemList)
        {
            if (item.getId() == id)
            {
                return item;
            }
        }

        return null;
    }

    private Order insertOrder(Order order, Patient patient)
    {
        System.out.println(patient.toString());
        order.setPatientId(patient.getId());
        orderMapper.insertOrder(order);
        Order resultOrder = orderMapper.selectCurrentOrder();
        resultOrder.setPatient(patient);

        return resultOrder;
    }

    private void updateOrderItems(Order oldOrder, Order newOrder)
    {
        ArrayList<OrderItem> oldOI = (ArrayList<OrderItem>) oldOrder.getOrderItems();
        ArrayList<OrderItem> newOI = (ArrayList<OrderItem>) newOrder.getOrderItems();
        ArrayList<OrderItem> copyNewOI = new ArrayList<>(newOI);
        ArrayList<OrderItem> copyOldOI= new ArrayList<>(oldOI);
        ArrayList<OrderItem> updatedOI = new ArrayList<>();

        for (OrderItem newOderItem: copyNewOI)
        {
            OrderItem oldOrderItem = isExistingId(copyOldOI, newOderItem.getId());
            if (oldOrderItem != null)
            {
                updatedOI.add(newOderItem);
                oldOI.remove(oldOrderItem);
                newOI.remove(newOderItem);
            }
        }
        if (!updatedOI.isEmpty())
        {
            orderItemMapper.updateOrderItems(updatedOI);
        }
        if (!newOI.isEmpty())
        {
            orderItemMapper.insertOrderItemsByOrderId(newOrder.getId(), newOI);
        }
        if (!oldOI.isEmpty())
        {
            orderItemMapper.deleteOrderItems(oldOI);
        }
    }
}
