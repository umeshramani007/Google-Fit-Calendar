package com.skyhope.evencalneder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    private Paint paint;
    int[] x, y;
    private int[] radius;
    private boolean[] isVisible;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAlpha(50);
    }

    public void drawShape(int[] x, int[] y, int[] radius, boolean[] isVisible) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isVisible = isVisible;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (x == null || x.length == 0)
            return;
        for (int i = 0; i < x.length; i++) {
            if (!isVisible[i])
                continue;
            canvas.drawCircle(x[i], y[i], radius[i], paint);
        }

    }
}
