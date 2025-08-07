package com.yzkj.starter.controller;

import com.yzkj.starter.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mvcuser")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/name/{userId}", method = RequestMethod.GET)
    public String query(@PathVariable("userId") String userId) {
        return userService.queryName(userId);
    }
}
