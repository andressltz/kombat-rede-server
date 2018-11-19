package br.feevale.game.context;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

public class ContextGame {

    private static final int X_APROX = 48;
    private static final int Y_APROX = 42;

    public static HashMap<String, ContextPlayer> players = new HashMap<>();
    private Random random = new Random();

    public void addNewPlayer(String name) {
        ContextPlayer player = new ContextPlayer();
        player.setPlayerName(name);
        player.setX(random.nextInt(500));
        player.setY(random.nextInt(500));
        players.put(name, player);
    }

    public void updateContext(String clientName, String command) {
        ContextPlayer player = players.get(clientName);
        if (Integer.valueOf(command) == KeyEvent.VK_SPACE) {
            int pX = player.getX();
            int pY = player.getY();

            for (ContextPlayer cPlayer : players.values()) {
                if (!cPlayer.getPlayerName().equals(player.getPlayerName())) {
                    if (player.getState() == State.NORMAL_RIGHT || player.getState() == State.ATTACK_RIGHT || player.getState() == State.ATTACKED_RIGHT) {
                        if ((cPlayer.getX() >= pX && cPlayer.getX() <= pX + X_APROX && cPlayer.getY() >= pY && cPlayer.getY() <= pY + Y_APROX) ||
                                (cPlayer.getX() <= pX && cPlayer.getX() >= pX - X_APROX && cPlayer.getY() <= pY && cPlayer.getY() >= pY - Y_APROX)) {
                            player.updatePlayer(command);
                            cPlayer.updatePlayer("attacked");
                        }
                    } else if (player.getState() == State.NORMAL_LEFT || player.getState() == State.ATTACK_LEFT || player.getState() == State.ATTACKED_LEFT) {
                        if ((cPlayer.getX() <= pX && cPlayer.getX() >= pX - X_APROX && cPlayer.getY() <= pY && cPlayer.getY() >= pY - Y_APROX) ||
                                (cPlayer.getX() >= pX && cPlayer.getX() <= pX + X_APROX && cPlayer.getY() >= pY && cPlayer.getY() <= pY + Y_APROX)) {
                            player.updatePlayer(command);
                            cPlayer.updatePlayer("attacked");
                        }
                    }
                }
            }
        } else {
            player.updatePlayer(command);
        }
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("{ \"players\":");
//        sb.append("[");
//        for (int i = 0; i < players.size(); i++) {
//            sb.append(players.get(i).toString());
//            if (i < players.size() - 1) {
//                sb.append(",");
//            }
//        }
//        sb.append("]");
//        sb.append("}");
//        return sb.toString();
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (ContextPlayer cPlayer : players.values()) {
            sb.append(cPlayer.toString());
            if (i < players.size() - 1) {
                sb.append(";");
            }
            i++;
        }
        return sb.toString();
    }

    public void fromString(String context) {
        String[] stringPlayer = context.split(";");
        players = new HashMap<>();
        for (int i = 0; i < stringPlayer.length; i++) {
            String[] vals = stringPlayer[i].split(",");
            ContextPlayer player = new ContextPlayer();
            player.setX(Integer.valueOf(vals[1]));
            player.setY(Integer.valueOf(vals[2]));
            player.setPlayerName(vals[0]);
            player.setPoints(Integer.valueOf(vals[3]));
            players.put(vals[0], player);
        }
    }

    public HashMap<String, ContextPlayer> getPlayers() {
        return players;
    }

}
