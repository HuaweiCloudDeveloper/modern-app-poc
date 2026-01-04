package com.hwc.poc.inventoryservice.infra.dynamodb;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class DynamoDbConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DynamoDbConfiguration.class);

    @Value("${hwc.poc.inventory.dynamodb.max-connections}")
    private Integer dynamoDbMaxCon;

    @Value("${hwc.poc.inventory.dynamodb.connection-timeout}")
    private Integer dynamoDbConTimeout;

    @Value("${hwc.poc.inventory.dynamodb.socket-timeout}")
    private Integer dynamoDbSocketTimeout;

    @Value("${hwc.poc.inventory.dynamodb.region}")
    private String dynamoDbRegion;

    @Value("${DB_HOST_AND_PORT:${hwc.poc.inventory.dynamodb.url}}")
    private String dynamoDbConRul;

    @Value("${DB_USERNAME:${hwc.poc.inventory.dynamodb.ak}}")
    private String dynamoDbAk;

    @Value("${DB_PASSWORD:${hwc.poc.inventory.dynamodb.sk}}")
    private String dynamoDbSk;

    @Bean
    public AmazonDynamoDB dynamoDBClient() {

        ClientConfiguration clientConfig = new ClientConfiguration()
                .withMaxConnections(dynamoDbMaxCon)
                .withConnectionTimeout(dynamoDbConTimeout)
                .withSocketTimeout(dynamoDbSocketTimeout)
                .withProtocol(Protocol.HTTP);

        BasicAWSCredentials credentials = new BasicAWSCredentials(
                dynamoDbAk,
                dynamoDbSk
        );

        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDbConRul, dynamoDbRegion))
                .withClientConfiguration(clientConfig)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        logger.info("Create dynamoDBClient successfully");

        return dynamoDBClient;
    }

}
