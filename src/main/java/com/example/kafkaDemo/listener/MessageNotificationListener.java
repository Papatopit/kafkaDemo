package com.example.kafkaDemo.listener;

import com.example.kafkaDemo.bean.Status;
import com.example.kafkaDemo.bean.Trade;
import com.example.kafkaDemo.bean.User;
import com.example.kafkaDemo.repo.TradeCrudRepo;
import com.example.kafkaDemo.repo.TradeRequestRepo;
import com.example.kafkaDemo.repo.UserCrudRepo;
import com.example.kafkaDemo.scheduler.Scheduler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class MessageNotificationListener {

    @Autowired
    private TradeCrudRepo tradeCrudRepo;
    @Autowired
    private UserCrudRepo userCrudRepo;
    @Autowired
    private TradeRequestRepo tradeRequestRepo;




    @Value("trade.topic-name")
    private String topicName;

    @KafkaListener(topics = "MESSAGE_TOPIC",groupId = "groupId")
    public void listener(String message){
        log.info("Received Trade in group: {}", message);
        ObjectMapper om = new ObjectMapper();

        Trade trade = null;
        try {
            trade = om.readValue(message, Trade.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        User user = userCrudRepo.findById(trade.getId()).get();
        if (trade.getStatus() == Status.IN_PROCESSING){
            trade.setStatus(Status.ACCEPTED);
            userCrudRepo.save(user);
            tradeCrudRepo.save(trade);
        } else {
            trade.setStatus(Status.CANCELLED);
            tradeCrudRepo.save(trade);
        }
    }
}
