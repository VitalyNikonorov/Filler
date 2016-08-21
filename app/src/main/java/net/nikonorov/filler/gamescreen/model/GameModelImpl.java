package net.nikonorov.filler.gamescreen.model;

import net.nikonorov.filler.gamescreen.ColorItem;
import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.gamescreen.presenter.GamePresenter;
import net.nikonorov.filler.utils.Constants;

import java.util.Date;
import java.util.Random;

/**
 * Created by vitaly on 20.08.16.
 */
public class GameModelImpl implements GameModel {

    private GamePresenter presenter;
    private GameMode gameMode;
    private ColorItem[][] gameField;


    public GameModelImpl(GamePresenter presenter, GameMode gameMode) {
        this.presenter = presenter;
        this.gameMode = gameMode;

        gameField = generateField(Constants.FIELD_HEIGHT, Constants.FIELD_WIDTH);

    }

    @Override
    public ColorItem[][] getGameField() {
        return gameField;
    }

    private ColorItem[][] generateField(int x, int y) {
        ColorItem[][] field = new ColorItem[x][y];

        for(int j = 0; j < x; j++){
            for(int i = 0; i < y; i++){
                field[j][i] = ColorItem.getRandom();
            }
        }
        return field;
    }

}
