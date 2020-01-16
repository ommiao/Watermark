package cn.ommiao.flyme8watermark.manager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import cn.ommiao.flyme8watermark.R;

public abstract class BaseCoverManager {

    private WindowManager mWindowManager;

    private View floatView;

    private WindowManager.LayoutParams params;

    private boolean show = false;

    public BaseCoverManager(Context context){
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        initWindowParams();
    }

    protected abstract int getLayoutId();

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

    public View getFloatView() {
        return floatView;
    }

    public void show() {
        try {
            mWindowManager.addView(floatView, params);
            show = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hide() {
        try {
            mWindowManager.removeView(floatView);
            show = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean switchState(){
        if(show){
            hide();
        } else {
            show();
        }
        return show;
    }

    public boolean isShow() {
        return show;
    }
}
