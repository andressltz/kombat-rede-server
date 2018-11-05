package br.feevale.game.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket connectedSocket;
    private BufferedReader reader;
    private PrintWriter print;

    public ClientThread(Socket socket) {
        connectedSocket = socket;
    }

    public void communicate() {
        try {
            reader = new BufferedReader(new InputStreamReader(connectedSocket.getInputStream()));
            print = new PrintWriter(connectedSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String key;
        while ((key = receive()) != null){
            System.out.println("Pressionado a tecla " + key);
            for (ClientThread client : GameServer.clients) {
                print.println("O servidor enviou a tecla " + key);
                print.flush();
            }
        }
    }

    private String receive() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
