package ru.spmi.temnov.lab10;

final class Fridge extends Appliances{//наследник холодильник
    private final int shelf_num;//число полок
    Fridge(String name, int shelf_num) {
        super(name);
        this.shelf_num = shelf_num;
    }

    @Override
    public void show() {//переопределение метода вывода информации
        System.out.printf("Холодильник компании %s с %d полками внутри\n", name, shelf_num);
    }

    public int getShelf_num() {
        return shelf_num;
    }

}
