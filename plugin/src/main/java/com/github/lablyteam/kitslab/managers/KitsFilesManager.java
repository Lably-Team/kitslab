package com.github.lablyteam.kitslab.managers;

import com.github.lablyteam.kitslab.KitsLab;
import com.github.lablyteam.kitslab.api.ManagerLoader;
import com.github.lablyteam.kitslab.configuration.YamlFile;

import javax.inject.Inject;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class KitsFilesManager implements ManagerLoader {

    @Inject
    private KitsLab plugin;

    private File[] files;
    private File kitsFolder;
    private final Map<String, YamlFile> filesMap = new HashMap<>();

    @Override
    public void start() {
        kitsFolder = new File(plugin.getDataFolder(), "kits");
        scanFiles();
        loadFiles();
    }

    private void scanFiles() {
        if (!kitsFolder.exists()) {
            kitsFolder.mkdir();
            plugin.getLogger().warning("Kits folder not found, creating one");
        }

        files = kitsFolder.listFiles();
    }

    private void loadFiles() {
        if (files == null) {
            scanFiles();
            plugin.getLogger().log(Level.SEVERE, "No files found in kits folder");
            return;
        }

        if (files.length == 0) {
            YamlFile defaultKit = new YamlFile(plugin, "initial", ".yml", kitsFolder);
            filesMap.put("initial", defaultKit);
            plugin.getLogger().warning("No kits found");
            return;
        }

        for (File file : files) {
            if (file.getName().endsWith(".yml")) {
                YamlFile yamlFile = new YamlFile(plugin, file.getName(), ".yml", kitsFolder);
                filesMap.put(yamlFile.getString("kit.name", "null"), yamlFile);
            }
        }

    }

    public void reloadFiles() {
        filesMap.clear();
        loadFiles();
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
