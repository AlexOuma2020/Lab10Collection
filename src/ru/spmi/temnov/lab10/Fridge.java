package ru.spmi.temnov.lab10;

final class Fridge extends Appliances{//наследник холодильник
    private final int shelf_num;//число полок
    Fridge(String name, int shelf_num) {
        super(name);
        this.shelf_num = shelf_num;
    }

    @Override
    public String toString() {
        return "Холодильник фирмы " + name + ". Число полок = " + shelf_num + '\n';
    }

    public int getShelf_num() {
        return shelf_num;
    }

}
