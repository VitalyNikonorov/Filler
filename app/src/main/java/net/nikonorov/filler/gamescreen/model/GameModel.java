package net.nikonorov.filler.gamescreen.model;

import net.nikonorov.filler.gamescreen.ColorItem;

/**
 * Created by vitaly on 20.08.16.
 */
public interface GameModel {

    ColorItem[][] getGameField();
    void makeMove(int player, ColorItem colorItem);
    void onMoved(int player);
    ColorItem[] getLockedColors();
    void createGame();
    void refreshScore();
}
