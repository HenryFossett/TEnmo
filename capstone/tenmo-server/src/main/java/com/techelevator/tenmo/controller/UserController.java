package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
@RestController
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }
    @GetMapping(path = "/{username}")
    public int getIdByUsername(String username){
       return this.userDao.findIdByUsername(username);
    }
    @GetMapping(path = "")
    public List<User> getAllUsers(){
        return this.userDao.findAll();
    }
    @GetMapping("byid/{id}")
    public User getByUsername(String username){
        return this.userDao.findByUsername(username);
    }
    @PostMapping("")
    public boolean createUser(String username, String password){
    return this.userDao.create(username, password);
    }

}
