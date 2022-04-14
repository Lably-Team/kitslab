package com.github.lablyteam.kitslab;

import com.github.lablyteam.kitslab.commands.KitsLabCommand;
import com.github.lablyteam.kitslab.configuration.YamlFile;
import com.github.lablyteam.kitslab.modules.BinderModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.bukkit.plugin.java.JavaPlugin;

public class KitsLab extends JavaPlugin {

    @Inject
    private KitsLabCommand kitsLabCommand;

    @Inject @Named("config")
    private YamlFile config;

    @Inject @Named("messages")
    private YamlFile messages;

    @Override
    public void onEnable() {
        registerModules();
        registerCommands();

    }

    @Override
    public void onDisable() {
        // On disable plugin
    }

    private void registerModules() {
        BinderModule binderModule = new BinderModule(this);
        Injector injector = binderModule.createInjector();
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
}
