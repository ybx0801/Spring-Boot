package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Admin_market;
import com.example.demo.dao.entity.user_market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface admin_marketRepository extends JpaRepository<Admin_market,String> {
    @Query(value="select * from user_market",nativeQuery=true)
    public List<user_market> findAllUser();
}
