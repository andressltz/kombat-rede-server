package br.feevale.game.context;

import java.awt.event.KeyEvent;

public class ContextPlayer {

    private static final int SPEED = 6;

    private int x = 0;
    private int y = 0;
    private String playerName;
    private int points = 0;
    private int state = 0; // 0 - normal 1 - attack

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

//    @Override
//    public String toString() {
//        return "{ \"x\": \"" + x + "\", \"y\": \"" + y + "\", \"playerName\": \"" + playerName + "\" }";
//    }

    public void updatePlayer(String command) {
        state = 0;
        if ("keyReleased".equals(command)) {

        } else if (isKeyPressDown(Integer.valueOf(command))) {
            y += SPEED;
        } else if (isKeyPressUp(Integer.valueOf(command))) {
            y -= SPEED;
        } else if (isKeyPressLeft(Integer.valueOf(command))) {
            x -= SPEED;
        } else if (isKeyPressRight(Integer.valueOf(command))) {
            x += SPEED;
        } else if (isKeyPressSpaceBar(Integer.valueOf(command))) {
            state = 1;
        }
    }

    @Override
    public String toString() {
        return "" + playerName + "," + x + "," + y + "," + points + "," + state + "";
    }

    private boolean isKeyPressUp(int keyCode) {
        return keyCode == KeyEvent.VK_UP;
    }

    private boolean isKeyPressDown(int keyCode) {
        return keyCode == KeyEvent.VK_DOWN;
    }

    private boolean isKeyPressLeft(int keyCode) {
        return keyCode == KeyEvent.VK_LEFT;
    }

    private boolean isKeyPressRight(int keyCode) {
        return keyCode == KeyEvent.VK_RIGHT;
    }

    private boolean isKeyPressSpaceBar(int keyCode) {
        return keyCode == KeyEvent.VK_SPACE;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
