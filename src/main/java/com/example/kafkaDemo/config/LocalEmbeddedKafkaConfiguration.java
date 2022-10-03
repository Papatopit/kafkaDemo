package com.example.kafkaDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

@Profile("local")
@Configuration
public class LocalEmbeddedKafkaConfiguration {
    @Bean
    EmbeddedKafkaBroker embeddedKafkaBroker() {
        EmbeddedKafkaBroker embeddedKafkaBroker = new EmbeddedKafkaBroker(1,true,1
        ,"MESSAGE_TOPIC")
                .zkPort(9021)
                .kafkaPorts(9092)
                .zkConnectionTimeout(5000)
                .zkSessionTimeout(5000);
        embeddedKafkaBroker.brokerProperty("listeners","PLAINTEXT://localhost:9092");
        embeddedKafkaBroker.brokerProperty("port","9092");
        embeddedKafkaBroker.brokerProperty("compression.type", "producer");

        return embeddedKafkaBroker;
    }
}
