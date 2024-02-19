package ru.spmi.temnov.lab10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main{//основной класс
    private List<TV> tv = new ArrayList<>();//массивный список телевизоров
    private List<Fridge> fridge = new LinkedList<>();//связный список телевизоров
    Main(){
        for (int i = 0; i < 100; ++i) {
            tv.add(new TV(RandomGenerator.getRandomComp(), RandomGenerator.getRandomScreen()));
            fridge.add(new Fridge(RandomGenerator.getRandomComp(), RandomGenerator.getRandomShelf()));
        }
    }
    public void setTV(ArrayList<TV> arr){
        tv = arr;
    }

    public void setFridge(LinkedList<Fridge> arr){
        fridge = arr;
    }
    public void printList(){
        Iterator<TV> titer = tv.iterator();
        Iterator<Fridge> fiter = fridge.iterator();
        System.out.println("\n\nСписок холодильников: ");
        while(titer.hasNext())
            titer.next().show();
        System.out.println("\n\n\n\nСписок холодильников: ");
        while(fiter.hasNext())
            fiter.next().show();
        System.out.println();
    }
    private static boolean find(String inp){
        for (String comp: RandomGenerator.getAll()){
            if (comp.equals(inp))
                return true;
        }
        return false;
    }
    private String inputCompany() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
    public String menu(String msg) {//без понятия как сделать тест
        String needed = null;
        boolean excep;
        do {
            excep = true;
            System.out.print(msg);
            try {
                needed = inputCompany();
            } catch (IOException e) {
                excep = false;
                System.out.println("Неверный формат ввода!");
            }
            if (!find(needed)) {
                System.out.println("Несуществующая фирма " + needed);
                excep = false;
            }
        } while (!excep);
        return needed;
    }
    public int selectFridge(Fridge fr, boolean removed){//выбор телевизора для удаления или поиска
        Iterator<Fridge> fiter = fridge.iterator();
        int ind = -1, i = -1;
        while(fiter.hasNext()){
            i++;
            Fridge current =  fiter.next();
            if (current.getName().equals(fr.getName()) && current.getShelf_num() == fr.getShelf_num()){
                ind = i;
                if(removed)
                    fiter.remove();
                break;
            }
        }
        return ind;
    }

    public int selectTV(TV tvs, boolean removed){//выбор телевизора для удаления или поиска
        Iterator<TV> titer = tv.iterator();
        int ind = -1, i = -1;
        while(titer.hasNext()){
            i++;
            TV current =  titer.next();
            if (current.getName().equals(tvs.getName()) && current.getScreen() == tvs.getScreen()){
                ind = i;
                if(removed)
                    titer.remove();
                break;
            }
        }
        return ind;
    }
    public int[] count(String needed){//метод расчета ВП
        int[] nums = new int[2];
        long startTimeT = System.nanoTime();
        for (TV app: tv){
            if (app.getName().equals(needed))
                ++nums[0];
        }
        long endTimeT = System.nanoTime();
        long startTimeF = System.nanoTime();
        for (Fridge app: fridge){
            if (app.getName().equals(needed))
                ++nums[1];
        }
        long endTimeF = System.nanoTime();
        long dur1 = endTimeT - startTimeT;
        long dur2 = endTimeF - startTimeF;
        System.out.printf("Время на выполнение задания: ArrayList = %d ... LinkedList = %d\n", dur1, dur2);
        System.out.printf("Телевизоров = %d ... Холодильников = %d\n", tv.size(), fridge.size());
        return nums;
    }
    public List<TV>  getTV(){
        return tv;
    }
    public List<Fridge>  getFridge(){
        return fridge;
    }

    public static void main(String[] args) {
        Menu m = new Menu();

    }
}
