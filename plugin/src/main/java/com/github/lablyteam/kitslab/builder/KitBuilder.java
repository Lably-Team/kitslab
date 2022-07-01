package com.github.lablyteam.kitslab.builder;

import com.github.lablyteam.kitslab.api.Kits;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.github.lablyteam.kitslab.utils.TextUtils.toLegacy;

public class KitBuilder {

    private final Kits kit;

    public KitBuilder(String kitId) {
        this.kit = new Kits(null);
    }

    public static KitBuilder create(String kitId) {
        return new KitBuilder(kitId);
    }

    public KitBuilder name(String kitName) {
        kit.setKitName(toLegacy(kitName));
        return this;
    }

    public KitBuilder setChar(char kitChar) {
        kit.setKitChar(kitChar);
        return this;
    }

    public KitBuilder permission(String kitPermission) {
        kit.setKitPermission(kitPermission);
        return this;
    }

    public KitBuilder bought(boolean bought) {
        kit.setBought(bought);
        return this;
    }

    public KitBuilder value(double kitValue) {
        kit.setKitValue(kitValue);
        return this;
    }

    public KitBuilder content(List<ItemStack> kitContent) {
        kit.setKitContent(kitContent);
        return this;
    }

    public KitBuilder equipment(List<ItemStack> equipment) {
        kit.setEquipment(equipment);
        return this;
    }

    public KitBuilder cooldown(long cooldown) {
        kit.setCooldown(cooldown);
        return this;
    }

    public Kits build() {
        return kit;
    }

}
