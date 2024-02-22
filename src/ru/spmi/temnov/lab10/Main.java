package ru.spmi.temnov.lab10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main{//основной класс
    private List<Appliances> arrayList = new ArrayList<>();//массивный список
    private List<Appliances> list = new LinkedList<>();//связный список
    Main(){
        for (int i = 0; i < 50; ++i) {
            arrayList.add(new TV(RandomGenerator.getRandomComp(), RandomGenerator.getRandomScreen()));
            arrayList.add(new Fridge(RandomGenerator.getRandomComp(), RandomGenerator.getRandomShelf()));
            list.add(new Fridge(RandomGenerator.getRandomComp(), RandomGenerator.getRandomShelf()));
            list.add(new TV(RandomGenerator.getRandomComp(), RandomGenerator.getRandomScreen()));

        }
    }
    public void setArr(ArrayList<Appliances> arr){
        arrayList = arr;
    }

    public void setList(LinkedList<Appliances> arr){
        list = arr;
    }
    public void printList(){
        Iterator<Appliances> arrIter = arrayList.iterator();
        Iterator<Appliances> lIter = list.iterator();
        System.out.println("\n\nArrayList: ");
        while(arrIter.hasNext())
            System.out.println(arrIter.next());
        System.out.println("\n\n\nLinkedList: ");
        while(lIter.hasNext())
            System.out.println(lIter.next());
        System.out.println();
    }
    private static boolean find(String inp){
        for (Company comp: Company.values()){
            if (comp.getName().equals(inp))
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
    public int selectFridge(Fridge fr, boolean lst, boolean removed){//выбор телевизора для удаления или поиска
        Iterator<Appliances> iter;
        if(lst)
             iter = arrayList.iterator();
        else
            iter = list.iterator();
        int ind = -1, i = -1;
        while(iter.hasNext()){
            i++;
            Appliances current =  iter.next();
            if (current instanceof Fridge)
            {
                Fridge curr = (Fridge) current;
                if (curr.getName().equals(fr.getName()) && curr.getShelf_num() == fr.getShelf_num()){
                    ind = i;
                    if(removed)
                        iter.remove();
                    break;
                }
            }

        }
        return ind;
    }

    public int selectTV(TV tv, boolean lst, boolean removed){//выбор телевизора для удаления или поиска
        Iterator<Appliances> iter;
        if(lst)
            iter = arrayList.iterator();
        else
            iter = list.iterator();
        int ind = -1, i = -1;
        while(iter.hasNext()){
            i++;
            Appliances current =  iter.next();
            if (current instanceof TV)
            {
                TV curr = (TV) current;
                if (curr.getName().equals(tv.getName()) && curr.getScreen() == tv.getScreen()){
                    ind = i;
                    if(removed)
                        iter.remove();
                    break;
                }
            }

        }
        return ind;
    }
    public int[] count(String needed){//метод расчета ВП
        int[] nums = new int[2];
        long startTimeT = System.nanoTime();
        for (Appliances app: arrayList){
            if (app.getName().equals(needed))
                ++nums[0];
        }
        long endTimeT = System.nanoTime();
        long startTimeF = System.nanoTime();
        for (Appliances app: list){
            if (app.getName().equals(needed))
                ++nums[1];
        }
        long endTimeF = System.nanoTime();
        long dur1 = endTimeT - startTimeT;
        long dur2 = endTimeF - startTimeF;
        System.out.printf("Время на выполнение задания: ArrayList = %d ... LinkedList = %d\n", dur1, dur2);
        System.out.printf("Длина ArrayList = %d ... длина LinkedList = %d\n", arrayList.size(), list.size());
        return nums;
    }
    public List<Appliances> getArr(){
        return arrayList;
    }
    public List<Appliances> getList(){
        return list;
    }

    public static void main(String[] args) {
        new Menu();
    }
}
