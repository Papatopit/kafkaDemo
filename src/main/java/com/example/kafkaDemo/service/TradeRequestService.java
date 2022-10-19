package com.example.kafkaDemo.service;

import com.example.kafkaDemo.bean.Status;
import com.example.kafkaDemo.bean.TradeRequest;
import com.example.kafkaDemo.repo.TradeRequestRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TradeRequestService {
    @Autowired
    private TradeRequestRepo tradeRequestRepo;
    @Autowired
    private UserService userService;

    @Value("trade.topic-name")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper om = new ObjectMapper();


    public TradeRequest sendTradeRequest(TradeRequest tradeRequest) {
        tradeRequest = tradeRequestRepo.save(tradeRequest);
        tradeRequest.setStatus(Status.IN_PROCESSING);
        String message = null;
        try {
            message = om.writeValueAsString(tradeRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topicName,message);
        return tradeRequest;
    }


}
