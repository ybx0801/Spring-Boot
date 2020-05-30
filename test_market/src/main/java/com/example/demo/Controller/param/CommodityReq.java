package com.example.demo.Controller.param;

import lombok.Data;

@Data
public class CommodityReq {
    private String c_id;
    private String c_uid;
    private String c_name;
    private String c_message;
    private String c_details;
    private String address;
    private int c_quantity;
    private int c_price;
    private int c_state;
    private String c_reason;
    private String c_comment;
    private String c_buyer;
    private int c_star;
}
