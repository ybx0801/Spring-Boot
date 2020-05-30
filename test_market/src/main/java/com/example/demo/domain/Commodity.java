package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    @Id
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
