package cn.ommiao.flyme8watermark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import cn.ommiao.flyme8watermark.widget.Flyme8Watermark;

public class WatermarkManager {

    private static final float animateOffset = 145;

    private WindowManager mWindowManager;
    private static WatermarkManager mInstance;
    private Context mContext;

    private boolean isShow = false;

    private View floatView;
    private Flyme8Watermark watermark;

    private WindowManager.LayoutParams params;

    public static WatermarkManager getInstance(Context context, boolean forceCreate) {
        if(mInstance == null || forceCreate){
            mInstance = new WatermarkManager(context);
        }
        return mInstance;
    }

    @SuppressLint("InflateParams")
    public WatermarkManager(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        floatView = LayoutInflater.from(mContext).inflate(R.layout.layout_watermark, null);
        watermark = floatView.findViewById(R.id.watermark);
        initWindowParams();
    }

    private void initWindowParams() {
        params = new WindowManager.LayoutParams();
        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 0;
        //总是出现在应用程序窗口之上
        params.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        //设置图片格式，效果为背景透明
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
    }

    public void show() {
        try {
            mWindowManager.addView(floatView, params);
            isShow = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hide() {
        try {
            mWindowManager.removeView(floatView);
            isShow = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShow(){
        return isShow;
    }

    public void refresh(){
        watermark.refresh();
    }

}
