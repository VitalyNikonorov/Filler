package net.nikonorov.filler.gamescreen.model;

/**
 * Created by vitaly on 17.09.16.
 */
public class CellCoordinate{
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