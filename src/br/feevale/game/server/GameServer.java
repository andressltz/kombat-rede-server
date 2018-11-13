package br.feevale.game.server;

import br.feevale.game.context.ContextGame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {

    private static final int SERVER_PORT = 8181;
    public static List<ClientThread> clients = new ArrayList<>();
    public static List<String> names = new ArrayList<>();
    public static ContextGame contextGame;

    public static void main(String[] args) {
        try {
            contextGame = new ContextGame();
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            createNames();

            while (true) {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket, names.get(0));
                contextGame.addNewPlayer(names.get(0));
                names.remove(0);
                client.communicate();
                client.start();

                System.out.println("Novo cliente conectado - " + client.getClientAddress() + " - " + client.getClientName());
                clients.add(client);
                System.out.println("Quantidade de clientes: " + clients.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createNames() {
        for (char c: "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            names.add(String.valueOf(c));
        }
    }

}
