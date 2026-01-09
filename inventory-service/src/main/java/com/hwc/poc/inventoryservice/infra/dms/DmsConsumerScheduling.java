package com.hwc.poc.inventoryservice.infra.dms;


import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;

@Component
@EnableScheduling
@EnableAsync
public class DmsConsumerScheduling {

    private static final Logger logger = LoggerFactory.getLogger(DmsConsumerScheduling.class);

    @Autowired
    Consumer<String, String> dmsConsumer;

    @Value("${DMS_CONSUMER:${hwc.poc.inventory.dms.dms-consumer}}")
    private Boolean dmsConsumerFlag;

    @Value("${DMS_TOPIC:${hwc.poc.inventory.dms.topic}}")
    private String dmsTopic;

    @Value("${DMS_CONSUMER_PULL_DURATION:${hwc.poc.inventory.dms.dms-consumer.pull-duration}}")
    private Integer dmsConsumerPullDuration;

    @PostConstruct
    @Async
    public void startDmsConsumer() {

        // if dmsConsumerFlag is false, return directly;
        if(!dmsConsumerFlag) {
            logger.warn("The dmsConsumerFlag is false, and return directly.");
            return;
        }

        dmsConsumer.subscribe(Arrays.asList(dmsTopic));
        logger.warn("The dmsConsumerFlag is true, and the dmsConsumer subscription is complete.");

        dmsConsumerPullInit();
    }

    @Async
    private void dmsConsumerPullInit(){
        try {
            logger.info("The dmsConsumer pulling async task is running at {}"
                    , Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()));
            new Thread(()-> {
                while(true) {

                    ConsumerRecords<String, String> records =
                            dmsConsumer.poll(Duration.ofMillis(dmsConsumerPullDuration));
                    Calendar calendar = Calendar.getInstance();
                    ZonedDateTime zonedDateTime = calendar.toInstant().atZone(ZoneId.systemDefault());
                    for(ConsumerRecord<String, String> record : records){
                        System.out.printf("InventoryTime = %s, offset = %d, key = %s, value = %s%n", zonedDateTime
                                , record.offset(), record.key(), record.value());
                    }
                }
            }).start();
        } catch (RuntimeException e) {

            logger.error("The dmsConsumer pulling task throws RuntimeException.", e);
        }
    }
}
