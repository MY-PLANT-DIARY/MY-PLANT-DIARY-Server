package com.myplantdiary.plant.domain.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String receiver;
    private String phoneNum;
    private String city;
    private String street;
    private String zipcode;

    protected Address(){}

    public Address(String receiver, String phoneNum, String city, String street, String zipcode) {
        this.receiver = receiver;
        this.phoneNum = phoneNum;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
