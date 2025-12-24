package com.hwc.poc.inventoryservice.application;


import com.hwc.poc.inventoryservice.application.contract.InventoryDmsContract;
import com.hwc.poc.inventoryservice.application.contract.InventoryDynamoDbContract;
import com.hwc.poc.inventoryservice.application.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    public boolean inventoryExist = false;

    @Autowired
    private InventoryDynamoDbContract dynamoDbRepository;

    @Autowired
    private InventoryDmsContract dmsRepository;

    public Integer createInventory(Order order){

        checkInventoryTable();
        return dynamoDbRepository.createInventory(order);
    }

    public String queryInventory(Integer oid) {

        checkInventoryTable();
        return dynamoDbRepository.queryInventory(oid);
    }

    public void deleteInventory(Integer oid){

        checkInventoryTable();
        dynamoDbRepository.deleteInventory(oid);
    }

    public void notifyInventory(Order order){

        dmsRepository.notifyInventory(order);
    }

    private void checkInventoryTable(){

        //If the inventory table flag is true, return directly.
        if(inventoryExist) {
          return;
        }

        //If the inventory table exist, return directly.
        boolean inventoryTableStatus = dynamoDbRepository.queryInventoryTable();
        if(inventoryTableStatus){
            inventoryExist = true;
            return;
        }

        //Try to create inventory table
        inventoryTableStatus = dynamoDbRepository.createInventoryTable();
        if(inventoryTableStatus){
            inventoryExist = true;
        }
    }
}
