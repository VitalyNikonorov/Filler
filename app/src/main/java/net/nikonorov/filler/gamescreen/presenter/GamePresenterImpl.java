package net.nikonorov.filler.gamescreen.presenter;

import android.app.Activity;
import android.text.Html;

import net.nikonorov.filler.R;
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
    public void scoreChanged(int score1, int score2, ColorItem player1Color, ColorItem player2Color) {
        view.updateScore(score1, score2, player1Color, player2Color);
        if ( (score1 + score2) == (Constants.FIELD_HEIGHT * Constants.FIELD_WIDTH) ){
            String msg1 = "<font color=#000000>" + ((Activity)view).getString(R.string.victory_text_1) + " </font>";
            String msg2 = "<font color=#000000> " + ((Activity)view).getString(R.string.victory_text_2) + "</font>";
            String msg3;

            if (score1 > score2) {
                msg3 = "<font color=#F44336>" + ((Activity)view).getString(R.string.victory_text_red) + "</font>";
            } else {
                msg3 = "<font color=#2196F3>" + ((Activity)view).getString(R.string.victory_text_blue) + "</font>";
            }
            view.showGameOverDialog(msg1 + msg3 + msg2);
        }
    }

    @Override
    public void disableBtns() {
        view.disableBtns(model.getLockedColors());
    }

    @Override
    public void onRestartClick() {
        model.createGame();
        view.showGameField(model.getGameField());
        disableBtns();
        refreshGameInfo();
    }

    @Override
    public void refreshGameInfo() {
        disableBtns();
        model.refreshScore();
    }
}
