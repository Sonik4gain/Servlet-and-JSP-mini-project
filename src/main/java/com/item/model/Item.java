package com.item.model;

public class Item {
    private int id;
    private String name;
    private double price;
    private int totalNumber;
    private String description; 

    public Item(String name, double price, int totalNumber) {
        this.name = name;
        this.price = price;
        this.totalNumber = totalNumber;
    }

    public Item(int id, String name, double price, int totalNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalNumber = totalNumber;
    }

    public Item(int id, String name, double price, int totalNumber, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalNumber = totalNumber;
        this.description = description;
    }

    // Getters and Setters 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
