package br.feevale.game.context;

public enum State {

    NORMAL_RIGHT(0),
    NORMAL_LEFT(1),
    ATTACK_RIGHT(2),
    ATTACK_LEFT(3),
    ATTACKED_RIGHT(4),
    ATTACKED_LEFT(5);

    private int state;

    State(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
