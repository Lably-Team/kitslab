package com.github.lablyteam.kitslab.api;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Kits {

    private final String kitId;
    private char kitChar;
    private String kitName;
    private String kitPermission;
    private boolean bought;
    private double kitValue;
    private List<ItemStack> kitContent;
    private List<ItemStack> equipment;
    private long cooldown;

    public Kits(@NotNull String kitId) {
        this.kitId = kitId;
    }

    public String getKitId() {
        return kitId;
    }

    public String getKitName() {
        return kitName;
    }

    public char getKitChar() {
        return kitChar;
    }

    public void setKitChar(char kitChar) {
        this.kitChar = kitChar;
    }

    public void setKitName(String kitName) {
        this.kitName = kitName;
    }

    public String getKitPermission() {
        return kitPermission;
    }

    public void setKitPermission(String kitPermission) {
        this.kitPermission = kitPermission;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public double getKitValue() {
        return kitValue;
    }

    public void setKitValue(double kitValue) {
        this.kitValue = kitValue;
    }

    public List<ItemStack> getKitContent() {
        return kitContent;
    }

    public void setKitContent(List<ItemStack> kitContent) {
        this.kitContent = kitContent;
    }

    public List<ItemStack> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<ItemStack> equipment) {
        this.equipment = equipment;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }
}
