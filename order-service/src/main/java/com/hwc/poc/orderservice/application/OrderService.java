package com.hwc.poc.orderservice.application;


import com.hwc.poc.orderservice.application.contract.InventoryGatewayContract;
import com.hwc.poc.orderservice.application.contract.OrderRepositoryContract;
import com.hwc.poc.orderservice.application.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderService {

    @Autowired
    private OrderRepositoryContract repository;

    @Autowired
    private InventoryGatewayContract inventoryGateway;

    public Order placeOrder(Order order) {
        repository.save(order);
        return order;
    }


    public Order getOrderById(Integer orderId) {
        return repository.load(orderId);
    }


    public String lockInventory(Order order) {
        return inventoryGateway.lock(order);
    }


    public String queryInventory(Integer oid) {
        return inventoryGateway.queryInventory(oid);
    }

    public String notifyInventory(Order order) {
        return inventoryGateway.notifyInventory(order);
    }
}
