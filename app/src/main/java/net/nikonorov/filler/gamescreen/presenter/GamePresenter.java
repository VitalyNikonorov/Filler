package net.nikonorov.filler.gamescreen.presenter;

import net.nikonorov.filler.gamescreen.ColorItem;

/**
 * Created by vitaly on 20.08.16.
 */
public interface GamePresenter {

    void makeMove(int player, ColorItem colorItem);
    void fieldChanged(ColorItem[][] gameField);
    void scoreChanged(int score);

}
