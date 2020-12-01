package com.example.oauth.guest.controllers;

import com.example.oauth.guest.models.Guest;
import com.example.oauth.guest.services.GuestService;
import com.example.oauth.user.models.UserEntity;
import com.example.oauth.user.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    @Autowired
    private UserEntityService userService;

    @RequestMapping("/guests/{userId}")
    public List<Guest> getAllGuestsById(@PathVariable int userId){
        return guestService.getAllGuestsById(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/guests/{userId}")
    public ResponseEntity<String> addSingleGuest(
            @RequestBody Guest guest,
            @PathVariable int userId
    ) {

        Optional<UserEntity> user = userService.getSingleUserById(userId);
        guest.setUser(user.get());

        guestService.addSingleGuest(guest);
        return ResponseEntity.status(HttpStatus.OK).body("Your user is added");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/guests/{id}")
    public Guest updateGuest(@RequestBody Guest guest, @PathVariable int id){
        Guest oldGuest = guestService.getSingleGuestById(id);
        oldGuest.setFirstName(guest.getFirstName());
        oldGuest.setLastName(guest.getLastName());
        oldGuest.setDescription(guest.getDescription());
        oldGuest.setInvited(guest.getInvited());
        oldGuest.setConfirmed(guest.getConfirmed());
        oldGuest.setTableNo(guest.getTableNo());
        return guestService.updateGuest(oldGuest);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/guests/{id}")
    public void deleteSingleGuest(@PathVariable String id){
        guestService.deleteSingleGuest(id);
    }
}
