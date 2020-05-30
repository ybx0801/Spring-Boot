package com.example.demo.Controller.param;

import lombok.Data;

import javax.persistence.Id;

@Data
public class UserReq {
    private String username;
    private String userpwd;
    private String userarticle1;
}
