package com.project.sortvisualisations.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.project.sortvisualisations.SortArray;

public class DataGraph extends View {

    private SortArray array;

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

    public void setData(SortArray data) {
        this.array = data;
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        viewWidth = xNew;
        viewHeight = yNew;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < array.getSize(); i++) {
            drawElement(canvas, i);
        }
        super.onDraw(canvas);
    }

    private void drawElement(Canvas canvas, int index) {
        Rect rectangleToDraw = getRect(index);
        Paint rectangleColor = getColor(index);

        canvas.drawRect(rectangleToDraw, rectangleColor);
    }

    private Rect getRect(int index) {
        int barWidth = (viewWidth - array.getSize()) / array.getSize();

        Rect rect = new Rect();
        rect.top = viewHeight - (array.getValue(index) * (viewHeight / array.getSize()));
        rect.left = index * barWidth + index;
        rect.bottom = viewHeight;
        rect.right = index * barWidth + barWidth + index;
        return rect;
    }

    private Paint getColor(int index) {
        boolean isHighlighted = array.isHighlighted(index);
        Paint color = new Paint();
        if (isHighlighted) {
            color.setColor(Color.RED);
        } else {
            color.setColor(Color.DKGRAY);
        }
        return color;
    }
}
