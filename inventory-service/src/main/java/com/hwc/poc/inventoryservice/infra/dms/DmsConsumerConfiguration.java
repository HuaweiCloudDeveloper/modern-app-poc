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

    @Value("${DMS_CONSUMER_MAX_POLL_RECORDS:${hwc.poc.inventory.dms.dms-consumer.max-poll-records}}")
    private Integer dmsConsumerMaxPollRecords;

    @Value("${DMS_CONSUMER_MAX_POLL_INTERVAL_MS:${hwc.poc.inventory.dms.dms-consumer.max-poll-interval-ms}}")
    private Integer dmsConsumerMaxPollIntervalMs;

    @Value("${DMS_CONSUMER_CONNECTIONS_MAX_IDLE_MS:${hwc.poc.inventory.dms.dms-consumer.connections-max-idle-ms}}")
    private Integer dmsConsumerConnectionsMaxIdleMs;

    @Value("${DMS_CONSUMER_HEARTBEAT_INTERVAL_MS:${hwc.poc.inventory.dms.dms-consumer.heartbeat-interval-ms}}")
    private Integer dmsConsumerHeartbeatIntervalMs;

    @Value("${DMS_CONSUMER_SESSION_TIMEOUT_MS:${hwc.poc.inventory.dms.dms-consumer.session-timeout-ms}}")
    private Integer dmsConsumerSessionTimeoutMs;

    @Value("${DMS_CONSUMER_FETCH_MAX_BYTES:${hwc.poc.inventory.dms.dms-consumer.fetch-max-bytes}}")
    private Integer dmsConsumerFetchMaxBytes;

    @Value("${DMS_CONSUMER_AUTO_OFFSET_RESET:${hwc.poc.inventory.dms.dms-consumer.auto-offset-reset}}")
    private String dmsConsumerAutoOffsetReset;

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

        // add extends params
        properties.put("max.poll.records", dmsConsumerMaxPollRecords);
        properties.put("max.poll.interval.ms", dmsConsumerMaxPollIntervalMs);
        properties.put("connections.max.idle.ms", dmsConsumerConnectionsMaxIdleMs);
        properties.put("heartbeat.interval.ms", dmsConsumerHeartbeatIntervalMs);
        properties.put("session.timeout.ms", dmsConsumerSessionTimeoutMs);
        properties.put("fetch.max.bytes", dmsConsumerFetchMaxBytes);
        properties.put("auto.offset.reset", dmsConsumerAutoOffsetReset);

        logger.info("The initialization of dmsConsumer properties is finished, and the dmsConsumerAutoCommit is {}."
                , dmsConsumerAutoCommit);

        return new KafkaConsumer<>(properties);
    }

}
