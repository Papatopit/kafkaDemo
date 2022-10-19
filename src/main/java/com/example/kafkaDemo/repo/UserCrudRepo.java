package com.example.kafkaDemo.repo;

import com.example.kafkaDemo.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepo extends CrudRepository<User,Long> {

}
