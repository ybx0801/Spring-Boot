package com.example.demo.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class commodity_market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String c_id;
    private String c_uid;
    private String c_name;
    private String c_message;
    private String c_details;
    private String c_address;
    private int c_quantity;
    private int c_price;
    private int c_state;
    private String c_reason;
    private String c_comment;
    private String c_buyer;
    private int c_star;
}
