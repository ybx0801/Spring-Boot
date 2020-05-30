package com.example.demo.service.impl;

import com.example.demo.Controller.param.UserReq;
import com.example.demo.dao.entity.user_market;
import com.example.demo.dao.repository.user_marketRepository;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    user_marketRepository user_marketRepository;

    @Override
    public User createOne(UserReq userReq) {
        user_market user_market=new user_market();
        BeanUtils.copyProperties(userReq,user_market);
        user_marketRepository.save(user_market);
        User user=new User();
        BeanUtils.copyProperties(user_market,user);
        return user;
    }

    @Override
    public String findUser(String username, String userpwd) {
        user_market user_market=null;
        try {
            user_market = user_marketRepository.findById(username).get();
        }catch (Exception e){
            return "-1";
        }
        if(user_market.getUserpwd().equals(userpwd)){
            return "1";
        }
        else{
            return "0";
        }
    }

    @Override
    public String findId(String id) {
        user_market user_market=user_marketRepository.findByUserarticle1(id);
        return user_market.getUsername();
    }

    @Override
    public String addUser(user_market user_market) {
        try{
            user_market userMarket=user_marketRepository.findById(user_market.getUsername()).get();
        }catch (Exception e){
            user_marketRepository.save(user_market);
            return "1";
        }
        return "-1";
    }

    @Override
    public List<User> findAllUser() {
        List<user_market> user_markets=user_marketRepository.findAllUser();
        List<User> users=user_markets.stream().map(entity->{
            User user=new User();
            BeanUtils.copyProperties(entity,user);
            return user;
        }).collect(Collectors.toList());
        return users;
    }

    @Override
    public String deleteUser(user_market user_market) {
        user_marketRepository.delete(user_market);
        return "1";
    }
}
