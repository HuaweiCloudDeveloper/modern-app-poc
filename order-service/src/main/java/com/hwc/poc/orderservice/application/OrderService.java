package com.hwc.poc.orderservice.application;


import com.hwc.poc.orderservice.application.contract.OrderRepositoryContract;
import com.hwc.poc.orderservice.application.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderService {

    @Autowired
    private OrderRepositoryContract repository;

//    @Autowired
//    private InventoryGatewayContract inventoryGateway;

    public Order placeOrder(Order order) {
        repository.save(order);
        return order;
    }


    public Order getOrderById(Integer orderId) {
        return repository.load(orderId);
    }


    private void lockInventory(Order order) {
//        Map<Integer, Integer> productInventoryMap = Maps.newHashMap();
//        inventoryGateway.lock(order.getOid(), productInventoryMap);
    }


}
