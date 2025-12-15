package com.hwc.poc.orderservice.application.contract;


import com.hwc.poc.orderservice.application.model.Order;

public interface OrderRepositoryContract {
    Order save(Order order);

    Order load(Integer orderId);
}
