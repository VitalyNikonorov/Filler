package net.nikonorov.filler.gamescreen.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import net.nikonorov.filler.utils.PixelConverter;

/**
 * Created by vitaly on 21.08.16.
 */
public class GameCell extends View {

    public GameCell(Context context) {
        super(context);
    }

    public GameCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setElevation(PixelConverter.DPToPX(6, getContext()));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
