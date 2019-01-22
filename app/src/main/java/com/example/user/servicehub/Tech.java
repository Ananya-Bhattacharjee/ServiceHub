package com.example.user.servicehub;

/**
 * Created by User on 5/20/2017.
 */

public class Tech {
    String id;
    String name;
    String password;
    String location;
    String address;
    String type;
    String rate;
    String contactNo;

    public Tech() {
    }

    public Tech(String id, String name, String password, String location, String address, String type, String rate, String contactNo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.location = location;
        this.address = address;
        this.type = type;
        this.rate=rate;
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

    public String getType() {
        return type;
    }

    public String getRate() {
        return rate;
    }

    public String getContactNo() {
        return contactNo;
    }
}
