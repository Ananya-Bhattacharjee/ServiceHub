package com.example.user.servicehub;

/**
 * Created by User on 5/5/2017.
 */

public class UserPhoto {
    String id;
    String name;
    String photo;

    public UserPhoto() {
    }

    public UserPhoto(String id,String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}
