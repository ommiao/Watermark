package cn.ommiao.flyme8watermark;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class WatermarkService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        WatermarkManager.getInstance(this, true).show();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

}
