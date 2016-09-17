package net.nikonorov.filler.gamescreen.view;

import net.nikonorov.filler.gamescreen.ColorItem;
import net.nikonorov.filler.gamescreen.model.CellCoordinate;

import java.util.ArrayList;

/**
 * Created by vitaly on 20.08.16.
 */
public interface GameView {

    void showGameField(ColorItem[][] field, ArrayList<CellCoordinate> cells, int player);
    void updateScore(int score1, int score2, ColorItem player1Color, ColorItem player2Color);
    void disableBtns(ColorItem[] lockedColors);
    void showGameOverDialog(String msg);

}
