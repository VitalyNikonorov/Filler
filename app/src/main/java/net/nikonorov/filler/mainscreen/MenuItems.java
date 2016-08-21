package net.nikonorov.filler.mainscreen;

/**
 * Created by vitaly on 21.08.16.
 */
public enum MenuItems {

    SINGLE_PLAYER(0),
    TWO_PLAYERS(1),
    ABOUT_AUTHOR(2);

    private int pointer;

    MenuItems(int pointer) {
        this.pointer = pointer;
    }
}
