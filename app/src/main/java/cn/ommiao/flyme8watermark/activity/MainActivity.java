package cn.ommiao.flyme8watermark.activity;

import cn.ommiao.flyme8watermark.R;
import cn.ommiao.flyme8watermark.util.ToastUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void initViews() {
        initStatusBar();
        findViewById(R.id.tv_mode_watermark).setOnClickListener(v -> {
            if(isServiceOn()){
                WatermarkActivity.start(this);
            } else {
                ToastUtil.shortToast(this, getString(R.string.tips_open_accessibility_server));
                openAccessibilityPage();
            }
        });
        findViewById(R.id.tv_mode_burn).setOnClickListener(v -> {
            if(isServiceOn()){
                BurnActivity.start(this);
            } else {
                ToastUtil.shortToast(this, getString(R.string.tips_open_accessibility_server));
                openAccessibilityPage();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}