package me.piyaphat;

import me.piyaphat.listeners.CommandListener;
import me.piyaphat.listeners.ReadyListener;
import me.piyaphat.shop.manager.ItemsManager;
import me.piyaphat.shop.manager.PointsManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class Main {

    public static String TOKEN = "----";

    public static void main(String[] args) throws InterruptedException {
        ItemsManager.init();
        PointsManager.init();
        JDA jda = JDABuilder.createDefault(TOKEN).enableIntents(EnumSet.allOf(GatewayIntent.class)).build();
        jda.addEventListener(new ReadyListener());
        jda.addEventListener(new CommandListener());
        jda.updateCommands().addCommands(
                Commands.slash("items", "View all the items"),
                Commands.slash("buy", "Buy an item").addOption(OptionType.STRING, "item", "The item to buy", true),
                Commands.slash("viewpoints", "View your points"),
                Commands.slash("register", "Register your account")
        ).queue();
        jda.awaitReady();
    }
}