package com.example.user.servicehub;

/**
 * Created by User on 6/26/2017.
 */

public class TechRate {
    String id;
    Tech tech;
    String n;
    String total;

    public TechRate() {
    }

    public TechRate(String id, Tech tech, String n, String total) {
        this.id = id;
        this.tech = tech;
        this.n = n;
        this.total = total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Tech getTech() {
        return tech;
    }

    public String getN() {
        return n;
    }

    public String getTotal() {
        return total;
    }

    public void setTech(Tech tech) {
        this.tech = tech;
    }

    public void setN(String n) {
        this.n = n;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
