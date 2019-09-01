package cn.ommiao.flyme8watermark.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import cn.ommiao.flyme8watermark.R;

public class Flyme8Watermark extends View {

    private static final String WATERMARK = "Flyme 内测版";

    private int fontSize, screenWidth, screenHeight, spaceH, spaceV, marginLeft;

    private Paint mPaint = new Paint();
    private float widthPerDraw;

    private int lines, timesPerLine;

    public Flyme8Watermark(Context context) {
        this(context, null);
    }

    public Flyme8Watermark(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Flyme8Watermark(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Flyme8Watermark(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        fontSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        spaceH = getResources().getDimensionPixelOffset(R.dimen.spaceH);
        spaceV = getResources().getDimensionPixelOffset(R.dimen.spaceV);
        marginLeft = getResources().getDimensionPixelOffset(R.dimen.marginLeft);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorWatermark));
        mPaint.setTextSize(fontSize);
        widthPerDraw = getTextWidth(WATERMARK) + spaceH;
        lines = getLines();
        timesPerLine = getTimesPerLine();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(-30);
        canvas.translate((float) -(Math.sqrt(3) / 8 * widthPerDraw) + marginLeft, 0);
        for(int i = 0; i < getLines(); i++){
            for(int j = 0; j < timesPerLine; j++){
                canvas.drawText(WATERMARK, - (float) 1 / 4 * widthPerDraw * i + widthPerDraw * j, (float) (Math.sqrt(3) / 4 * widthPerDraw * (i + 1)), mPaint);
            }
        }
    }

    private int getTimesPerLine(){
        int timesPerLine = 1;
        for (;;){
            if(widthPerDraw * timesPerLine > screenWidth){
                break;
            } else {
                timesPerLine++;
            }
        }
        timesPerLine++;
        return timesPerLine;
    }

    private int getLines(){
        int lines = 1;
        for(;;){
            if((float) (Math.sqrt(3) / 4 * widthPerDraw * lines) > screenHeight){
                break;
            } else {
                lines++;
            }
        }
        lines++;
        return lines;
    }

    private float getTextWidth(String text){
        TextPaint paint = new TextPaint();
        paint.setTextSize(fontSize);
        return paint.measureText(text);
    }
}
