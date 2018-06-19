package com.cityconstruction.blcheung.heweather.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by BLCheung.
 * Date:2018/5/13 17:10
 */
public class ViewTest extends View {
    public ViewTest(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
