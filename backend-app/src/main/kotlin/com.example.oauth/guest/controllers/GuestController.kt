package com.example.oauth.guest.controllers

import com.example.oauth.guest.models.Guest
import com.example.oauth.guest.services.GuestService
import com.example.oauth.user.services.UserEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class GuestController {
    @Autowired
    private val guestService: GuestService? = null

    @Autowired
    private val userService: UserEntityService? = null

    @RequestMapping("/guests/{userId}")
    fun getAllGuestsById(@PathVariable userId: Int): List<Guest?>? {
        return guestService!!.getAllGuestsById(userId)
    }

    @RequestMapping(method = [RequestMethod.POST], value = ["/guests/{userId}"])
    fun addSingleGuest(@RequestBody guest: Guest, @PathVariable userId: Int): ResponseEntity<String>? {
        val user = userService!!.getSingleUserById(userId);
        guest.user = user.get();

        guestService!!.addSingleGuest(guest);
        return ResponseEntity.status(HttpStatus.OK).body("Your user has been added");
    }

    @RequestMapping(method = [RequestMethod.PUT], value = ["/guests/{id}"])
    fun updateGuest(@RequestBody guest: Guest, @PathVariable id: Int): Guest? {
        val oldGuest = guestService!!.getSingleGuestById(id);
        oldGuest?.firstName = guest.firstName;
        oldGuest?.lastName = guest.lastName;
        oldGuest?.description = guest.description;
        oldGuest?.invited = guest.invited;
        oldGuest?.confirmed = guest.confirmed;
        oldGuest?.tableNo = guest.tableNo;
        return guestService.updateGuest(oldGuest!!);
    }

    @RequestMapping(method = [RequestMethod.DELETE], value = ["/guests/{id}"])
    fun deleteSingleGuest(@PathVariable id: String) {
        guestService!!.deleteSingleGuest(id);
    }
}