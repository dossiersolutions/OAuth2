package com.example.oauth.guest.repositories;

import java.util.List;

import com.example.oauth.guest.models.Guest;
import com.example.oauth.user.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
  public List<Guest> findAllByUser(UserEntity userEntity);
  public long countGuestsByUser(UserEntity userEntity);
  public long countAllBy();
  public long countGuestsByConfirmedEquals(String checkTrue);
}
