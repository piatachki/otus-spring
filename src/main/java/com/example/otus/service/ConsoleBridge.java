package com.example.otus.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleBridge implements CommunicationBridge {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void write(String line) {
        System.out.println(line);
    }

    @Override
    public String read() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
