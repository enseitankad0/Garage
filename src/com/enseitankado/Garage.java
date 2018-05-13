package com.enseitankado;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;


public class Garage {

    HashMap<Integer, Car> garage = new HashMap<>();

    public Car put(Integer key, Car value) {
        return garage.put(key, value);
    }

    public Collection<Car> values() {
        return garage.values();
    }



}


