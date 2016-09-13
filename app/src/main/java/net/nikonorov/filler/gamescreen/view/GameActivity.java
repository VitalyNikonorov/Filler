package net.nikonorov.filler.gamescreen.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.nikonorov.filler.R;
import net.nikonorov.filler.gamescreen.ColorItem;
import net.nikonorov.filler.gamescreen.GameMode;
import net.nikonorov.filler.gamescreen.presenter.GamePresenter;
import net.nikonorov.filler.gamescreen.presenter.GamePresenterImpl;
import net.nikonorov.filler.utils.Constants;
import net.nikonorov.filler.utils.PixelConverter;

/**
 * Created by vitaly on 20.08.16.
 */
public class GameActivity extends Activity implements GameView {

    private GamePresenter presenter;
    private LinearLayout gameFieldView;
    private GameCell[][] gameCells;
    private TextView scoreTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameMode mode = (GameMode) getIntent().getSerializableExtra(Constants.MODE_FIELD);
        switch (mode){
            case SINGLE_MODE:
                setTitle(getResources().getString(R.string.single_mode_title));
        }

        gameCells = new GameCell[Constants.FIELD_HEIGHT][Constants.FIELD_WIDTH];

        gameFieldView = (LinearLayout) findViewById(R.id.game_field_view);

        generateGameFieldView();

        presenter = new GamePresenterImpl(this, mode);

        Button redBtn = (Button) findViewById(R.id.game_red_btn);
        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(Constants.PLAYER_ONE, ColorItem.RED);
            }
        });

        Button yellowBtn = (Button) findViewById(R.id.game_yellow_btn);
        yellowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(Constants.PLAYER_ONE, ColorItem.YELLOW);
            }
        });

        Button greenBtn = (Button) findViewById(R.id.game_green_btn);
        greenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(Constants.PLAYER_ONE, ColorItem.GREEN);
            }
        });

        Button blueBtn = (Button) findViewById(R.id.game_blue_btn);
        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(Constants.PLAYER_ONE, ColorItem.BLUE);
            }
        });

        Button pinkBtn = (Button) findViewById(R.id.game_pink_btn);
        pinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(Constants.PLAYER_ONE, ColorItem.PINK);
            }
        });

        scoreTV = (TextView) findViewById(R.id.score_tv);
    }

    private void generateGameFieldView() {
        int padding = PixelConverter.DPToPX(1, this);

        for(int j = 0; j < Constants.FIELD_HEIGHT; j++){

            LinearLayout row = new LinearLayout(this);
            LinearLayout.LayoutParams rowParameters = new LinearLayout.LayoutParams(0, 100);
            rowParameters.width = LinearLayout.LayoutParams.MATCH_PARENT;
            rowParameters.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            row.setLayoutParams(rowParameters);

            for(int i = 0; i < Constants.FIELD_WIDTH; i++){
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, 100);
                lp.weight = 1;
                lp.setMargins(padding, padding, padding, padding);

                GameCell cell = new GameCell(GameActivity.this);
                cell.setLayoutParams(lp);
                gameCells[j][i] = cell;
                row.addView(cell);
            }

            gameFieldView.addView(row);

        }
    }

    @Override
    public void showGameField(ColorItem[][] field) {

        for(int j = 0; j < Constants.FIELD_HEIGHT; j++){
            for(int i = 0; i < Constants.FIELD_WIDTH; i++){
                gameCells[j][i].setBackgroundColor(ContextCompat.getColor(this, Constants.GAME_COLORS[field[j][i].getIndex()]));
            }
        }
    }

    @Override
    public void updateScore(int score1, int score2) {
        scoreTV.setText(String.format("Player1: %d, Player2: %d", score1, score2));
    }
}
