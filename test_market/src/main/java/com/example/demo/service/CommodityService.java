package com.example.demo.service;

import com.example.demo.Controller.param.CommodityReq;
import com.example.demo.dao.entity.commodity_market;
import com.example.demo.domain.Commodity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommodityService {
    public Commodity createOne(CommodityReq commodityReq);
    public List<Commodity> getAll();
    public List<Commodity> findById(String Id);
    public List<Commodity> findAllById(String Id);
    public String add(commodity_market commodity_market);
    public String delete(commodity_market commodity_market);
    public List<Commodity> findAllByState(String Id);
    public List<Commodity> findAllBySandU(String Id,String Uid);
    public List<Commodity> findAllByBuyer(String Id,String state);
}
