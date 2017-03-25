package com.soccer.cloud.domain.enumeration;

public enum PlayerPosition {
    GK("Goalkeeper"),
    DF("Defender"),
    MF("Midfielder"),
    FW("Forward"),;
    private final String name;

    PlayerPosition(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
