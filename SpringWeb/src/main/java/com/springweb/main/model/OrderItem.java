package com.springweb.main.model;

public class OrderItem {

    private String type;
    private String name;
    private double price;
    private int count;

    public OrderItem(String type, String name, double price, int count) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
