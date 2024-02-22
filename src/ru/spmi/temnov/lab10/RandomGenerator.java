package ru.spmi.temnov.lab10;

public class RandomGenerator {
    public static String getRandomComp() {
        Company[] company = Company.values();
        return company[(int) (Math.random() * company.length)].getName();
    }

    public static int getRandomScreen() {
        return (int) (Math.random() * 18) + 22;
    }

    public static int getRandomShelf() {
        return (int) (Math.random() * 4) + 3;
    }
}
