package com.example.demo.Controller;

import com.example.demo.dao.entity.commodity_market;
import com.example.demo.dao.repository.commodity_marketRepository;
import com.example.demo.domain.Commodity;
import com.example.demo.service.CommodityService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityHandler {
    @Autowired
    private commodity_marketRepository commodity_marketRepository;
    @Autowired
    private CommodityService commodityService;


    @GetMapping("/findAllcommodity")
    public List<commodity_market> findAllCommodity(){
        return commodity_marketRepository.findAll();
    }
    @GetMapping("/getAll")
    public List<Commodity> getAll(){
        List<Commodity> commodities=commodityService.getAll();
        return commodities;
    }
    @GetMapping("/findById/{Id}")
    public List<Commodity> findById(@PathVariable("Id") String Id){

        return commodityService.findById(Id);
    }
    @GetMapping("/findAllById/{Id}")
    public List<Commodity> findAllById(@PathVariable("Id") String Id){
        return commodityService.findAllById(Id);
    }
    @PostMapping("/updateMarket")
    public String update(@RequestBody commodity_market commodity_market){
        commodity_marketRepository.save(commodity_market);
        return "1";
    }
    @PostMapping("/addMarket")
    public String add(@RequestBody commodity_market commodity_market){
        return commodityService.add(commodity_market);
    }
    @PostMapping("/deleteMarket")
    public String delete(@RequestBody commodity_market commodity_market){
        return commodityService.delete(commodity_market);
    }
    @GetMapping("/findAllByState/{Id}")
    public List<Commodity> findAllByState(@PathVariable("Id") String Id){
        return commodityService.findAllByState(Id);
    }
    @GetMapping("/findAllBySandU/{Id}/{Uid}")
    public List<Commodity> findAllBySandU(@PathVariable("Id") String Id,@PathVariable("Uid") String Uid){
        return commodityService.findAllBySandU(Id,Uid);
    }
    @GetMapping("/findAllByBuyer/{Id}/{state}")
    public List<Commodity> findAllByBuyer(@PathVariable("Id") String Id,@PathVariable("state") String state){
        return commodityService.findAllByBuyer(Id,state);
    }
}
