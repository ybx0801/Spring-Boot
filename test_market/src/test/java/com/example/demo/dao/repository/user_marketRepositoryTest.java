package com.example.demo.dao.repository;

import com.example.demo.dao.entity.user_market;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class user_marketRepositoryTest {
    @Autowired
    private user_marketRepository user_marketRepository;
    @Test
    void findAll(){
        System.out.println(user_marketRepository.findAll());
    }
    @Test
    void findById(){
        user_market  user_market=user_marketRepository.findById("ybx0801").get();
        System.out.println(user_market);
    }

}