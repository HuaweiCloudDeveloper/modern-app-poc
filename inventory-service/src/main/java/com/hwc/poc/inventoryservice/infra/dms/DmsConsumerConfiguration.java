package com.hwc.poc.inventoryservice.infra.dms;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class DmsConsumerConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DmsConsumerConfiguration.class);

    @Value("${DMS_HOST_AND_PORT:${hwc.poc.inventory.dms.bootstrap-servers}}")
    private String dmsConServers;

    @Value("${DMS_CONSUMER_GROUP_ID:${hwc.poc.inventory.dms.dms-consumer.group-id}}")
    private String dmsConsumerGroupId;

    @Value("${DMS_CONSUMER_ENABLE_AUTO_COMMIT:${hwc.poc.inventory.dms.dms-consumer.enable-auto-commit}}")
    private Boolean dmsConsumerAutoCommit;

    @Value("${DMS_CONSUMER_AUTO_COMMIT_INTERVAL_MS:${hwc.poc.inventory.dms.dms-consumer.auto-commit-interval-ms}}")
    private Integer dmsConsumerAutoCommitInterval;



    @Bean
    Consumer<String, String> dmsConsumer(){

        Properties properties = new Properties();

        properties.put("bootstrap.servers", dmsConServers);
        properties.put("group.id", dmsConsumerGroupId);
        properties.put("enable.auto.commit", dmsConsumerAutoCommit);
        if(dmsConsumerAutoCommit) {
            properties.put("auto.commit.interval.ms", dmsConsumerAutoCommitInterval);
        }
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        logger.info("The initialization of dmsConsumer properties is finished, and the dmsConsumerAutoCommit is {}."
                , dmsConsumerAutoCommit);

        return new KafkaConsumer<>(properties);
    }

}
