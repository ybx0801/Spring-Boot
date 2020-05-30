package com.example.demo.service;

import com.example.demo.Controller.param.AdminReq;
import com.example.demo.dao.entity.Admin_market;
import com.example.demo.domain.Admin;
import com.example.demo.domain.User;

import java.util.List;

public interface AdminService {
    public Admin createOne(AdminReq adminReq);
    public String findAdmin(String username,String userpwd);
    public String addAdmin(Admin_market admin_market);
}
