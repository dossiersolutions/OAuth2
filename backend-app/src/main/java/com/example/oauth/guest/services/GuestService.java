package com.example.oauth.guest.services;

import com.example.oauth.guest.models.Guest;
import com.example.oauth.guest.repositories.GuestRepository;
import com.example.oauth.user.models.UserEntity;
import com.example.oauth.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Guest> getAllGuestsById(int userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        List<Guest> listAllGuests = guestRepository.findAllByUser(userEntity.get());

        return listAllGuests;
    }

    public Guest getSingleGuestById(int id){
        Optional<Guest> guest = guestRepository.findById(id);
        return guest.get();
    }

    public void deleteSingleGuest(String id){
        guestRepository.deleteById(Integer.valueOf(id));
    }

    public void addSingleGuest(Guest guest) {
        guestRepository.save(guest);
    }

    public Guest updateGuest(Guest guest) {
        return guestRepository.save(guest);
    }

}
