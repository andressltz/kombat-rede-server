package br.feevale.game.context;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ContextGame {

    private static List<ContextPlayer> players = new ArrayList<>();

    public void addNewPlayer(String name) {
        ContextPlayer player = new ContextPlayer();
        player.setPlayerName(name);
        players.add(player);
    }

    public void updateContext(String clientName, String command) {
        for (ContextPlayer player : players) {
            // TODO: aqui podia ser um hashmap
            if (player.getPlayerName().equals(clientName)) {
                player.updatePlayer(command);
                if (Integer.valueOf(command) == KeyEvent.VK_SPACE) {

                }
                break;
            }
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
//        sb.append("{ \"players\":");
//        sb.append("[");
        for (int i = 0; i < players.size(); i++) {
            sb.append(players.get(i).toString());
            if (i < players.size() - 1) {
                sb.append(";");
            }
        }
//        sb.append("]");
//        sb.append("}");
        return sb.toString();
    }

    public void fromString(String context) {
        String[] stringPlayer = context.split(";");
        players = new ArrayList<>();
        for (int i = 0; i < stringPlayer.length; i++) {
            String[] vals = stringPlayer[i].split(",");
            ContextPlayer player = new ContextPlayer();
            player.setX(Integer.valueOf(vals[1]));
            player.setY(Integer.valueOf(vals[2]));
            player.setPlayerName(vals[0]);
            player.setPoints(Integer.valueOf(vals[3]));
            players.add(player);
        }
    }

    public List<ContextPlayer> getPlayers() {
        return players;
    }

}
