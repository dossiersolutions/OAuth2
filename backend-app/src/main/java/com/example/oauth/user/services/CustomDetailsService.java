package com.example.oauth.user.services;

import com.example.oauth.oauth2.OAuthDao;
import com.example.oauth.user.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {

  @Autowired
  OAuthDao oauthDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = null;
    try {
      userEntity = oauthDao.getUserDetails(username);
      User customUser = new User(userEntity.getEmail(), userEntity.getPassword(), userEntity.getGrantedAuthoritiesList());
      return customUser;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new UsernameNotFoundException("User " + username + " was not found in the database");
    }
  }
}
