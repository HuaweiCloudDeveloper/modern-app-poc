package com.hwc.poc.orderservice.application.contract;

import com.hwc.poc.orderservice.application.model.Order;

public interface InventoryGatewayContract {
    String lock(Order order);

    String unlock(String lockId);

    String queryInventory(Integer oid);

    String notifyInventory(Order order);
}
