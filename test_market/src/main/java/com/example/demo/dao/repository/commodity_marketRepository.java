package com.example.demo.dao.repository;

import com.example.demo.dao.entity.commodity_market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface commodity_marketRepository extends JpaRepository<commodity_market,String> {
    @Query(value="select * from commodity_market where c_uid=?1 and c_state=0",nativeQuery=true)
    public List<commodity_market> findAllByC_uid(String Id);
    @Query(value="select * from commodity_market where c_state=?1",nativeQuery=true)
    public List<commodity_market> findAllByC_state(String Id);
    @Query(value="select * from commodity_market where c_state=?1 and c_uid=?2",nativeQuery=true)
    public List<commodity_market> findAllByC_stateAndC_uid(String Id,String Uid);
    @Query(value="select * from commodity_market where c_buyer=?1 and c_state=?2",nativeQuery=true)
    public List<commodity_market> findAllByC_buyer(String Id,String state);
}
