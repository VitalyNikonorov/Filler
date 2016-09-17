package net.nikonorov.filler.gamescreen.presenter;

import net.nikonorov.filler.gamescreen.ColorItem;
import net.nikonorov.filler.gamescreen.model.CellCoordinate;

import java.util.ArrayList;

/**
 * Created by vitaly on 20.08.16.
 */
public interface GamePresenter {

    void makeMove(ColorItem colorItem);
    void fieldChanged(ColorItem[][] gameField, ArrayList<CellCoordinate> cells, int player);
    void scoreChanged(int score1, int score2, ColorItem player1Color, ColorItem player2Color);
    void disableBtns();
    void onRestartClick();
    void refreshGameInfo();
}
