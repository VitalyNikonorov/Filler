package net.nikonorov.filler.gamescreen;

import android.util.Log;

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

    public static ColorItem getRandom(ColorItem[] lockedColors) {

        ColorItem item;

        if (lockedColors != null) {
            boolean isInCorrectColor;

            do {
                item = values()[(int) (Math.random() * values().length)];
                isInCorrectColor = false;

                for (int i = 0; i < lockedColors.length; i++) {
                    if (lockedColors[i] == item) {
                        isInCorrectColor = true;
                        break;
                    }
                }
            } while (isInCorrectColor);
        } else {
            item = values()[(int) (Math.random() * values().length)];
        }

        return item;
    }

    ColorItem(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
