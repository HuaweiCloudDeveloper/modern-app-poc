package com.hwc.poc.inventoryservice.application.contract;


import com.hwc.poc.inventoryservice.application.model.Order;

public interface InventoryDynamoDbContract {

    Integer createInventory(Order order);

    String queryInventory(Integer oid);

    void deleteInventory(Integer oid);

    boolean queryInventoryTable();

    boolean createInventoryTable();
}
