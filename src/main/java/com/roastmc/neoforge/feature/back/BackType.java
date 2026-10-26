package com.roastmc.neoforge.feature.back;

public enum BackType {
    TELEPORT, DEATH;

    public boolean isTeleport() {
        return this == TELEPORT;
    }
}
