package cn.ommiao.flyme8watermark.activity;

import android.content.Context;
import android.content.Intent;

import cn.ommiao.flyme8watermark.R;
import cn.ommiao.flyme8watermark.WatermarkService;
import cn.ommiao.flyme8watermark.manager.CornerManager;

public class CornerActivity extends BaseModeActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CornerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected CornerManager coverManager() {
        return WatermarkService.getCornerManager();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_corner;
    }

}
