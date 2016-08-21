package net.nikonorov.filler.gamescreen;

/**
 * Created by vitaly on 21.08.16.
 */
public enum ColorItem {

    RED(0),
    YELLOW(1),
    GREEN(2),
    BLUE(3),
    PINK(4);

    private int index;

    public static ColorItem getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    ColorItem(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
