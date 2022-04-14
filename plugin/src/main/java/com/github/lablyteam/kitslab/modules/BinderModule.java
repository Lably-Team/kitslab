package com.github.lablyteam.kitslab.modules;

import com.github.lablyteam.kitslab.KitsLab;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class BinderModule extends AbstractModule {

    private final KitsLab plugin;

    public BinderModule(KitsLab plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        this.bind(KitsLab.class).toInstance(plugin);
        this.install(new CommandModule());
        this.install(new FilesModule(plugin));
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }
}
