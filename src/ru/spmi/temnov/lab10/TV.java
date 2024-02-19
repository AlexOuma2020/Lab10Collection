package ru.spmi.temnov.lab10;

final class TV extends Appliances{//наследник телевизор
    private final int screen;//диагональ экрана
    TV(String name, int screen){
        super(name);
        this.screen = screen;
    }

    @Override
    public void show(){//переопределение метода вывода информации
        System.out.printf("Телевиор компании %s с диагональю %d''\n", name, screen);
    }

    public int getScreen(){
        return screen;
    }


}
