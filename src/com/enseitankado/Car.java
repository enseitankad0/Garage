package com.enseitankado;

import java.io.Serializable;

public class Car implements Serializable {

    private int number;
    private String model;
    private String color;

    public Car(int number, String model, String color) {
        this.number = number;
        this.model = model;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return number + ": " + model + ": " + color;
    }
}
