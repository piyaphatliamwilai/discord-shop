package me.piyaphat.listeners;

import me.piyaphat.shop.manager.ItemsManager;
import me.piyaphat.shop.manager.PointsManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("register")) {
            boolean status = PointsManager.registerUser(event.getUser().getId());
            System.out.println(event.getUser().getId() + " registered!");
            if (status) {
                event.reply("You have been registered!").setEphemeral(true).queue();
            } else {
                event.reply("You have already been registered!").setEphemeral(true).queue();
            }
        } else if (event.getName().equals("buy")) {
            String item = event.getOption("item").getAsString();
            event.reply(ItemsManager.processBuy(item, event.getUser().getId())).setEphemeral(true).queue();
        } else if (event.getName().equals("viewpoints")) {
            event.reply("You currently have " + PointsManager.getPoints(event.getUser().getId()) + " points!").setEphemeral(true).queue();
        } else if (event.getName().equals("items")) {
            event.replyEmbeds(ItemsManager.getItems()).setEphemeral(true).queue();
        } else {
            event.reply("Unknown command!").setEphemeral(true).queue();
        }
    }
}
