package com.github.RuSichPT.TestOrderMicroservice.services;

public enum Command {
    CREATE,
    READ,
    UPDATE,
    DELETE;
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
}
