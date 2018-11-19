package br.feevale.game.context;

import java.awt.event.KeyEvent;

public class ContextPlayer {

    private static final int SPEED = 6;

    private int x = 0;
    private int y = 0;
    private String playerName;
    private int points = 0;
    private State state = State.NORMAL_RIGHT;

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
        if ("attacked".equals(command)) {
            if (state == State.ATTACK_RIGHT || state == State.NORMAL_RIGHT) {
                state = State.ATTACKED_RIGHT;
            } else if (state == State.ATTACK_LEFT || state == State.NORMAL_LEFT) {
                state = State.ATTACKED_LEFT;
            }
        } else if (isKeyPressDown(Integer.valueOf(command))) {
            if (state == State.ATTACK_RIGHT || state == State.ATTACKED_RIGHT) {
                state = State.NORMAL_RIGHT;
            } else if (state == State.ATTACK_LEFT || state == State.ATTACKED_LEFT) {
                state = State.NORMAL_LEFT;
            }
            y += SPEED;
        } else if (isKeyPressUp(Integer.valueOf(command))) {
            if (state == State.ATTACK_RIGHT || state == State.ATTACKED_RIGHT) {
                state = State.NORMAL_RIGHT;
            } else if (state == State.ATTACK_LEFT || state == State.ATTACKED_LEFT) {
                state = State.NORMAL_LEFT;
            }
            y -= SPEED;
        } else if (isKeyPressLeft(Integer.valueOf(command))) {
            state = State.NORMAL_LEFT;
            x -= SPEED;
        } else if (isKeyPressRight(Integer.valueOf(command))) {
            state = State.NORMAL_RIGHT;
            x += SPEED;
        } else if (isKeyPressSpaceBar(Integer.valueOf(command))) {
            if (state == State.NORMAL_RIGHT || state == State.ATTACKED_RIGHT) {
                state = State.ATTACK_RIGHT;
            } else if (state == State.NORMAL_LEFT || state == State.ATTACKED_LEFT) {
                state = State.ATTACK_LEFT;
            }
            points++;
        }
    }

    @Override
    public String toString() {
        return "" + playerName + "," + x + "," + y + "," + points + "," + state.getState() + "";
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

    public State getState() {
        return state;
    }
}
