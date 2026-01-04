package com.hwc.poc.inventoryservice.infra.dms;

import com.hwc.poc.inventoryservice.application.utils.InventoryConstants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class DmsProducerConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DmsProducerConfiguration.class);

    @Value("${DMS_HOST_AND_PORT:${hwc.poc.inventory.dms.bootstrap-servers}}")
    private String dmsConServers;

    @Bean
    Producer<String, String>  dmsProducer(){

        Properties properties = new Properties();

        properties.put("bootstrap.servers", dmsConServers);
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("buffer.memory", InventoryConstants.KEY_INV_DMS_ORDER_DEFAULT_BUFFER);
        properties.put("retries", InventoryConstants.KEY_INV_DMS_ORDER_DEFAULT_RETRIES);

        Producer<String, String> producer = new KafkaProducer<>(properties);
        logger.info("Create dmsProducer successfully");

        return producer;
    }
}
