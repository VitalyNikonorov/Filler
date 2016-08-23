package net.nikonorov.filler.gamescreen.model;

import net.nikonorov.filler.gamescreen.ColorItem;
import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.gamescreen.presenter.GamePresenter;
import net.nikonorov.filler.utils.Constants;

/**
 * Created by vitaly on 20.08.16.
 */
public class GameModelImpl implements GameModel {

    private GamePresenter presenter;
    private GameMode gameMode;
    private ColorItem[][] gameField;
    private ColorItem[] lockedColors;
    private Player[] players;

    public GameModelImpl(GamePresenter presenter, GameMode gameMode) {
        this.presenter = presenter;
        this.gameMode = gameMode;

        gameField = generateField(Constants.FIELD_HEIGHT, Constants.FIELD_WIDTH);
        lockedColors = new ColorItem[]{gameField[Constants.FIELD_HEIGHT - 1][0], gameField[0][Constants.FIELD_WIDTH - 1]};

        players = new Player[2];

        Player player1 = new Player(gameField, Constants.PLAYER_ONE);
        Player player2 = new Player(gameField, Constants.PLAYER_TWO);

        players[0] = player1;
        players[1] = player2;

    }

    @Override
    public ColorItem[][] getGameField() {
        return gameField;
    }

    @Override
    public void makeMove(int player, ColorItem colorItem) {

        players[player].makeMove(colorItem, gameField);

        for (Player.CellCoordinate coordinate : players[player].getCells()){
            gameField[coordinate.getCoordinates()[0]][coordinate.getCoordinates()[1]] = colorItem;
        }

        presenter.fieldChanged(gameField);
        presenter.scoreChanged(players[0].getScore(), players[1].getScore());

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
