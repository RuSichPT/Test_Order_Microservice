package com.github.RuSichPT.TestOrderMicroservice.services;

public enum Command {
    CREATE("create"),
    READ("read"),
    UPDATE("update"),
    DELETE("delete");

    private String command;

    Command(String command)
    {
        this.command = command;
    }

    static Command toCommand(String command) throws RuntimeException
    {
        command = command.toLowerCase();

        return switch (command) {
            case "create" -> CREATE;
            case "read" -> READ;
            case "update" -> UPDATE;
            case "delete" -> DELETE;
            default -> throw new RuntimeException("is not command");
        };
    }

    @Override
    public String toString() {
        return this.command;
    }
}
