package com.project.sortvisualisations.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class DataGraph extends View {

    private int[] values;
    private boolean[] highlighted;
    private int viewWidth;
    private int viewHeight;

    public DataGraph(Context context) {
        super(context);
    }

    public DataGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DataGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DataGraph(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(int[] data, boolean[] highlighted) {
        values = data;
        this.highlighted = highlighted;
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        viewWidth = xNew;
        viewHeight = yNew;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < values.length; i++) {
            drawElement(canvas, i);
        }
        super.onDraw(canvas);
        invalidate();
    }

    private void drawElement(Canvas canvas, int index) {
        Rect rectangleToDraw = getRect(index);
        Paint rectangleColor = getColor(index);

        canvas.drawRect(rectangleToDraw, rectangleColor);
    }

    private Rect getRect(int index) {
        int barWidth = (viewWidth - values.length) / values.length;

        Rect rect = new Rect();
        rect.top = viewHeight - (values[index] * (viewHeight / values.length));
        rect.left = index * barWidth + index;
        rect.bottom = viewHeight;
        rect.right = index * barWidth + barWidth + index;
        return rect;
    }

    private Paint getColor(int index) {
        boolean hl = highlighted[index];
        Paint p = new Paint();
        if (hl) {
            p.setColor(Color.RED);
        } else {
            p.setColor(Color.DKGRAY);
        }
        return p;
    }
}
