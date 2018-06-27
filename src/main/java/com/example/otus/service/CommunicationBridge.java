package com.example.otus.service;

public interface CommunicationBridge {

    void write(String line);

    String read();

}
