package net.nikonorov.filler.gamescreen;

/**
 * Created by vitaly on 21.08.16.
 */
public enum GameMode {

    SINGLE_MODE(1),
    TWO_PLAYERS(2);

    private int index;

    GameMode(int index) {
        this.index = index;
    }
}
