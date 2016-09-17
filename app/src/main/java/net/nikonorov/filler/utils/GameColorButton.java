package net.nikonorov.filler.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by vitaly on 16.09.16.
 */
public class GameColorButton extends ImageButton {
    public GameColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if ( (width != 0) && (height != 0) ) {
            if (width < height) {
                height = width;
            } else {
                width = height;
            }
        }

        setMeasuredDimension(width, height);
    }
}
