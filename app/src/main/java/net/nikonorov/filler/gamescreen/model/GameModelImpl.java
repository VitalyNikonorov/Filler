package net.nikonorov.filler.gamescreen.model;

import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.gamescreen.presenter.GamePresenter;

/**
 * Created by vitaly on 20.08.16.
 */
public class GameModelImpl implements GameModel {

    private GamePresenter presenter;
    private GameMode gameMode;

    public GameModelImpl(GamePresenter presenter, GameMode gameMode) {
        this.presenter = presenter;
        this.gameMode = gameMode;
    }
}
