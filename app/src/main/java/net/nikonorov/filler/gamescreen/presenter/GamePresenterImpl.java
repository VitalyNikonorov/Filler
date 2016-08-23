package net.nikonorov.filler.gamescreen.presenter;

import net.nikonorov.filler.gamescreen.ColorItem;
import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.gamescreen.model.GameModel;
import net.nikonorov.filler.gamescreen.model.GameModelImpl;
import net.nikonorov.filler.gamescreen.view.GameView;

/**
 * Created by vitaly on 20.08.16.
 */
public class GamePresenterImpl implements GamePresenter {

    private GameView view;
    private GameModel model;

    private int activePlayer = 0; //TODO need to rewrite!!!!

    public GamePresenterImpl(GameView view, GameMode mode) {
        this.view = view;
        this.model = new GameModelImpl(this, mode);

        view.showGameField(model.getGameField());

    }

    @Override
    public void makeMove(int player, ColorItem colorItem) {
//        model.makeMove(player, colorItem);
        model.makeMove(activePlayer, colorItem);
        if (activePlayer == 0 ){
            activePlayer = 1;
        } else {
            activePlayer = 0;
        }
    }

    @Override
    public void fieldChanged(ColorItem[][] gameField) {
        view.showGameField(gameField);
    }

    @Override
    public void scoreChanged(int score1, int score2) {
        view.updateScore(score1, score2);
    }
}
