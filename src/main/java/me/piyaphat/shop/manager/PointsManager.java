package me.piyaphat.shop.manager;

import java.util.HashMap;

public class PointsManager {
    public static HashMap<String, Integer> points = new HashMap<>(); // item and stock

    public static void init() {
        points.put("noat.", 1000);
    }

    public static boolean isUserValid(String user) {
        return points.containsKey(user);
    }

    public static boolean isSufficient(String user, int amount) {
        if (isUserValid(user)) {
            return points.get(user) >= amount;
        }
        return false;
    }

    public static boolean processPayment(String user, int amount) {
        if (!isSufficient(user, amount)) return false;
        points.put(user, points.get(user) - amount);
        return true;
    }

    public static int getPoints(String user) {
        return points.get(user);
    }

    public static boolean registerUser(String user) {
        if (isUserValid(user)) {
            return false;
        }
        points.put(user, 1000);
        return true;
    }
}
