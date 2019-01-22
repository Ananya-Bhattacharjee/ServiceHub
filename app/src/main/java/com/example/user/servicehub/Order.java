package com.example.user.servicehub;

/**
 * Created by User on 5/7/2017.
 */

public class Order {
    String id;
    String contactNo;
    String type;
    String desc;
    String location;
    String address;
    String date;

    public Order() {
    }

    public Order(String id, String contactNo, String type, String desc, String location, String address, String date) {
        this.id = id;
        this.contactNo = contactNo;
        this.type = type;
        this.desc = desc;
        this.location = location;
        this.address = address;
        this.date=date;
    }

    public String getId() {
        return id;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }
    public String getDate(){
        return date;
    }
}
