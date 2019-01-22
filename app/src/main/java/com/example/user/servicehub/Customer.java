package com.example.user.servicehub;

import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by User on 5/1/2017.
 */
@IgnoreExtraProperties
public class Customer {
    String id;
    String name;
    String password;
    String location;
    String address;
    String contactNo;

    public Customer() {
    }

    public Customer(String id, String name, String password, String location, String address, String contactNo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.location = location;
        this.address = address;
        this.contactNo = contactNo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }
}
