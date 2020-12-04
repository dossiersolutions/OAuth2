package com.example.oauth.guest.repositories

import com.example.oauth.guest.models.Guest
import com.example.oauth.user.models.UserEntity
import org.springframework.data.repository.CrudRepository

interface GuestRepository: CrudRepository<Guest, Int> {
    fun findAllByUser(userEntity: UserEntity?): List<Guest?>?
    fun countGuestsByUser(userEntity: UserEntity?): Long
    fun countAllBy(): Long
    fun countGuestsByConfirmedEquals(checkTrue: String?): Long
}