package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.entity.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/testBoot")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public User GetUser(@PathVariable String id){
        User u = userService.select(id);
        logger.info("[GetUser] id:{} ,return:{}",id, JSON.toJSONString(u));
        return u;
    }

    @RequestMapping("users")
    public List<User> GetUsers(){
        return userService.users().stream().sorted((o,t)->{
          return o.getUserName().compareTo(t.getUserName());
        }).collect(Collectors.toList());
    }
}
