package io.egroup.backendtestproject.model.enums;

public enum Priority {
    CRITICAL("critical", 1),
    MAJOR("major", 2),
    MINOR("minor", 3);

    Priority(String name, int code) {
        this.name = name;
        this.code = code;
    }

    private final String name;

    private final int code;

    public String getValue() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
