package com.example.kafkaDemo.service;

import com.example.kafkaDemo.bean.Trade;
import com.example.kafkaDemo.repo.TradeCrudRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    TradeCrudRepo tradeCrudRepo;

    @Value("trade.topic-name")
    private String topicName;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    public Trade createTrade(Trade trade){
        trade = tradeCrudRepo.save(trade);
        trade.setStatus("CREATED");
        String message = null;
        try {
            message = objectMapper.writeValueAsString(trade);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(topicName, message);
        return trade;
    }
    public List<Trade> getAllTrades(){
        List<Trade> trades = (List<Trade>) tradeCrudRepo.findAll();
        return trades;

    }

}
