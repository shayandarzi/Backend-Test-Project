package io.egroup.backendtestproject.model.enums;

public enum BugStatus {
    NEW("new", 1),
    VERIFIED("verified", 2),
    RESOLVED("resolved", 3);


    private final String name;

    private final int code;

    BugStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getValue() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
