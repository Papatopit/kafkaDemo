package com.example.kafkaDemo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class KafkaDemoApplication {

	public static void main(String[] args) {
	   new SpringApplicationBuilder()
			   .bannerMode(Banner.Mode.OFF)
			   .sources(KafkaDemoApplication.class)
			   .run(args);
	}
}
