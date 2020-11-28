package com.example.oauth.user.services;

import java.util.List;
import java.util.Optional;

import com.example.oauth.user.repositories.UserRepository;
import com.example.oauth.user.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {

  @Autowired
  private UserRepository userRepository;

  public boolean getSingleUser(UserEntity user) {
    List<UserEntity> listAllUsers = (List<UserEntity>) userRepository.findAll();
    for (UserEntity u : listAllUsers) {
      if (u.getEmail().equals(user.getEmail()) &&
          u.getPassword().equals(user.getPassword())) {
        return true;
      }
    }
    return false;
  }

  public Optional<UserEntity> getSingleUserById(int userId) {
    return userRepository.findById(userId);
  }

  public UserEntity getUserDetails(String userName) {
    return userRepository.findByEmail(userName);
  }
}
