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
    private String clientName;

    public ClientThread(Socket socket) {
        connectedSocket = socket;
        clientName = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
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
        while ((key = receive()) != null) {
            PrintWriter printWriter = null;
            System.out.println("Pressionado a tecla " + key);
            for (ClientThread client : GameServer.clients) {
                try {
                    System.out.println("Avisando o cliente " + client.getClientName() + " tecla " + key);
                    printWriter = new PrintWriter(client.connectedSocket.getOutputStream());
                    printWriter.println(key);
                    printWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public String getClientName() {
        return clientName;
    }

}
