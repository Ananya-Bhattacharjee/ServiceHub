package com.example.user.servicehub;

/**
 * Created by User on 5/28/2017.
 */

public class TakenOrder {
    String id;
    String contactNo;
    String type;
    String desc;
    String location;
    String address;
    String date;
    Tech tech;
    boolean done;
    boolean paid;

    public TakenOrder() {
    }


    public TakenOrder(String id, String contactNo, String type, String desc, String location, String address, String date, Tech tech, boolean done, boolean paid) {
        this.id = id;
        this.contactNo = contactNo;
        this.type = type;
        this.desc = desc;
        this.location = location;
        this.address = address;
        this.date = date;
        this.tech = tech;
        this.done = done;
        this.paid = paid;
    }

    public TakenOrder(String id, String contactNo, String type, String desc, String location, String address, String date, Tech tech) {
        this.id = id;
        this.contactNo = contactNo;
        this.type = type;
        this.desc = desc;
        this.location = location;
        this.address = address;
        this.date = date;
        this.tech = tech;
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

    public String getDate() {
        return date;
    }

    public Tech getTech() {
        return tech;
    }

    public boolean isDone() {
        return done;
    }

    public boolean isPaid() {
        return paid;
    }
}
