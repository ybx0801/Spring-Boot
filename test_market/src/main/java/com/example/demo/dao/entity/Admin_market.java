package com.example.demo.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Admin_market {
    @Id
    private String username;
    private String userpwd;
}
