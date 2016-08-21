package net.nikonorov.filler.mainscreen.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import net.nikonorov.filler.R;
import net.nikonorov.filler.authorscreen.view.AuthorActivity;
import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.gamescreen.view.GameActivity;
import net.nikonorov.filler.mainscreen.MenuItems;
import net.nikonorov.filler.mainscreen.model.MainModelImpl;
import net.nikonorov.filler.mainscreen.presenter.MainPresenter;
import net.nikonorov.filler.mainscreen.presenter.MainPresenterImpl;
import net.nikonorov.filler.utils.Constants;

/**
 * Created by vitaly on 20.08.16.
 */
public class MainActivity extends Activity implements MainView {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImpl(this);


        Button singlePlayerBtn = (Button) findViewById(R.id.single_player_btn);
        singlePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMenuItemClick(MenuItems.SINGLE_PLAYER);
            }
        });

        Button twoPlayersBtn = (Button) findViewById(R.id.two_players_btn);
        twoPlayersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMenuItemClick(MenuItems.TWO_PLAYERS);
            }
        });

        Button authorBtn = (Button) findViewById(R.id.about_author_btn);
        authorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMenuItemClick(MenuItems.ABOUT_AUTHOR);
            }
        });
    }

    @Override
    public void showAuthorInfoScreen() {
        Intent intent = new Intent(this, AuthorActivity.class);
        startActivity(intent);
    }

    @Override
    public void startGame(GameMode mode) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(Constants.modeField, mode);
        startActivity(intent);
    }
}
