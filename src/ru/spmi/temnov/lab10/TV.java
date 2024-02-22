package ru.spmi.temnov.lab10;

final class TV extends Appliances{//наследник телевизор
    private final int screen;//диагональ экрана
    TV(String name, int screen){
        super(name);
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Телевизор фирмы " + name + " с диагональю = " + screen + '\n';
    }

    public int getScreen(){
        return screen;
    }


}
