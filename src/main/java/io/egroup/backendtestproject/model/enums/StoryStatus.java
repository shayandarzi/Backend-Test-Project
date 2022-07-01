package io.egroup.backendtestproject.model.enums;

public enum StoryStatus {
    NEW("new", 1),
    ESTIMATED("estimated", 2),
    COMPLETED("completed", 3);
    private final String name;

    private final int code;

    StoryStatus(String name, int code) {
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
