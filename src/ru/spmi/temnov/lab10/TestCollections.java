package ru.spmi.temnov.lab10;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TestCollections {
    private void provideInput(String data){
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void getTVTest(){
        TV tv = new TV("LG", 29);
        assertEquals("LG",tv.getName());
        assertEquals(29,tv.getScreen());
        System.out.println("Геттеры TV работают корректно\n");
    }
    @Test
    void getFridgeTest(){
        Fridge fr = new Fridge("LG", 4);
        assertEquals("LG",fr.getName());
        assertEquals(4,fr.getShelf_num());
        System.out.println("Геттеры Fridge работают корректно\n");
    }

    @Test
    void randGetRandomCompanyTest(){
        Main m = new Main();
        try {
            Method method = Main.class.getDeclaredMethod("find", String.class);
            method.setAccessible(true);
            assertTrue((Boolean)method.invoke(m, RandomGenerator.getRandomComp()));
            System.out.println("Случайное значение массива company работает корректно\n");
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void companyGetAllTest(){
        assertArrayEquals(new String[]{"LG", "Haier", "Sharp", "Samsung", "Bosch", "Siemens", "Hitachi"}, RandomGenerator.getAll());
        System.out.println("Получение массива фирм работает корректно\n");
    }

    @Test
    public void testFound1(){
        Main m = new Main();
        try {
            Method method = Main.class.getDeclaredMethod("find", String.class);
            method.setAccessible(true);
            assertEquals(true, method.invoke(m, "LG"));
            System.out.println("Товар фирмы LG существует\n");
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFound2(){
        Main m = new Main();
        try {
            Method method = Main.class.getDeclaredMethod("find", String.class);
            method.setAccessible(true);
            assertEquals(false, method.invoke(m, "Qwerty"));
            System.out.println("Товара фирмы lG не существует\n");
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFound3(){
        Main m = new Main();
        try {
            Method method = Main.class.getDeclaredMethod("find", String.class);
            method.setAccessible(true);
            assertEquals(false, method.invoke(m, ""));
            System.out.println("Товара фирмы без названия не существует\n");
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void inputTest1(){
        Main m = new Main();
        provideInput("Haier");
        try {
            Method method = Main.class.getDeclaredMethod("inputCompany", null);
            method.setAccessible(true);
            String output = (String) method.invoke(m);
            assertEquals("Haier", output);
            System.out.println("Ввод Haier подерживается\n");
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void inputTest2(){
        Main m = new Main();
        provideInput("");
        try {
            Method method = Main.class.getDeclaredMethod("inputCompany", null);
            method.setAccessible(true);
            String output = (String) method.invoke(m);
            assertNull(output);
            System.out.println("Ввод пустой строки подерживается\n");
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
  private HashMap<String, Integer> countFr(Main m){//для холодильников
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String st: RandomGenerator.getAll()){
            Integer num = 0;
            for (Fridge app: m.getFridge())
                if (app.getName().equals(st))
                    ++num;
            hashMap.put(st, num);
        }
        return hashMap;
    }
    private HashMap<String, Integer> countTV(Main m){//для телевизоров
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String st: RandomGenerator.getAll()){
            Integer num = 0;
            for (TV app: m.getTV())
                if (app.getName().equals(st))
                    ++num;
            hashMap.put(st, num);
        }
        return hashMap;
    }

    @Test
    public void inputMenuTest1(){
        provideInput("4");
        assertEquals(4, Menu.inputOption());
        System.out.println("Ввод пункта меню 4 поддерживатеся");
    }

    @Test
    public void inputMenuTest2(){
        provideInput("rtuyrt");
        assertEquals(-1, Menu.inputOption());
        System.out.println("Ввод пункта меню не в числовом виде не поддерживатеся");
    }

    @Test
    public void chooseAppTest(){
        provideInput("1");
        assertEquals(1, Menu.appChoose());
        System.out.println("Выбор товара типа 1 (холодильник) поддерживатеся");
    }

    @Test
    public void charInputTest(){
        provideInput("22");
        assertEquals(22, Menu.charInput("Введите число:\n"));
        System.out.println("Выбор числа 22 поддерживатеся");
    }


    @Test
    public void countTestNotRandom() throws RuntimeException {//для неслучайных значений
        System.out.println("Заданные значения!\n");
        Main m = new Main();
        try {
            ArrayList<TV> arrayListTV = new ArrayList<>();
            arrayListTV.add(new TV("Sharp", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("LG", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("Sharp", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("Siemens", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("Bosch", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("Bosch", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("Sharp", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("Haier", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("Hitachi", RandomGenerator.getRandomScreen()));
            arrayListTV.add(new TV("Haier", RandomGenerator.getRandomScreen()));
            m.setTV(arrayListTV);

            LinkedList<Fridge> linkedListFridge = new LinkedList<>();
            linkedListFridge.add(new Fridge("LG", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("Siemens", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("Siemens", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("LG", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("Haier", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("Sharp", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("LG", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("Bosch", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("LG", RandomGenerator.getRandomShelf()));
            linkedListFridge.add(new Fridge("Bosch", RandomGenerator.getRandomShelf()));
            m.setFridge(linkedListFridge);

            Method method = Main.class.getDeclaredMethod("printList");
            method.setAccessible(true);
            method.invoke(m);

            method = Main.class.getDeclaredMethod("count", String.class);
            method.setAccessible(true);

            int[] n = (int[]) method.invoke(m, "Haier");
            assertEquals(2, n[0]);
            System.out.printf("Количество телевизоров компании Haier равно %d\n", n[0]);
            assertEquals(1, n[1]);
            System.out.printf("Количество холодильников компании Haier равно %d\n", n[1]);
            n = (int[]) method.invoke(m, "LG");
            assertEquals(1, n[0]);
            System.out.printf("Количество телевизоров компании LG равно %d\n", n[0]);
            assertEquals(4, n[1]);
            System.out.printf("Количество холодильников компани LG равно %d\n", n[1]);
            n = (int[]) method.invoke(m, "Sharp");
            assertEquals(3, n[0]);
            System.out.printf("Количество телевизоров компании Sharp равно %d\n", n[0]);
            assertEquals(1, n[1]);
            System.out.printf("Количество холодильников компани Sharp равно %d\n", n[1]);
            n = (int[]) method.invoke(m, "Samsung");
            assertEquals(0, n[0]);
            System.out.printf("Количество телевизоров компании Samsung равно %d\n", n[0]);
            assertEquals(0, n[1]);
            System.out.printf("Количество холодильников компани Samsung равно %d\n", n[1]);
            n = (int[]) method.invoke(m, "Siemens");
            assertEquals(1, n[0]);
            System.out.printf("Количество телевизоров компании Siemens равно %d\n", n[0]);
            assertEquals(2, n[1]);
            System.out.printf("Количество холодильников компани Siemens равно %d\n", n[1]);
            n = (int[]) method.invoke(m, "Bosch");
            assertEquals(2, n[0]);
            System.out.printf("Количество телевизоров компании Bosch равно %d\n", n[0]);
            assertEquals(2, n[1]);
            System.out.printf("Количество холодильников компани Bosch равно %d\n", n[1]);
            n = (int[]) method.invoke(m, "Hitachi");
            assertEquals(1, n[0]);
            System.out.printf("Количество телевизоров компании Hitachi равно %d\n", n[0]);
            assertEquals(0, n[1]);
            System.out.printf("Количество холодильников компани Hitachi равно %d\n\n", n[1]);
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void countTest() throws RuntimeException {//для случайных значений
        System.out.println("Случайные значения!\n");
        Main m = new Main();
        try {
            Method method = Main.class.getDeclaredMethod("printList");
            method.setAccessible(true);
            method.invoke(m);

            HashMap<String, Integer> numFridge= countFr(m);
            HashMap<String, Integer> numTV= countTV(m);
            method = Main.class.getDeclaredMethod("count", String.class);
            method.setAccessible(true);
            for (String st: RandomGenerator.getAll()){
                int[] n = (int[]) method.invoke(m, st);
                assertEquals(numTV.get(st), n[0]);
                assertEquals(numFridge.get(st), n[1]);
                System.out.printf("Количество телевизоров компании %s равно %d\n", st, n[0]);
                System.out.printf("Количество холодильников компании %s равно %d\n", st, n[1]);
            }
            System.out.println();
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
