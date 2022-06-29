package com.github.lablyteam.kitslab.modules;

import com.github.lablyteam.kitslab.managers.FilesManager;
import com.github.lablyteam.kitslab.managers.KitManager;
import com.google.inject.AbstractModule;

public class ManagerModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(FilesManager.class).toInstance(new FilesManager());
        this.bind(KitManager.class).toInstance(new KitManager());
    }
}
