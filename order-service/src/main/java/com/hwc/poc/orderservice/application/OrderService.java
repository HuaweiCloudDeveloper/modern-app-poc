package com.hwc.poc.orderservice.application;


import com.hwc.poc.orderservice.application.contract.OrderRepositoryContract;
import com.hwc.poc.orderservice.application.exception.OrderInvalidException;
import com.hwc.poc.orderservice.application.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


@Component
public class OrderService {

    public static final int TOW_HOURS_TIMEOUT_OFFSET = 2 * 60 * 60 * 1000;

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private OrderRepositoryContract repository;

//    @Autowired
//    private ShippingGatewayContract shippingGateway;
//
//    @Autowired
//    private InventoryGatewayContract inventoryGateway;
//

    public Order placeOrder(Order order) {

//        if (CollectionUtils.isEmpty(order.getItems())) {
//            throw new OrderInvalidException();
//        }

        repository.save(order);
//
//        lockInventory(order);


        return order;
    }


    public Order getOrderById(Integer orderId) {
        return null; //repository.load(orderId);
    }


    private void lockInventory(Order order) {
//        Map<Integer, Integer> productInventoryMap = Maps.newHashMap();
//        inventoryGateway.lock(order.getOid(), productInventoryMap);
    }


}
