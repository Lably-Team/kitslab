package com.github.lablyteam.kitslab.managers;

import com.github.lablyteam.kitslab.KitsLab;
import com.github.lablyteam.kitslab.api.Kits;
import com.github.lablyteam.kitslab.api.ManagerLoader;
import com.github.lablyteam.kitslab.builder.ItemBuilder;
import com.github.lablyteam.kitslab.builder.KitBuilder;
import com.github.lablyteam.kitslab.configuration.YamlFile;
import org.bukkit.inventory.ItemStack;

import javax.inject.Inject;
import java.util.*;

public class KitManager implements ManagerLoader {

    @Inject
    private KitsLab plugin;
    private static final String KIT_PATH = "kit.";
    private final Map<String, Kits> mapKits = new HashMap<>();


    @Override
    public void start() {
        loadKits();

    }

    private void loadKits() {
        KitsFilesManager filesManager = plugin.getKitsFilesManager();

        for(String kitId : filesManager.getKitsNames()) {
            plugin.getLogger().info("Loading kit " + kitId);
            YamlFile kitFile = filesManager.getFile(kitId);
            mapKits.put(
                    kitId,
                    KitBuilder.create(kitId)
                            .name(kitFile.getString(KIT_PATH + "display-kit"))
                            .permission(kitFile.getString(KIT_PATH + "permission-kit"))
                            .bought(kitFile.getBoolean(KIT_PATH + "enable-kit"))
                            .value(kitFile.getDouble(KIT_PATH + "price"))
                            .setChar(kitFile.getString(KIT_PATH + "char").charAt(0))
                            .content(getKitContent(kitFile))
                            .equipment(getKitEquipment(kitFile))
                            .cooldown(kitFile.getLong(KIT_PATH + "kit-countdown"))
                            .build()
            );
        }
    }

    private List<ItemStack> getKitContent(YamlFile kitFile) {
        List<ItemStack> kitContent = new ArrayList<>();

        // TODO: analyze , no jump more items in configuration section
        int contents = kitFile.getConfigurationSection(KIT_PATH + "content")
                .getKeys(false).size();

        for (int i = 0; i < contents; i++) {

            if (!kitFile.contains(KIT_PATH + "content." + i)) {
                continue;
            }

            kitContent.add(
                    ItemBuilder.of(kitFile.getMaterial(KIT_PATH + "content." + i + ".material-item"))
                            .name(kitFile.getString(KIT_PATH + "content." + i + ".display-name"))
                            .lore(kitFile.getStringList(KIT_PATH + "content." + i + ".lore-item"))
                            .build()
            );
        }


        return kitContent;
    }

    private List<ItemStack> getKitEquipment(YamlFile kitFile) {
        List<ItemStack> kitEquipment = new ArrayList<>();





        return kitEquipment;
    }

    public void reloadKits() {
        mapKits.clear();
        loadKits();
    }

    public List<String> getKitList() {
        List<String> kitList = new ArrayList<>();
        mapKits.forEach((key, value) -> kitList.add(key));
        return kitList;
    }

    public Kits getKit(String name) {
        return mapKits.get(name);
    }

    @Override
    public void stop() {

    }
}
