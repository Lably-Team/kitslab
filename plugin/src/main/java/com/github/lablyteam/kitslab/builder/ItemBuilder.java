package com.github.lablyteam.kitslab.builder;

import com.github.lablyteam.kitslab.utils.TextUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.github.lablyteam.kitslab.utils.TextUtils.toLegacy;

public class ItemBuilder {

    private ItemStack item;
    private final ItemMeta meta;

    private ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public static ItemBuilder of(Material material) {
        return new ItemBuilder(material);
    }

    public ItemBuilder name(String name) {
        this.meta.setDisplayName(toLegacy(name));
        return this;
    }

    public ItemBuilder lore(String... lore) {
        List<String> itemLore = new ArrayList<>();
        for (String line : lore) {
            itemLore.add(toLegacy(line));
        }
        this.meta.setLore(itemLore);
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        lore.replaceAll(TextUtils::toLegacy);
        this.meta.setLore(lore);
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder durability(short durability) {
        this.item.setDurability(durability);
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        this.meta.spigot().setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder enchant(String enchantmentPath) {
        String[] enchantmentParts = enchantmentPath.split(":");
        if (enchantmentParts.length < 2) {
            throw new IllegalArgumentException("Enchantment path must be in format 'enchantment:level'");
        }

        if (Integer.parseInt(enchantmentParts[1]) < 1) {
            throw new IllegalArgumentException("Enchantment level must be greater than 0");
        }

        if (Enchantment.getByName(enchantmentParts[0]) == null) {
            throw new IllegalArgumentException("Enchantment '" + enchantmentParts[0] + "' does not exist");
        }

        this.meta.addEnchant(
                Enchantment.getByName(enchantmentParts[0].toUpperCase()),
                Integer.parseInt(enchantmentParts[1]),
                true
        );
        return this;
    }


    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
