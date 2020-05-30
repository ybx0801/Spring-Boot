package com.example.demo.Controller;
import com.example.demo.dao.entity.user_market;
import com.example.demo.dao.repository.user_marketRepository;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;

@RestController
@RequestMapping("/user_market")
public class user_marketHander {
    @Autowired
    private user_marketRepository user_marketRepository;
    @Autowired
    private UserService userService;
    private final Log log = LogFactory.getLog(user_marketHander.class);

    @GetMapping("/findAll")
    public List<user_market> findAll(){
        return user_marketRepository.findAll();
    }
    @GetMapping("/findUser/{username}/{userpwd}")
    public String findUser(@PathVariable("username") String username, @PathVariable("userpwd") String userpwd){
        return userService.findUser(username,userpwd);
    }
    @GetMapping("/findId/{Id}")
    public String findId(@PathVariable("Id") String Id){
        return userService.findId(Id);
    }
    @PostMapping("/addUser")
    public String addUser(@RequestBody user_market user_market){
        return userService.addUser(user_market);
    }
    @GetMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }
    @PostMapping("/DeleteUser")
    public String deleteUser(@RequestBody user_market user_market){
        return userService.deleteUser(user_market);
    }
}
