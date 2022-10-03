package com.example.kafkaDemo.listener;

import com.example.kafkaDemo.bean.Trade;
import com.example.kafkaDemo.bean.User;
import com.example.kafkaDemo.repo.TradeCrudRepo;
import com.example.kafkaDemo.repo.UserCrudRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

public class MessageNotificationListener {

    @Autowired
    TradeCrudRepo tradeCrudRepo;

    @Autowired
    UserCrudRepo userCrudRepo;

    @Value("trade.topic-name")
    private String topicName;

    @KafkaListener(topics = "MESSAGE_TOPIC",groupId = "groupId")
    public void listen(String message){
        System.out.println("Received Trade in group: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        Trade trade = null;
        try {
            trade = objectMapper.readValue(message, Trade.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        User user = userCrudRepo.findById(trade.getId()).get();
        if (user.getId() == user.getId()){
            user.setMessage(message);
            trade.setStatus("SUCCESS");
            userCrudRepo.save(user);
            tradeCrudRepo.save(trade);        }
        else {
            trade.setStatus("FAILED");
            tradeCrudRepo.save(trade);
        }
    }
}
