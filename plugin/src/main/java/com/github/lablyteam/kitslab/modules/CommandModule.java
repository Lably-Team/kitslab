package com.github.lablyteam.kitslab.modules;


import com.github.lablyteam.kitslab.commands.KitsLabCommand;
import com.google.inject.AbstractModule;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(KitsLabCommand.class).toInstance(new KitsLabCommand());
    }
}
