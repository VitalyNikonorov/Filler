package net.nikonorov.filler.gamescreen.presenter;

import android.text.Html;

import net.nikonorov.filler.gamescreen.ColorItem;
import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.gamescreen.model.GameModel;
import net.nikonorov.filler.gamescreen.model.GameModelImpl;
import net.nikonorov.filler.gamescreen.view.GameView;
import net.nikonorov.filler.utils.Constants;

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
    public void makeMove(ColorItem colorItem) {

        if (!isLocked(colorItem)) {

            model.makeMove(activePlayer, colorItem);

            if (activePlayer == 0) {
                activePlayer = 1;
            } else {
                activePlayer = 0;
            }

            model.onMoved(activePlayer);
        }
    }

    boolean isLocked(ColorItem colorItem){
        boolean result = false;

        for  (int i = 0; i < model.getLockedColors().length; i++){
            if (model.getLockedColors()[i] == colorItem){
                result = true;
            }
        }
        return result;

    }

    @Override
    public void fieldChanged(ColorItem[][] gameField) {
        view.showGameField(gameField);
    }

    @Override
    public void scoreChanged(int score1, int score2) {
        view.updateScore(score1, score2);
        if ( (score1 + score2) == (Constants.FIELD_HEIGHT * Constants.FIELD_WIDTH) ){
            String msg;
            if (score1 > score2) {
                msg = "<font color=#F48FB1>Выиграл игрок в</font> <font color=#F44336> красном </font> <font color=#F48FB1>углу</font>";
            } else {
                msg = "<font color=#F48FB1>Выиграл игрок в</font> <font color=#2196F3> синем </font> <font color=#F48FB1>углу</font>";
            }
            view.showGameOverDialog(msg);
        }
    }

    @Override
    public void disableBtns() {
        view.disableBtns(model.getLockedColors());
    }
}
