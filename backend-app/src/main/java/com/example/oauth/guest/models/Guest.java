package com.example.oauth.guest.models;

import javax.persistence.*;

import com.example.oauth.user.models.UserEntity;

@Entity
public class Guest {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;
  private String firstName;
  private String lastName;
  private String description;
  private String invited;
  private String confirmed;
  private String tableNo;

  @ManyToOne
  private UserEntity user;

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getInvited() {
    return invited;
  }

  public void setInvited(String invited) {
    this.invited = invited;
  }

  public String getConfirmed() {
    return confirmed;
  }

  public void setConfirmed(String confirmed) {
    this.confirmed = confirmed;
  }

  public String getTableNo() {
    return tableNo;
  }

  public void setTableNo(String tableNo) {
    this.tableNo = tableNo;
  }
}
