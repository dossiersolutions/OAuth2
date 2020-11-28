package com.example.oauth.guest.models;

import java.util.List;

public class GuestData {

    private List<Guest> guestData;
    private long allGuests;
    private long jovanGuests;
    private long danicaGuests;
    private long confirmedGuests;

    public List<Guest> getGuestData() {
        return guestData;
    }

    public void setGuestData(List<Guest> guestData) {
        this.guestData = guestData;
    }

    public long getAllGuests() {
        return allGuests;
    }

    public void setAllGuests(long allGuests) {
        this.allGuests = allGuests;
    }

    public long getJovanGuests() {
        return jovanGuests;
    }

    public void setJovanGuests(long jovanGuests) {
        this.jovanGuests = jovanGuests;
    }

    public long getDanicaGuests() {
        return danicaGuests;
    }

    public void setDanicaGuests(long danicaGuests) {
        this.danicaGuests = danicaGuests;
    }

    public long getConfirmedGuests() {
        return confirmedGuests;
    }

    public void setConfirmedGuests(long confirmedGuests) {
        this.confirmedGuests = confirmedGuests;
    }
}
