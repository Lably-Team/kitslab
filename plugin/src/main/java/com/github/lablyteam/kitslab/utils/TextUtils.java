package com.github.lablyteam.kitslab.utils;

import org.bukkit.ChatColor;

public class TextUtils {

    public static String toLegacy(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
