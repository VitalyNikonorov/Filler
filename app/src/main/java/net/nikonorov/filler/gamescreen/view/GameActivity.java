package net.nikonorov.filler.gamescreen.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
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
    private TextView scorePlayerOneTV;
    private TextView scorePlayerTwoTV;
    private ImageButton player2Img;
    private Dialog dialog;
    private TextView victoryMsgTV;
    private ImageButton player1Mark;
    private ImageButton player2Mark;

    private ImageButton[] gameButtons = new ImageButton[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameMode mode = (GameMode) getIntent().getSerializableExtra(Constants.MODE_FIELD);
        player2Img = (ImageButton) findViewById(R.id.player_2_img);
        switch (mode){
            case SINGLE_MODE:
                player2Img.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.ic_cpu_24dp));
                break;
            case TWO_PLAYERS:
                player2Img.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.ic_person_white_24dp));
        }

        gameCells = new GameCell[Constants.FIELD_HEIGHT][Constants.FIELD_WIDTH];

        player1Mark = (ImageButton) findViewById(R.id.game_player_1_mark);
        player2Mark = (ImageButton) findViewById(R.id.game_player_2_mark);

        gameFieldView = (LinearLayout) findViewById(R.id.game_field_view);
        generateGameFieldView();
        presenter = new GamePresenterImpl(this, mode);

        gameButtons[ColorItem.RED.getIndex()] = (ImageButton) findViewById(R.id.game_red_btn);
        gameButtons[ColorItem.RED.getIndex()].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(ColorItem.RED);
            }
        });

        gameButtons[ColorItem.YELLOW.getIndex()] = (ImageButton) findViewById(R.id.game_yellow_btn);
        gameButtons[ColorItem.YELLOW.getIndex()].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(ColorItem.YELLOW);
            }
        });

        gameButtons[ColorItem.GREEN.getIndex()] = (ImageButton) findViewById(R.id.game_green_btn);
        gameButtons[ColorItem.GREEN.getIndex()].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(ColorItem.GREEN);
            }
        });

        gameButtons[ColorItem.BLUE.getIndex()] = (ImageButton) findViewById(R.id.game_blue_btn);
        gameButtons[ColorItem.BLUE.getIndex()].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(ColorItem.BLUE);
            }
        });

        gameButtons[ColorItem.PINK.getIndex()] = (ImageButton) findViewById(R.id.game_pink_btn);
        gameButtons[ColorItem.PINK.getIndex()].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.makeMove(ColorItem.PINK);
            }
        });

        scorePlayerOneTV = (TextView) findViewById(R.id.score_player_1);
        scorePlayerTwoTV = (TextView) findViewById(R.id.score_player_2);

        presenter.refreshGameInfo();

        dialog = new Dialog(GameActivity.this);
        dialog.setContentView(R.layout.popup_victory);
        victoryMsgTV = (TextView) dialog.findViewById(R.id.popup_msg);

        dialog.findViewById(R.id.restart_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                presenter.onRestartClick();
            }
        });

        dialog.findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        overridePendingTransition(R.animator.open_translate, R.animator.close_scale);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.animator.open_scale, R.animator.close_translate);
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

//    public void scaleView(View v, float startScale, float endScale) {
//        Animation anim = new ScaleAnimation(
//                startScale, endScale, // Start and end values for the X axis scaling
//                startScale, endScale, // Start and end values for the Y axis scaling
//                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
//                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
//        anim.setFillAfter(true); // Needed to keep the result of the animation
//        v.startAnimation(anim);
//    }

    @Override
    public void showGameField(ColorItem[][] field) {

        for(int j = 0; j < Constants.FIELD_HEIGHT; j++){
            for(int i = 0; i < Constants.FIELD_WIDTH; i++){
                gameCells[j][i].setBackgroundColor(ContextCompat.getColor(this, Constants.GAME_COLORS[field[j][i].getIndex()]));
//                scaleView(gameCells[j][i], 1.5f, 1.0f);
            }
        }
    }

    @Override
    public void updateScore(int score1, int score2, ColorItem player1Color, ColorItem player2Color) {
        scorePlayerOneTV.setText(String.valueOf(score1));
        scorePlayerTwoTV.setText(String.valueOf(score2));
        player1Mark.setBackgroundColor(ContextCompat.getColor(this, Constants.GAME_COLORS[player1Color.getIndex()]));
        player2Mark.setBackgroundColor(ContextCompat.getColor(this, Constants.GAME_COLORS[player2Color.getIndex()]));
    }

    @Override
    public void disableBtns(ColorItem[] lockedColors) {
        for (int i = 0; i < gameButtons.length; i++){
            gameButtons[i].setImageDrawable(null);
        }

        for (int i = 0; i < lockedColors.length; i++){
            gameButtons[lockedColors[i].getIndex()].setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.ic_block_white_48dp));
        }
    }

    @Override
    public void showGameOverDialog(String msg) {
        victoryMsgTV.setText(Html.fromHtml(msg));
        dialog.show();
    }
}
