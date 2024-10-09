package com.example.demo.services;

import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder createCustomerOrder(String customerEmail, String customerAddress, List<OrderItem> items);
    CustomerOrder addOrderItem(Long orderId, OrderItem item);
    CustomerOrder removeOrderItem(Long orderId, OrderItem item);
    BigDecimal getCustomerOrderTotal(Long orderId);
    void sendCustomerOrderForDelivery(Long orderId);
    void updateCustomerOrderDeliveryStatus(Long orderId, String status);
    CustomerOrder getCustomerOrderDetails(Long orderId);
}
