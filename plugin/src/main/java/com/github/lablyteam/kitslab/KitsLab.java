package com.github.lablyteam.kitslab;

import com.github.lablyteam.kitslab.commands.KitsLabCommand;
import com.github.lablyteam.kitslab.configuration.YamlFile;
import com.github.lablyteam.kitslab.managers.FilesManager;
import com.github.lablyteam.kitslab.modules.BinderModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.bukkit.plugin.java.JavaPlugin;

public class KitsLab extends JavaPlugin {

    @Inject
    private KitsLabCommand kitsLabCommand;

    @Inject
    private FilesManager filesManager;

    @Inject @Named("config")
    private YamlFile config;

    @Inject @Named("messages")
    private YamlFile messages;

    @Override
    public void onEnable() {
        registerModules();
        registerCommands();

        // Startup logic files
        filesManager.start();

    }

    @Override
    public void onDisable() {
        getLogger().info("KitsLab disabled");

        filesManager.stop();
    }

    private void registerModules() {
        Injector injector = Guice.createInjector(new BinderModule(this));
        injector.injectMembers(this);
    }

    private void registerCommands() {
        getCommand("kitslab").setExecutor(kitsLabCommand);
    }

    @Override
    public YamlFile getConfig() {
        return config;
    }

    public YamlFile getMessages() {
        return messages;
    }

    public FilesManager getFilesManager() {
        return filesManager;
    }
}