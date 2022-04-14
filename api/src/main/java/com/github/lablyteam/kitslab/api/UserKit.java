package com.github.lablyteam.kitslab.api;

import java.util.List;
import java.util.UUID;

public class UserKit {

    private String playerName;
    private UUID idPlayer;
    private List<Kits> playerKits;

    public UserKit(UUID idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public UUID getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(UUID idPlayer) {
        this.idPlayer = idPlayer;
    }

    public List<Kits> getPlayerKits() {
        return playerKits;
    }

    public void setPlayerKits(List<Kits> playerKits) {
        this.playerKits = playerKits;
    }
}
