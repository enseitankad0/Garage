package com.enseitankado;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Garage implements Map<Integer, Car> {

    HashMap<Integer, Car> garage = new HashMap<>();


    public String printGarage() {
        for (Car car : garage.values()) {
            return car.toString();
        }
        return null;
    }

    @Override
    public int size() {
        return garage.size();
    }

    @Override
    public boolean isEmpty() {
        return garage.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return garage.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return garage.containsValue(value);
    }

    @Override
    public Car get(Object key) {
        return garage.get(key);
    }

    @Override
    public Car put(Integer key, Car value) {
        return garage.put(key, value);
    }

    @Override
    public Car remove(Object key) {
        return garage.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Car> m) {

    }

    @Override
    public void clear() {
        garage.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return garage.keySet();
    }

    @Override
    public Collection<Car> values() {
        return garage.values();
    }

    @Override
    public Set<Entry<Integer, Car>> entrySet() {
        return null;
    }


}


