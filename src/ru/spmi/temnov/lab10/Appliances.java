package ru.spmi.temnov.lab10;

abstract class Appliances{//суперкласс-товар
    protected String name;//названия фирмы-товара
    Appliances(String name){
        this.name = name;
    }
    public abstract void show();
    public String getName(){
        return name;
    }
}
