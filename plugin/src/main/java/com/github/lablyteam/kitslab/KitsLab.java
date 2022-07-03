package com.github.lablyteam.kitslab;

import com.github.lablyteam.kitslab.api.ManagerLoader;
import com.github.lablyteam.kitslab.commands.KitsLabCommand;
import com.github.lablyteam.kitslab.configuration.YamlFile;
import com.github.lablyteam.kitslab.managers.KitsFilesManager;
import com.github.lablyteam.kitslab.managers.KitManager;
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
    private KitsFilesManager kitsFilesManager;

    @Inject
    private KitManager kitManager;

    @Inject @Named("config")
    private YamlFile config;

    @Inject @Named("messages")
    private YamlFile messages;

    @Override
    public void onEnable() {
        registerModules();
        registerCommands();

        initManagers(
                kitsFilesManager,
                kitManager
        );

    }

    @Override
    public void onDisable() {
        getLogger().info("KitsLab disabled");

        stopMangers(
                kitsFilesManager,
                kitManager
        );
    }

    private void registerModules() {
        Injector injector = Guice.createInjector(new BinderModule(this));
        injector.injectMembers(this);
    }

    private void registerCommands() {
        getCommand("kitslab").setExecutor(kitsLabCommand);
    }

    private void initManagers(ManagerLoader... managerLoaders) {
        for (ManagerLoader managerLoader : managerLoaders) {
            managerLoader.start();
        }
    }

    private void stopMangers(ManagerLoader... managerLoaders) {
        for (ManagerLoader managerLoader : managerLoaders) {
            managerLoader.stop();
        }
    }

    @Override
    public YamlFile getConfig() {
        return config;
    }

    public YamlFile getMessages() {
        return messages;
    }

    public KitsFilesManager getKitsFilesManager() {
        return kitsFilesManager;
    }

    public KitManager getKitManager() {
        return kitManager;
    }
}