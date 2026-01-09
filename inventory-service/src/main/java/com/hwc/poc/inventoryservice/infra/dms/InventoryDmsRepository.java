package com.hwc.poc.inventoryservice.infra.dms;

import com.amazonaws.services.dynamodbv2.model.*;
import com.hwc.poc.inventoryservice.application.contract.InventoryDmsContract;
import com.hwc.poc.inventoryservice.application.model.Order;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.Properties;

@Component
public class InventoryDmsRepository implements InventoryDmsContract {

    private static final Logger logger = LoggerFactory.getLogger(InventoryDmsRepository.class);

    @Value("${DMS_TOPIC:${hwc.poc.inventory.dms.topic}}")
    private String dmsTopic;

    @Value("${DMS_PARTITION:${hwc.poc.inventory.dms.partition}}")
    private String dmsPartition;

    @Autowired
    Producer<String, String> dmsProducer;

    @Autowired
    private ObjectMapper objectMapper;

     public boolean notifyInventory(Order order) {

         String oid = String.valueOf(order.getOid());
         String orderDetails = objectMapper.writeValueAsString(order);
         ProducerRecord<String, String> record = new ProducerRecord<>(dmsTopic, Integer.parseInt(dmsPartition), oid, orderDetails);

         try {
             dmsProducer.send(record);
         } catch (RuntimeException e) {
             logger.error("Producer send message to DMS failed.");
             return false;
         }
         return true;
     }
}
