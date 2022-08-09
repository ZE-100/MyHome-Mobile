package com.myhome.util.debug;

public enum DebugMode {
    OFFLINE("Offline"),
    ONLINE("Online"),
    DEACTIVATED("Deactivated");

    private String s;

    DebugMode(String s) {
        this.s = s;
    }

    public String value() {
        return this.s;
    }
}
