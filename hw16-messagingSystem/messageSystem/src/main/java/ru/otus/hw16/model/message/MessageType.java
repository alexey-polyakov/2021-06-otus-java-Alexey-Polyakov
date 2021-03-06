package ru.otus.hw16.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MessageType {

    VOID("Void"),
    SAVE_CLIENT("SaveClient"),
    GET_CLIENTS("GetClients");

    @Getter
    private final String name;
}
