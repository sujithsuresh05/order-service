package com.food.ordering.system.kafka.config.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "kafka-config")
@Configuration
public class KafkaConfigData {

    private String bootstrapServers;
    private String schemaRegistryKey;
    private String schemaRegistryUrl;
    private Integer numOfPartitions;
    private Short replicationFactor;

}
