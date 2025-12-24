package com.hwc.poc.inventoryservice.application.contract;


import com.hwc.poc.inventoryservice.application.model.Order;

public interface InventoryDmsContract {

    void notifyInventory(Order order);
}
