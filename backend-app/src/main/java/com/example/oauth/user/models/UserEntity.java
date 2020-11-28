package com.example.oauth.user.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "my_users")
public class UserEntity {

  @Id
  private int id;
  private String name;
  private String email;
  private String password;

  @ElementCollection
  @Column(name="role")
  private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

  public UserEntity(int id, String name, String email, String password){
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public UserEntity(int id, String email, String name){
    this.id = id;
    this.email = email;
    this.name = name;
  }

  public UserEntity(){}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
    return grantedAuthoritiesList;
  }

  public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
    this.grantedAuthoritiesList = grantedAuthoritiesList;
  }
}
