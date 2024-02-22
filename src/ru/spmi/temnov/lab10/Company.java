package ru.spmi.temnov.lab10;

public enum Company {
    LG("LG"),
    HAIER("Haier"),
    SHARP("Sharp"),
    SAMSUNG("Samsung"),
    SIEMENS("Siemens"),
    HITACHI("Hitachi");

    Company(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    private String name;
}
