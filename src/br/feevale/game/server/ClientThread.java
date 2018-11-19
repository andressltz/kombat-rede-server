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
    private String clientAddress;
    private String clientName;


    public ClientThread(Socket socket, String clientName) {
        this.connectedSocket = socket;
        this.clientAddress = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
        this.clientName = clientName;
    }

    public void communicate() {
        try {
            reader = new BufferedReader(new InputStreamReader(connectedSocket.getInputStream()));
            print = new PrintWriter(connectedSocket.getOutputStream());
            print.println(GameServer.contextGame);
            print.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String command;
        while ((command = receive()) != null) {
            PrintWriter printWriter = null;
            System.out.println("Cliente: " + clientName + " Tecla: " + command);
            GameServer.contextGame.updateContext(clientName, command);
            for (ClientThread client : GameServer.clients) {
                try {
                    printWriter = new PrintWriter(client.connectedSocket.getOutputStream());
                    printWriter.println(GameServer.contextGame);
                    printWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println(GameServer.contextGame.toString());
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

    public String getClientAddress() {
        return clientAddress;
    }

    public String getClientName() {
        return clientName;
    }

}
