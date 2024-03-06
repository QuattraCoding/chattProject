package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    ServerSocket serverSocket = new ServerSocket(1234);
    Server server  = new Server();

    public Main() throws IOException {
    }

    public static void main(String[] args) {



    }
}