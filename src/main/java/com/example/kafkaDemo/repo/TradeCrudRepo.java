package com.example.kafkaDemo.repo;

import com.example.kafkaDemo.bean.Trade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeCrudRepo extends CrudRepository<Trade,Long> {
}
