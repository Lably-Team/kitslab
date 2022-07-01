package com.github.lablyteam.kitslab.managers;

import com.github.lablyteam.kitslab.KitsLab;
import com.github.lablyteam.kitslab.api.ManagerLoader;
import com.github.lablyteam.kitslab.configuration.YamlFile;
import org.bukkit.Bukkit;

import javax.inject.Inject;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FilesManager implements ManagerLoader {

    @Inject
    private KitsLab plugin;

    private static final Logger LOGGER = Bukkit.getLogger();

    private File[] files;
    private final Map<String, YamlFile> filesMap = new HashMap<>();

    @Override
    public void start() {
        scanFiles();
        loadFiles();
    }

    private void scanFiles() {
        File kitsFolder = new File(plugin.getDataFolder(), "kits");

        if (!kitsFolder.exists()) {
            kitsFolder.mkdir();
            LOGGER.warning("Kits folder not found, creating one");
        }

        files = kitsFolder.listFiles();
    }

    private void loadFiles() {
        File kitsFolder = new File(plugin.getDataFolder(), "kits");
        if (files == null) {
            scanFiles();
        }

        if (files.length == 0) {
            LOGGER.warning("No kits found");
            return;
        }

        for (File file : files) {
            if (file.getName().endsWith(".yml")) {
                YamlFile yamlFile = new YamlFile(plugin, file.getName(), ".yml", kitsFolder);
                filesMap.put(yamlFile.getName(), yamlFile);
            }
        }

    }

    public YamlFile getFile(String name) {
        return filesMap.get(name);
    }

    public String[] getKitsNames() {
        return filesMap.keySet().toArray(new String[0]);
    }

    @Override
    public void stop() {
        filesMap.forEach((key, value) -> value.save());
        filesMap.clear();
    }
}
