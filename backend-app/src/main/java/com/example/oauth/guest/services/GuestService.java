package com.example.oauth.guest.services;

import com.example.oauth.guest.models.Guest;
import com.example.oauth.guest.models.GuestData;
import com.example.oauth.guest.repositories.GuestRepository;
import com.example.oauth.user.models.UserEntity;
import com.example.oauth.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Guest> getAllGuests() {
        return (List<Guest>) guestRepository.findAll();
    }

    public GuestData getAllGuestsById(int id, String jovanMail) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        Optional<UserEntity> userEntityByEmailJovan = Optional.ofNullable(userRepository.findUserEntityByEmail(jovanMail));
        long jovanCount = guestRepository.countGuestsByUser(userEntityByEmailJovan.get());
        long totalCount = guestRepository.countAllBy();
        long totalCountConfirmed = guestRepository.countGuestsByConfirmedEquals("true");
        List<Guest> listAllGuests = guestRepository.findAllByUser(userEntity.get());

        GuestData data = new GuestData();
        data.setGuestData(listAllGuests);
        data.setJovanGuests(jovanCount);
        data.setAllGuests(totalCount);
        data.setConfirmedGuests(totalCountConfirmed);
        return data;
    }

    public Guest getSingleGuestById(int id){
        Optional<Guest> guest = guestRepository.findById(id);
        return guest.get();
    }

    public List<Guest> getAllGuestsById() {
        return (List<Guest>) guestRepository.findAll();
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
