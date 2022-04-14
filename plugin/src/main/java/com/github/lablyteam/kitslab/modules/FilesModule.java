package com.github.lablyteam.kitslab.modules;

import com.github.lablyteam.kitslab.KitsLab;
import com.github.lablyteam.kitslab.configuration.YamlFile;
import com.google.inject.AbstractModule;

import static com.google.inject.name.Names.named;

public class FilesModule extends AbstractModule {
    private final KitsLab plugin;

    public FilesModule(KitsLab plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        this.bind(YamlFile.class)
                .annotatedWith(named("config"))
                .toInstance(new YamlFile(plugin, "config"));
        this.bind(YamlFile.class)
                .annotatedWith(named("messages"))
                .toInstance(new YamlFile(plugin, "messages"));

    }
}
