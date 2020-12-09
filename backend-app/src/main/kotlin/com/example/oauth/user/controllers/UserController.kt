package com.example.oauth.user.controllers

import com.example.oauth.user.models.UserEntity
import com.example.oauth.user.services.UserEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    var userEntityService: UserEntityService? = null;

    @RequestMapping(method = [RequestMethod.POST], value = ["/users/login"])
    fun loginUser(@RequestBody user: UserEntity): Boolean? {
        return userEntityService?.getSingleUser(user);
    }

    @RequestMapping("/users/details/{userName}")
    fun getUserDetails(@PathVariable userName: String): UserEntity {
        val dbUser: UserEntity = userEntityService!!.getUserDetails(userName);
        return UserEntity(dbUser.id, dbUser.email, dbUser.name);
    }
}