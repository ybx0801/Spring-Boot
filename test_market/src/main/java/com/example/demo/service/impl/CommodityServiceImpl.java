package com.example.demo.service.impl;

import com.example.demo.Controller.param.CommodityReq;
import com.example.demo.dao.entity.commodity_market;
import com.example.demo.dao.repository.commodity_marketRepository;
import com.example.demo.domain.Commodity;
import com.example.demo.service.CommodityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    commodity_marketRepository commodity_marketRepository;


    @Override
    public Commodity createOne(CommodityReq commodityReq) {
        commodity_market commodity_market=new commodity_market();
        BeanUtils.copyProperties(commodityReq,commodity_market);
        commodity_marketRepository.save(commodity_market);
        Commodity commodity=new Commodity();
        BeanUtils.copyProperties(commodityReq,commodity);
        return commodity;
    }

    @Override
    public List<Commodity> getAll(){
        List<commodity_market> commodity_marketRepositories=commodity_marketRepository.findAll();
        List<Commodity> commodities=commodity_marketRepositories.stream().map(entity->{
            Commodity commodity=new Commodity();
            BeanUtils.copyProperties(entity,commodity);
            return commodity;
        }).collect(Collectors.toList());
        return commodities;
    }

    @Override
    public List<Commodity> findById(String Id) {
        //commodity_market commodity_markets=commodity_marketRepository.findById(Id).get();
        List<commodity_market> commodity_marketRepositories=commodity_marketRepository.findAllById(Collections.singleton(Id));
        List<Commodity> commodities=commodity_marketRepositories.stream().map(entity->{
            Commodity commodity=new Commodity();
            BeanUtils.copyProperties(entity,commodity);
            return commodity;
        }).collect(Collectors.toList());
        return commodities;
    }

    @Override
    public List<Commodity> findAllById(String Id) {
        List<commodity_market> commodity_marketRepositories=commodity_marketRepository.findAllByC_uid(Id);
        List<Commodity> commodities=commodity_marketRepositories.stream().map(entity->{
            Commodity commodity=new Commodity();
            BeanUtils.copyProperties(entity,commodity);
            return commodity;
        }).collect(Collectors.toList());
        return commodities;
    }

    @Override
    public String add(commodity_market commodity_market) {
        commodity_market commodityMarket=commodity_marketRepository.save(commodity_market);
        return "1";
    }

    @Override
    public String delete(commodity_market commodity_market) {
        commodity_marketRepository.delete(commodity_market);
        return "1";
    }

    @Override
    public List<Commodity> findAllByState(String Id) {
        List<commodity_market> commodity_marketRepositories=commodity_marketRepository.findAllByC_state(Id);
        List<Commodity> commodities=commodity_marketRepositories.stream().map(entity->{
            Commodity commodity=new Commodity();
            BeanUtils.copyProperties(entity,commodity);
            return commodity;
        }).collect(Collectors.toList());
        return commodities;
    }

    @Override
    public List<Commodity> findAllBySandU(String Id, String Uid) {
        List<commodity_market> commodity_marketRepositories=commodity_marketRepository.findAllByC_stateAndC_uid(Id,Uid);
        List<Commodity> commodities=commodity_marketRepositories.stream().map(entity->{
            Commodity commodity=new Commodity();
            BeanUtils.copyProperties(entity,commodity);
            return commodity;
        }).collect(Collectors.toList());
        return commodities;
    }

    @Override
    public List<Commodity> findAllByBuyer(String Id,String state) {
        List<commodity_market> commodity_marketRepositories=commodity_marketRepository.findAllByC_buyer(Id,state);
        List<Commodity> commodities=commodity_marketRepositories.stream().map(entity->{
            Commodity commodity=new Commodity();
            BeanUtils.copyProperties(entity,commodity);
            return commodity;
        }).collect(Collectors.toList());
        return commodities;
    }
}
