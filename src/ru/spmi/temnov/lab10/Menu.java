package ru.spmi.temnov.lab10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class Menu {//класс меню
    Main m;
    Menu(){//апуск программы
        m = new Main();
        System.out.println("Добро пожаловать! В наличии есть следующие товары (холодильники и телевизоры):\n");
        menuController();
    }
    private void printOptions() {//опции меню
        System.out.println("Выберете действие: ");
        System.out.println("1 - создание списка объектов и вывод всех объектов на экран");
        System.out.println("2 - поиск элемента в списке");
        System.out.println("3 - удаление элемента из списка");
        System.out.println("4 - вывод числа товаров заданной фирмы");
        System.out.print("0 - выход из программы\n>>");
    }
    public static int inputOption(){//ввод опции меню
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String op = null;

        try {
            op = br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка ввода! " + e);
        }

        int a = -1;

        try{
           a = Integer.parseInt(op);
        }catch(NumberFormatException e){
            System.out.println("Это не число! " + e);
        }

        return a;
    }

    public static int listChoose(){//неизвестно как сделать тест. выбор товара для поиска/удалления
        int menuOption = -1;

        do{
            System.out.println("В каком списке искать? 1 - ArrayList, 2 - LinkedList");
            menuOption = inputOption();
            if (menuOption != 1 && menuOption != 2)
                System.out.println("Неверный ввод!");
        }while(menuOption != 1 && menuOption != 2);

        return menuOption;
    }
    public static int appChoose(){//неизвестно как сделать тест. выбор товара для поиска/удалления
        int menuOption = -1;

        do{
            System.out.println("Какой товар искать? 1 - Холодильник, 2 - Телевизор: ");
            menuOption = inputOption();
            if (menuOption != 1 && menuOption != 2)
                System.out.println("Неверный ввод!");
        }while(menuOption != 1 && menuOption != 2);

        return menuOption;
    }
    public static int charInput(String msg){//неизвестно как сделать тест. выбор характеристики для поиска/удалления
        int feature = -1;

        do{
            System.out.print(msg);
            feature = inputOption();
            if (feature <= 0)
                System.out.println("Неверное число!");
        }while(feature <= 0);

        return feature;
    }
    private void selectFrom(){//поиск и списках
        boolean list = (listChoose() == 1) ? true : false;
        switch (appChoose()){

            case 1 -> {
                String needed = m.menu("Введите название фирмы холодильника: ");
                int feature = charInput("Введите число полок холодильника: ");
                Fridge fr = new Fridge(needed, feature);
                int found = m.selectFridge(fr, list,false);
                if (found > -1)
                    System.out.printf("Найден холодильник фирмы %s с числом полок %d на позиции %d\n", needed, feature, found);
                else
                    System.out.println("Холодильник не найден");
            }

            case 2 -> {
                String needed = m.menu("Введите название фирмы телевизора: ");
                int feature = charInput("Введите диагональ телевизора: ");
                TV tv = new TV(needed, feature);
                int found = m.selectTV(tv, list,false);
                if (found > -1)
                    System.out.printf("Найден телевизор фирмы %s с диагональю %d на позиции %d\n", needed, feature, found);
                else
                    System.out.println("Телевизор не найден");
            }
            default -> System.out.println("Неверная опция!");
        }

    }
    private void deleteFrom(){//удаления из списка
        boolean list = (listChoose() == 1) ? true : false;
        switch (appChoose()){

            case 1 -> {
                String needed = m.menu("Введите название фирмы холодильника: ");
                int feature = charInput("Введите число полок холодильника: ");
                Fridge fr = new Fridge(needed, feature);
                int found = m.selectFridge(fr, list, true);
                if (found > -1)
                    System.out.printf("Удален холодильник фирмы %s с числом полок %d на позиции %d\n", needed, feature, found);

                else
                    System.out.println("Холодильник не найден");
            }

            case 2 -> {
                String needed = m.menu("Введите название фирмы телевизора: ");
                int feature = charInput("Введите диагональ телевизора: ");
                TV tv = new TV(needed, feature);
                int found = m.selectTV(tv, list, true);
                if (found > -1)
                    System.out.printf("Найден телевизор фирмы %s с диагональю %d на позиции %d\n", needed, feature, found);
                else
                    System.out.println("Телевизор не найден");
            }
            default -> System.out.println("Неверная опция!");
        }

    }
    private void count(){//опция меню расчета количества товаров
        String optionMenu = "Введите название фирмы, количество товаров которой хотите узнать {";
        StringJoiner sj = new StringJoiner(", ");
        for (Company comp: Company.values()){
            sj.add(comp.getName());
        }
        optionMenu += sj + "} :";
        String needed = m.menu(optionMenu);
        int[] nums = m.count(needed);
        System.out.printf("Товаров заданной фирмы в arrayList = %d\n", nums[0]);
        System.out.printf("Товаров заданной фирмы в LinkedList = %d\n", nums[1]);
        }
    private void menuController(){//контроллер меню
        int menuOption = -1;
        do{
            printOptions();
            menuOption = inputOption();
            chooseOption(menuOption);
        }while(menuOption != 0);
    }
    private void chooseOption(int option){//реализация опции меню
        switch(option){
            case -1 -> System.out.println("Ошибка ввода!");
            case 0 -> System.out.println("Спасибо за пользование программой!");
            case 1 -> m.printList();
            case 2 -> selectFrom();
            case 3 -> deleteFrom();
            case 4 -> count();
            default -> System.out.println("Такой команды не существует");
        }
    }

}
