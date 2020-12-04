package com.example.oauth.guest.services

import com.example.oauth.guest.models.Guest
import com.example.oauth.guest.repositories.GuestRepository
import com.example.oauth.user.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GuestService {

    @Autowired
    private var guestRepository: GuestRepository? = null;

    @Autowired
    private var userRepository: UserRepository? = null;


    fun getAllGuestsById(userId: Int):List<Guest?>?{
        val userEntity = userRepository?.findById(userId);
        return guestRepository?.findAllByUser(userEntity?.get());
    }

    fun getSingleGuestById(id: Int): Guest?{
        val guest: Optional<Guest>? = guestRepository?.findById(id);
        return guest?.get();
    }

    fun deleteSingleGuest(id: String){
        guestRepository?.deleteById(Integer.valueOf(id));
    }

    fun addSingleGuest(guest: Guest){
        guestRepository?.save(guest);
    }

    fun updateGuest(guest: Guest): Guest?{
        return guestRepository?.save(guest);
    }
}