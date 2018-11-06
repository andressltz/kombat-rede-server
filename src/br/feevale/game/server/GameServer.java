package br.feevale.game.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {

    private static final int SERVER_PORT = 8181;
    public static List<ClientThread> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);

                client.communicate();
                client.start();

                System.out.println("Novo cliente conectado - " + client.getClientName());
                clients.add(client);
                System.out.println("Quantidade de clientes: " + clients.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
