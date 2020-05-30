package com.example.demo.dao.repository;

import com.example.demo.dao.entity.user_market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface user_marketRepository extends JpaRepository<user_market,String> {
    public user_market findByUserarticle1(String id);
    @Query(value="select * from user_market",nativeQuery=true)
    public List<user_market> findAllUser();
}
