package net.nikonorov.filler.gamescreen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by vitaly on 21.08.16.
 */
public class GameCell extends View {

    public GameCell(Context context) {
        super(context);
    }

    public GameCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
