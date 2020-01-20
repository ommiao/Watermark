package cn.ommiao.flyme8watermark;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.view.accessibility.AccessibilityEvent;

import cn.ommiao.flyme8watermark.manager.BurnManager;
import cn.ommiao.flyme8watermark.manager.CornerManager;
import cn.ommiao.flyme8watermark.manager.WatermarkManager;

public class WatermarkService extends AccessibilityService {

    @SuppressLint("StaticFieldLeak")
    private static WatermarkManager watermarkManager;

    @SuppressLint("StaticFieldLeak")
    private static BurnManager burnManager;

    @SuppressLint("StaticFieldLeak")
    private static CornerManager cornerManager;

    @Override
    protected void onServiceConnected() {
        watermarkManager = new WatermarkManager(this);
        burnManager = new BurnManager(this);
        cornerManager = new CornerManager(this);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    public static WatermarkManager getWatermarkManager(){
        return watermarkManager;
    }

    public static BurnManager getBurnManager(){
        return burnManager;
    }

    public static CornerManager getCornerManager() {
        return cornerManager;
    }
}
