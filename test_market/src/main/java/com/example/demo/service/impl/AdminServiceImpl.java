package com.example.demo.service.impl;

import com.example.demo.Controller.param.AdminReq;
import com.example.demo.dao.entity.Admin_market;
import com.example.demo.dao.entity.commodity_market;
import com.example.demo.dao.entity.user_market;
import com.example.demo.dao.repository.admin_marketRepository;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Commodity;
import com.example.demo.domain.User;
import com.example.demo.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    admin_marketRepository admin_marketRepository;
    @Override
    public Admin createOne(AdminReq adminReq) {
        Admin_market admin_market=new Admin_market();
        BeanUtils.copyProperties(adminReq,admin_market);
        admin_marketRepository.save(admin_market);
        Admin admin=new Admin();
        BeanUtils.copyProperties(admin_market,admin);
        return admin;
    }

    @Override
    public String findAdmin(String username, String userpwd) {
        Admin_market admin_market=null;
        try {
            admin_market = admin_marketRepository.findById(username).get();
        }catch (Exception e){
            return "-1";
        }
        if(admin_market.getUserpwd().equals(userpwd)){
            return "1";
        }
        else{
            return "0";
        }
    }

    @Override
    public String addAdmin(Admin_market admin_market) {
        try{
            Admin_market adminMarket=admin_marketRepository.findById(admin_market.getUsername()).get();
        }catch (Exception e){
            admin_marketRepository.save(admin_market);
            return "1";
        }
        return "-1";
    }

}
