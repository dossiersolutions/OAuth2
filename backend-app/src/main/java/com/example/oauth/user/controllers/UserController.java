package com.example.oauth.user.controllers;

import com.example.oauth.user.models.UserEntity;
import com.example.oauth.user.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserEntityService userService;

  @RequestMapping(method = RequestMethod.POST, value = "/users/login")
  public boolean loginUser(@RequestBody UserEntity user) {
    return userService.getSingleUser(user);
  }

  @RequestMapping("/users/details/{userName}")
  public UserEntity getUserDetails(@PathVariable String userName) {
    UserEntity dbUser = userService.getUserDetails(userName);
    return new UserEntity(dbUser.getId(), dbUser.getEmail(), dbUser.getName());
  }
}
