package com.github.goodfatcat;

public class Airport<V>{
    private final V value;
    private final int id;

    public Airport(V value, int id) {
        this.value = value;
        this.id = id;
    }

    public V getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
