package com.hwc.poc.inventoryservice.infra.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.hwc.poc.inventoryservice.application.contract.InventoryDynamoDbContract;
import com.hwc.poc.inventoryservice.application.model.Order;
import com.hwc.poc.inventoryservice.application.utils.InventoryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InventoryDynamoDbRepository implements InventoryDynamoDbContract {

    private static final Logger logger = LoggerFactory.getLogger(InventoryDynamoDbRepository.class);

    @Autowired
    private AmazonDynamoDB dynamoDBClient;

    @Autowired
    private InventoryDynamoDbRepository(AmazonDynamoDB dynamoDBClient){
        this.dynamoDBClient = dynamoDBClient;
    };

    public Integer createInventory(Order order){

        // Get Order details
        ObjectMapper objectMapper = new ObjectMapper();
        String orderDetails = objectMapper.writeValueAsString(order);

        // Define the item info
        HashMap<String, AttributeValue> putItemValues = new HashMap<String, AttributeValue>();
        putItemValues.put(InventoryConstants.KEY_INV_TABLE_FIELD_OID
                , new AttributeValue(String.valueOf(order.getOid())));
        putItemValues.put(InventoryConstants.KEY_INV_TABLE_FIELD_DETAILS
                , new AttributeValue(String.valueOf(orderDetails)));


        PutItemResult putItemRequest = dynamoDBClient.putItem(InventoryConstants.KEY_INV_TABLE_NAME, putItemValues);

        if(putItemRequest == null){
            logger.error("Put Item {} to Table {} failed.", order.getOid(), InventoryConstants.KEY_INV_TABLE_NAME);
            return InventoryConstants.KEY_INVALID_ORDER_ID;
        }

        return order.getOid();
    }

    public String queryInventory(Integer oid){

        // Define the item info by key
        HashMap<String, AttributeValue> getItemValues = new HashMap<String, AttributeValue>();
        getItemValues.put(InventoryConstants.KEY_INV_TABLE_FIELD_OID, new AttributeValue(String.valueOf(oid)));

        // Define GetItemRequest
        GetItemRequest getItemRequest = new GetItemRequest()
                .withTableName(InventoryConstants.KEY_INV_TABLE_NAME)
                .withKey(getItemValues);

        // Get the item info
        Map<String, AttributeValue> returnItem = dynamoDBClient.getItem(getItemRequest).getItem();

        if(returnItem == null || returnItem.isEmpty()){
            return null;
        }

        if(!returnItem.containsKey(InventoryConstants.KEY_INV_TABLE_FIELD_DETAILS)){
            return null;
        }

        return returnItem.get(InventoryConstants.KEY_INV_TABLE_FIELD_DETAILS).getS();
    }

    public void deleteInventory(Integer oid){

        // Define the item info by key
        HashMap<String, AttributeValue> deleteItemValues = new HashMap<String, AttributeValue>();
        deleteItemValues.put(InventoryConstants.KEY_INV_TABLE_FIELD_OID, new AttributeValue(String.valueOf(oid)));

        // Delete item
        dynamoDBClient.deleteItem(InventoryConstants.KEY_INV_TABLE_NAME, deleteItemValues);
    }

    public boolean queryInventoryTable(){

        boolean listTabResult = false;

        // Define list table Req
        ListTablesRequest listTablesRequest = new ListTablesRequest()
                .withLimit(InventoryConstants.KEY_INV_TABLE_LIMIT)
                .withExclusiveStartTableName(InventoryConstants.KEY_INV_TABLE_NAME);

        ListTablesResult listTablesResult = dynamoDBClient.listTables(listTablesRequest)
                .withTableNames(InventoryConstants.KEY_INV_TABLE_NAME);

        if(listTablesResult != null && !listTablesResult.getTableNames().isEmpty()){

            logger.info("The table {} exists.",listTablesResult.getTableNames().toString());
            listTabResult = true;
        }

        return listTabResult;
    }

    public boolean createInventoryTable(){

        boolean createTabResult = true;

        // Define attributes
        List<AttributeDefinition> attributeDefinitionList = new ArrayList<>();
        attributeDefinitionList.add(
                new AttributeDefinition(InventoryConstants.KEY_INV_TABLE_FIELD_OID, ScalarAttributeType.S));

        // Define schemas
        List<KeySchemaElement> keySchema = new ArrayList<>();
        keySchema.add(new KeySchemaElement(InventoryConstants.KEY_INV_TABLE_FIELD_OID, KeyType.HASH));

        // Define throughput
        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput()
                .withReadCapacityUnits(5000L)
                .withWriteCapacityUnits(5000L);

        // Define create table Req
        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName(InventoryConstants.KEY_INV_TABLE_NAME)
                .withAttributeDefinitions(attributeDefinitionList)
                .withKeySchema(keySchema)
                .withProvisionedThroughput(provisionedThroughput);

        // Create table
        try {
            dynamoDBClient.createTable(createTableRequest);
        } catch (ResourceInUseException e) {
            logger.error("Create inventory table {} failed. ", InventoryConstants.KEY_INV_TABLE_NAME, e);
            createTabResult = false;
        }

        return createTabResult;
    }

}
