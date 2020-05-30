package com.example.demo.Controller;

import com.example.demo.dao.entity.Admin_market;
import com.example.demo.dao.entity.user_market;
import com.example.demo.dao.repository.admin_marketRepository;
import com.example.demo.domain.User;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminHander {
    @Autowired
    private admin_marketRepository admin_marketRepository;
    @Autowired
    private AdminService adminService;
    @GetMapping("/findAdmin/{username}/{userpwd}")
    public String findAdmin(@PathVariable("username") String username,@PathVariable("userpwd") String userpwd){
        return adminService.findAdmin(username,userpwd);
    }
    @PostMapping("/addAdmin")
    public String addAdmin(@RequestBody Admin_market admin_market){
        return adminService.addAdmin(admin_market);
    }
}
