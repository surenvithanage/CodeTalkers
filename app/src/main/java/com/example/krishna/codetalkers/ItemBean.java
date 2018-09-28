package com.example.krishna.codetalkers;

public class ItemBean {
    int id;
    String name, section, joiningdate;
    double price;

    public ItemBean(int id, String name, String section, String joiningdate, double price) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.joiningdate = joiningdate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public double getPrice() {
        return price;
    }
}
