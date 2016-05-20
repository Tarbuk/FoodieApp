package com.example.thomas.foodie;

/**
 * Created by Thomas on 17/05/2016.
 */
public class Restaurant {
    private int id;
    private String name;
    private String type;

    public Restaurant(){
        super();
    }

    public Restaurant(int id, String name, String type) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.name + " [$" + this.type + "]";
    }
}