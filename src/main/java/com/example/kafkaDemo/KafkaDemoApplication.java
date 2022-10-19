package com.example.kafkaDemo;

import com.example.kafkaDemo.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;


@Slf4j
@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class KafkaDemoApplication {

	public static void main(String[] args) {
	   new SpringApplicationBuilder()
			   .bannerMode(Banner.Mode.OFF)
			   .sources(KafkaDemoApplication.class)
			   .run(args);

	   log.info("Running.....");
	}
}
