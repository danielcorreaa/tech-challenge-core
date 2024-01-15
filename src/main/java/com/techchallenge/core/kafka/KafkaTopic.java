package com.techchallenge.core.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;


public class KafkaTopic {

    private String bootstrapAddress;

    private String topic;

    public KafkaTopic(String bootstrapAddress, String topic) {
        this.bootstrapAddress = bootstrapAddress;
        this.topic = topic;
    }


    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    public NewTopic createTopic(int numPartitions, short replicationFactor) {
        return new NewTopic(topic, numPartitions, (short) replicationFactor);
    }
}
