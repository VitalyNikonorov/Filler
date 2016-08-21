package net.nikonorov.filler.gamescreen.view;

import android.app.Activity;
import android.os.Bundle;

import net.nikonorov.filler.R;
import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.gamescreen.presenter.GamePresenter;
import net.nikonorov.filler.gamescreen.presenter.GamePresenterImpl;
import net.nikonorov.filler.utils.Constants;

/**
 * Created by vitaly on 20.08.16.
 */
public class GameActivity extends Activity implements GameView {

    private GamePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameMode mode = (GameMode) getIntent().getSerializableExtra(Constants.modeField);
        presenter = new GamePresenterImpl(this, mode);
    }
}
