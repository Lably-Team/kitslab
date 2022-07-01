package com.github.lablyteam.kitslab.configuration;

import com.github.lablyteam.kitslab.KitsLab;
import com.github.lablyteam.kitslab.utils.TextUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static com.github.lablyteam.kitslab.utils.TextUtils.toLegacy;

public class YamlFile extends YamlConfiguration {

    private final KitsLab plugin;
    private final String fileName;
    private final File folder;

    public YamlFile(KitsLab plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    public YamlFile(KitsLab plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    public YamlFile(KitsLab plugin, String fileName, String fileExtension, File folder) {
        this.folder = folder;
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : fileExtension);
        this.create();
    }

    @Override
    public String getString(String path) {
        // Return the specified path in case the result is null
        return super.getString(path, path);
    }

    /**
     * Returns a String from a configuration file.
     * @param path The path of the string
     * @param colorize Whether to translate alternate color codes or not
     * @return The final string
     */
    public String getString(String path, boolean colorize) {
        String result = getString(path);
        return colorize ? ChatColor.translateAlternateColorCodes('&', result) : result;
    }

    /**
     * Returns a Material from a configuration file.
     * @param path The path of the material
     * @return The final material
     */
    public Material getMaterial(String path) {
        return getMaterial(path, Material.BEDROCK);
    }

    /**
     * Returns a Material from a configuration file.
     * @param path The path of the material
     * @param defaultValue The default value if the material is not found or is null
     * @return The final material
     */
    public Material getMaterial(String path, Material defaultValue) {
        if (Material.matchMaterial(super.getString(path)) == null) {
            return defaultValue;
        }
        return Material.matchMaterial(getString(path));
    }

    @Override
    public List<String> getStringList(String path) {
        List<String> result;
        if (super.getStringList(path) == null) {
            result = new ArrayList<>();
            result.add(path);
            return result;
        }
        result = super.getStringList(path);
        result.replaceAll(TextUtils::toLegacy);
        return result;
    }

    public void create() {
        try {
            File file = new File(this.folder, this.fileName);
            if (file.exists()) {
                this.load(file);
                this.save(file);
            } else {
                if (this.plugin.getResource(this.fileName) != null) {
                    this.plugin.saveResource(this.fileName, false);
                } else {
                    this.save(file);
                }

                this.load(file);
            }
        } catch (IOException | InvalidConfigurationException exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Unable to create '" + this.fileName + "'.", exception);
        }
    }

    public void save() {
        File folder = this.plugin.getDataFolder();
        File file = new File(folder, this.fileName);

        try {
            this.save(file);
        } catch (IOException exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Unable to save file '" + this.fileName + "'.", exception);
        }

    }

    public void reload() {
        File file = new File(folder, this.fileName);

        try {
            this.load(file);
        } catch (InvalidConfigurationException | IOException exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Unable to reload file '" + this.fileName + "'.", exception);
        }
    }
}
