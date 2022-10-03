package com.example.kafkaDemo.controller;

import com.example.kafkaDemo.bean.Trade;
import com.example.kafkaDemo.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

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
