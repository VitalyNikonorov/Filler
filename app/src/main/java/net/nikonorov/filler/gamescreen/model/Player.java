package net.nikonorov.filler.gamescreen.model;

import android.util.Log;

import net.nikonorov.filler.gamescreen.ColorItem;
import net.nikonorov.filler.utils.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Stack;

/**
 * Created by vitaly on 22.08.16.
 */
public class Player {

    private ArrayList<CellCoordinate> cells = new ArrayList<>();
    private HashSet<String> cellsSet = new HashSet<>();
    private int score;
    private ColorItem color;

    public Player(ColorItem[][] gameField, int player) {

        score = 0;

        if (player == Constants.PLAYER_ONE){
            color = gameField[Constants.FIELD_HEIGHT - 1][0];
            cells.add(new CellCoordinate(Constants.FIELD_HEIGHT - 1, 0));
            cellsSet.add(String.format("%d_%d", Constants.FIELD_HEIGHT - 1, 0));
        } else {
            color = gameField[0][Constants.FIELD_WIDTH - 1];
            cells.add(new CellCoordinate(0, Constants.FIELD_WIDTH - 1));
            cellsSet.add(String.format("%d_%d", 0, Constants.FIELD_WIDTH - 1));
        }

        makeMove(color, gameField);

    }

    public int getScore() {
        return score;
    }

    public void makeMove(ColorItem color, ColorItem[][] gameField){
        this.color = color;

        boolean changed = true;

        while (changed) {
            changed = false;

            int indexI = -1;
            int indexJ = -1;

            for (CellCoordinate cell : cells){

                int i = cell.getCoordinates()[0];
                int j = cell.getCoordinates()[1];

                if( i < Constants.FIELD_HEIGHT - 1 ){
                    if ( gameField[i+1][j] == color && !cellsSet.contains(String.format("%d_%d", i + 1, j)) ){
                        indexI = i + 1;
                        indexJ = j;
                        break;
                    }
                }

                if( i > 0 ){
                    if ( gameField[i-1][j] == color && !cellsSet.contains(String.format("%d_%d", i - 1, j)) ){
                        indexI = i - 1;
                        indexJ = j;
                        break;
                    }
                }

                if( j < Constants.FIELD_WIDTH - 1 ){
                    if ( gameField[i][j+1] == color  && !cellsSet.contains(String.format("%d_%d", i, j + 1)) ){
                        indexI = i;
                        indexJ = j + 1;
                        break;
                    }
                }

                if( j > 0 ){
                    if ( gameField[i][j - 1] == color && !cellsSet.contains(String.format("%d_%d", i, j - 1)) ){
                        indexI = i;
                        indexJ = j - 1;
                        break;
                    }
                }
            }

            if (indexI != -1){
                cells.add(new CellCoordinate(indexI, indexJ));
                cellsSet.add(String.format("%d_%d", indexI, indexJ));
                changed = true;
            }

        }
        score = cells.size();
    }

    public ArrayList<CellCoordinate> getCells() {
        return cells;
    }

    class CellCoordinate{
        private int row;
        private int column;

        public CellCoordinate(int row, int column) {
            this.column = column;
            this.row = row;
        }

        public int[] getCoordinates(){
            return new int[]{row, column};
        }
    }

}
