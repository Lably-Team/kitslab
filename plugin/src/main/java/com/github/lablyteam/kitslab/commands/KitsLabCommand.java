package com.github.lablyteam.kitslab.commands;

import com.github.lablyteam.kitslab.KitsLab;
import com.github.lablyteam.kitslab.configuration.YamlFile;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Logger;

public class KitsLabCommand implements CommandExecutor {

    @Inject
    private KitsLab plugin;

    @Inject @Named("messages")
    private YamlFile messages;

    @Inject @Named("config")
    private YamlFile config;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logger logger = plugin.getLogger();
        if (!(sender instanceof Player)) {
            if (args.length <= 0) {
                messages.getStringList("help-messages.console-message").forEach(sender::sendMessage);
                return true;
            }

            switch(args[0].toLowerCase()) {
                case "reload":
                    sender.sendMessage(messages.getString("information.plugin-reloaded", true));
                    messages.reload();
                    config.reload();
                    return true;
                case "give":
                    sender.sendMessage(messages.getString("information.plugin-reloaded", true));
                    return true;
                default:
                    messages.getStringList("help-messages.console-message").forEach(sender::sendMessage);
                    return true;
            }
        }

        Player player = (Player) sender;
        switch(args[0].toLowerCase()) {
            case "a":
                PlayerInventory inventory = player.getInventory();

                ItemStack itemStack = new ItemStack(Material.APPLE);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(null);
                inventory.addItem(itemStack);
                player.sendMessage("Whosh!!!");

                return true;
            case "reload":
                sender.sendMessage(messages.getString("information.plugin-reloaded", true));
                messages.reload();
                config.reload();
                return true;
            case "give":
                sender.sendMessage(messages.getString("information.plugin-reloaded", true));
                return true;
            default:
                messages.getStringList("help-messages.console-message").forEach(sender::sendMessage);
                return true;
        }
    }
}
