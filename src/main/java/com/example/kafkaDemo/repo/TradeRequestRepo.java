package com.example.kafkaDemo.repo;

import com.example.kafkaDemo.bean.TradeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TradeRequestRepo extends CrudRepository<TradeRequest, Long> {
}
