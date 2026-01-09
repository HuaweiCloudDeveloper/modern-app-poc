package com.hwc.poc.inventoryservice;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.*;
import com.hwc.poc.inventoryservice.application.utils.InventoryConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.*;

public class DemoTest {

    public static AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
            new BasicAWSCredentials("rwuser", "Huawei@#123")
    );

    public static void main(String[] args){

        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://172.16.0.248:80", "region_default"))
                .withCredentials(credentialsProvider)
                .build();

        //ListTables
        ListTablesRequest listTablesRequest = new ListTablesRequest()
                .withLimit(10);
        ListTablesResult res = dynamoDBClient.listTables(listTablesRequest);
        System.out.println("[1]-amazonDynamoDB.listTables:   " + res.getTableNames().toString());

        ListTablesResult resExt1 = dynamoDBClient.listTables();
        System.out.println("[1.1]-amazonDynamoDB.listTables Ext:   " + resExt1.getTableNames().toString());

        boolean inventoryTableIsExist = resExt1.getTableNames().contains(InventoryConstants.KEY_INV_TABLE_NAME);
        System.out.println("[1.2]-amazonDynamoDB.listTables Ext:  inventoryTableIsExist = " + inventoryTableIsExist);


        //deleteTable
        try {
            dynamoDBClient.deleteTable(InventoryConstants.KEY_INV_TABLE_NAME);
            res = dynamoDBClient.listTables(listTablesRequest);
            System.out.println("[2]-amazonDynamoDB.deleteTable:   " + res.getTableNames().toString());
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
//
//        //createTable
//        List<AttributeDefinition> attributeDefinitionList = new ArrayList<>();
//        attributeDefinitionList.add(new AttributeDefinition("oid", ScalarAttributeType.S));
//
//        List<KeySchemaElement> keySchema = new ArrayList<>();
//        keySchema.add(new KeySchemaElement("oid", KeyType.HASH));
//
//        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput()
//                .withReadCapacityUnits(5000L)
//                .withWriteCapacityUnits(5000L);
//
//        CreateTableRequest createTableRequest = new CreateTableRequest()
//                .withTableName(InventoryConstants.KEY_INV_TABLE_NAME)
//                .withAttributeDefinitions(attributeDefinitionList)
//                .withKeySchema(keySchema)
//                .withProvisionedThroughput(provisionedThroughput);
//        try {
//            dynamoDBClient.createTable(createTableRequest);
//        } catch (ResourceInUseException e) {
//            e.printStackTrace();
//        }
//
//        res = dynamoDBClient.listTables(listTablesRequest);
//        System.out.println("[3]-amazonDynamoDB.createTable:   " + res.getTableNames().toString());
//
//        //putItem
//        HashMap<String, AttributeValue> putItemValues = new HashMap<String, AttributeValue>();
//        putItemValues.put("oid", new AttributeValue("1"));
//        putItemValues.put("order_details", new AttributeValue(String.valueOf("{}")));
//        dynamoDBClient.putItem(InventoryConstants.KEY_INV_TABLE_NAME, putItemValues);
//        HashMap<String, AttributeValue> getItemValues = new HashMap<String, AttributeValue>();
//        getItemValues.put("oid", new AttributeValue("1"));
//        GetItemRequest getItemRequest = new GetItemRequest().withTableName(InventoryConstants.KEY_INV_TABLE_NAME)
//                .withKey(getItemValues);
//        Map<String, AttributeValue> returnItem = dynamoDBClient.getItem(getItemRequest).getItem();
//        System.out.println("[3.1]-amazonDynamoDB.putItem:   " +
//                "[oid: " + returnItem.get("oid") + "]" +
//                "[order_details: " + returnItem.get("order_details") + "]");
//
//        //deleteItem
//        HashMap<String, AttributeValue> deleteItemValues = new HashMap<String, AttributeValue>();
//        deleteItemValues.put("oid", new AttributeValue("1"));
//        dynamoDBClient.deleteItem(InventoryConstants.KEY_INV_TABLE_NAME, deleteItemValues);
//        getItemRequest = new GetItemRequest().withTableName(InventoryConstants.KEY_INV_TABLE_NAME)
//                .withKey(deleteItemValues);
//        returnItem = dynamoDBClient.getItem(getItemRequest).getItem();
//        if(returnItem != null){
//            System.out.println("[3.2]-amazonDynamoDB.putItem:   [oid:" + returnItem.get("oid") + "]");
//        }else{
//            System.out.println("[3.3]-amazonDynamoDB.putItem:   [oid: null]");
//        }
//
//        Properties producerProperties = new Properties();
//
//        producerProperties.put("bootstrap.servers", "172.16.0.46:9092,172.16.0.191:9092,172.16.0.138:9092");
//        producerProperties.put("acks", "all");
//        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        producerProperties.put("buffer.memory", 33554432);
//        producerProperties.put("retries", 0);
//
//        Producer<String, String> producer = new KafkaProducer<>(producerProperties);
//
//        for (int i = 0; i < 10; i++) {
//            String key = "key-" + i;
//            String message = "Message " + i;
//            ProducerRecord<String, String> record = new ProducerRecord<>("topic-inventory", key, message);
//            producer.send(record);
//            System.out.println("Send Key[" + key + "]message: [" + message + "]");
//        }


//        Properties consumerProperties = new Properties();
//        consumerProperties.put("bootstrap.servers", "172.16.0.46:9092,172.16.0.191:9092,172.16.0.138:9092");
//        consumerProperties.put("group.id", "test-group");
//        consumerProperties.put("enable.auto.commit", "true");
//        consumerProperties.put("auto.commit.interval.ms", "1000");
//        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//
//        KafkaConsumer<String, String> dmsConsumer = new KafkaConsumer<>(consumerProperties);
//        dmsConsumer.subscribe(Arrays.asList("topic-inventory"));
//        while(true){
//
//            ConsumerRecords<String, String> records = dmsConsumer.poll(Duration.ofMillis(1000));
//            for(ConsumerRecord<String, String> record : records){
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(),record.key(),record.value());
//            }
//        }
    }
}
