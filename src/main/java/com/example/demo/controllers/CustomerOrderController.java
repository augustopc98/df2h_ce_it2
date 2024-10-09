package com.example.demo.controllers;

import com.example.demo.models.CustomerOrder;
import com.example.demo.models.CustomerOrderDto;
import com.example.demo.models.OrderItem;
import com.example.demo.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customerOrders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public CustomerOrder createCustomerOrder(@RequestBody CustomerOrderDto customerOrderDto) {
        return customerOrderService.createCustomerOrder(
                customerOrderDto.getCustomerEmail(),
                customerOrderDto.getCustomerAddress(),
                customerOrderDto.getItems());
    }

    @PostMapping("/{orderId}/items")
    public CustomerOrder addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        return customerOrderService.addOrderItem(orderId, item);
    }

    @DeleteMapping("/{orderId}/items")
    public CustomerOrder removeOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        return customerOrderService.removeOrderItem(orderId, item);
    }

    @GetMapping("/{orderId}/total")
    public BigDecimal getCustomerOrderTotal(@PathVariable Long orderId) {
        return customerOrderService.getCustomerOrderTotal(orderId);
    }

    @PostMapping("/{orderId}/sendForDelivery")
    public void sendCustomerOrderForDelivery(@PathVariable Long orderId) {
        customerOrderService.sendCustomerOrderForDelivery(orderId);
    }

    @PatchMapping("/{orderId}/deliveryStatus")
    public void updateCustomerOrderDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        customerOrderService.updateCustomerOrderDeliveryStatus(orderId, status);
    }

    @GetMapping("/{orderId}")
    public CustomerOrder getCustomerOrderDetails(@PathVariable Long orderId) {
        return customerOrderService.getCustomerOrderDetails(orderId);
    }
}
