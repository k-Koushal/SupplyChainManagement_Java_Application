package com.majorproject.supplychain;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    //id, name,price
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleDoubleProperty price;

    public Product(int id, String name, Double price){
        this.id=new SimpleIntegerProperty(id);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleDoubleProperty(price);
    }

    //getter methods:
    public int getId(){return id.get();}
    public String getName(){return name.get();}
    public Double getPrice(){return price.get();}



}
