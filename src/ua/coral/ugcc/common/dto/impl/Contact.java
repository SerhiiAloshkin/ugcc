package ua.coral.ugcc.common.dto.impl;

import ua.coral.ugcc.common.annotation.ShortString;
import ua.coral.ugcc.common.dto.Dto;

import java.io.Serializable;

public class Contact implements Dto, Serializable {

    private Long id = System.currentTimeMillis();
    @ShortString
    private String address;
    @ShortString
    private String index;
    @ShortString
    private String email;
    @ShortString
    private String phoneNumber;
    @ShortString
    private String description;

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(final String index) {
        this.index = index;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
