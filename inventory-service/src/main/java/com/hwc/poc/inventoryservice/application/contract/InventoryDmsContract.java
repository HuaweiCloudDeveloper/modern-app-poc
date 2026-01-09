package com.hwc.poc.inventoryservice.application.contract;


import com.hwc.poc.inventoryservice.application.model.Order;

public interface InventoryDmsContract {

    boolean notifyInventory(Order order);
}
