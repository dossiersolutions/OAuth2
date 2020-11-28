package com.example.oauth.user.repositories;

import com.example.oauth.user.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
  public UserEntity findByEmail(String email);
  public UserEntity findUserEntityByEmail(String email);
}
