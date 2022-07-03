package com.github.lablyteam.kitslab.modules;

import com.github.lablyteam.kitslab.managers.KitsFilesManager;
import com.github.lablyteam.kitslab.managers.KitManager;
import com.google.inject.AbstractModule;

public class ManagerModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(KitsFilesManager.class).toInstance(new KitsFilesManager());
        this.bind(KitManager.class).toInstance(new KitManager());
    }
}
