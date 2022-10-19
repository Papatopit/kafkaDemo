package com.example.kafkaDemo.controller;

import com.example.kafkaDemo.bean.Trade;
import com.example.kafkaDemo.bean.TradeRequest;
import com.example.kafkaDemo.service.TradeRequestService;
import com.example.kafkaDemo.service.TradeService;
import com.example.kafkaDemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserService userService;
    @Autowired
    private TradeRequestService tradeRequestService;

    @PostMapping(value = "/create")
    public Trade createTrade(@RequestBody Trade trade) {
        trade = tradeService.createTrade(trade);
        return trade;
    }

    @GetMapping(value = "/all")
    public List<Trade> getTrades(){
        return tradeService.getAllTrades();
    }

}
