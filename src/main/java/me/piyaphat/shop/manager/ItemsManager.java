package me.piyaphat.shop.manager;

import me.piyaphat.shop.item.Item;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.HashMap;

public class ItemsManager {

    public static HashMap<Item, Integer> items = new HashMap<>(); // item and stock

    public static void init() {
        items.put(new Item("pen", "pen from somewhere", 300), 100);
        items.put(new Item("bag", "bag from ipst", 1000), 20);
    }

    public static MessageEmbed getItems() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Items");
        for (Item item : items.keySet()) {
            embed.addField(item.name, item.description, true);
            embed.addField("Price", String.valueOf(item.price), true);
            embed.addField("Stock", String.valueOf(items.get(item)), true);
        }
        return embed.build();
    }

    public static Item isItemValid(String name) {
        for (Item item : items.keySet()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

    public static int getPrice(String name) {
        if (isItemValid(name) == null) return -1;
        return isItemValid(name).price;
    }

    public static String processBuy(String name, String user) {
        if (isItemValid(name) != null) {
            if (PointsManager.processPayment(user, getPrice(name)))  {
                items.put(isItemValid(name), items.get(isItemValid(name)) - 1);
                return "Successfully bought " + name + "!";
            }
            return "Buying failed due to insufficient points!";
        }
        return "Buying failed due to the item not being found!";
    }
}
