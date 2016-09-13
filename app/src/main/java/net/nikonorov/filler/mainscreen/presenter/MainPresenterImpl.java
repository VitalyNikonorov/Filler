package net.nikonorov.filler.mainscreen.presenter;

import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.mainscreen.MenuItems;
import net.nikonorov.filler.mainscreen.model.MainModel;
import net.nikonorov.filler.mainscreen.model.MainModelImpl;
import net.nikonorov.filler.mainscreen.view.MainView;

/**
 * Created by vitaly on 20.08.16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private MainModel model;

    public MainPresenterImpl(MainView view) {

        this.view = view;
        model = new MainModelImpl();

    }

    @Override
    public void onMenuItemClick(MenuItems item) {

        switch (item) {
            case SINGLE_PLAYER :
                view.startGame(GameMode.SINGLE_MODE);
                break;

            case TWO_PLAYERS :
                view.startGame(GameMode.TWO_PLAYERS);
                break;

            case ABOUT_AUTHOR :
                view.showAuthorInfoScreen();
                break;
        }

    }
}
