package com.example.demo.services;

import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;
import com.example.demo.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public CustomerOrder createCustomerOrder(String customerEmail, String customerAddress, List<OrderItem> items) {
        CustomerOrder customerOrder = new CustomerOrder(customerEmail, customerAddress, new java.util.Date());
        items.forEach(customerOrder::addOrderItem);
        return customerOrderRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder addOrderItem(Long orderId, OrderItem item) {
        Optional<CustomerOrder> orderOptional = customerOrderRepository.findById(orderId);
        if(orderOptional.isPresent()) {
            CustomerOrder customerOrder = orderOptional.get();
            customerOrder.addOrderItem(item);
            return customerOrderRepository.save(customerOrder);
        }
        return null;
    }

    @Override
    public CustomerOrder removeOrderItem(Long orderId, OrderItem item) {
        Optional<CustomerOrder> orderOptional = customerOrderRepository.findById(orderId);
        if(orderOptional.isPresent()) {
            CustomerOrder customerOrder = orderOptional.get();
            customerOrder.removeOrderItem(item);
            return customerOrderRepository.save(customerOrder);
        }
        return null;
    }

    @Override
    public BigDecimal getCustomerOrderTotal(Long orderId) {
        Optional<CustomerOrder> orderOptional = customerOrderRepository.findById(orderId);
        return orderOptional.map(CustomerOrder::calculateTotal).orElse(BigDecimal.ZERO);
    }

    @Override
    public void sendCustomerOrderForDelivery(Long orderId) {
        Optional<CustomerOrder> orderOptional = customerOrderRepository.findById(orderId);
        orderOptional.ifPresent(order -> {
            order.sendForDelivery();
            customerOrderRepository.save(order);
        });
    }

    @Override
    public void updateCustomerOrderDeliveryStatus(Long orderId, String status) {
        Optional<CustomerOrder> orderOptional = customerOrderRepository.findById(orderId);
        orderOptional.ifPresent(order -> {
            order.updateDeliveryStatus(status);
            customerOrderRepository.save(order);
        });
    }

    @Override
    public CustomerOrder getCustomerOrderDetails(Long orderId) {
        return customerOrderRepository.findById(orderId).orElse(null);
    }
}
