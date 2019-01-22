package com.example.user.servicehub;

import java.util.Date;

/**
 * Created by User on 6/26/2017.
 */

public class custRateTech {
    String id;
    String contactNo;
    Tech tech;
    String comment;
    Date date;

    public custRateTech() {
    }

    public custRateTech(String id, String contactNo, Tech tech, String comment, Date date) {
        this.id = id;
        this.contactNo = contactNo;
        this.tech = tech;
        this.comment = comment;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getContactNo() {
        return contactNo;
    }

    public Tech getTech() {
        return tech;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
}
