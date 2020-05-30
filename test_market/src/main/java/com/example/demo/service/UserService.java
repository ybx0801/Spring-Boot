package com.example.demo.service;

import com.example.demo.Controller.param.UserReq;
import com.example.demo.dao.entity.user_market;
import com.example.demo.dao.repository.user_marketRepository;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    public User createOne(UserReq userReq);
    public String findUser(String username,String userpwd);
    public String findId(String id);
    public String addUser(user_market user_market);
    public List<User> findAllUser();
    public String deleteUser(user_market user_market);
}
