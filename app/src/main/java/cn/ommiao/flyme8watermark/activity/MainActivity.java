package cn.ommiao.flyme8watermark.activity;

import cn.ommiao.flyme8watermark.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void initViews() {
        initStatusBar();
        findViewById(R.id.tv_mode_watermark).setOnClickListener(v -> {
            if(isServiceOn()){
                WatermarkActivity.start(this);
            }
        });
        findViewById(R.id.tv_mode_burn).setOnClickListener(v -> {
            if(isServiceOn()){
                BurnActivity.start(this);
            }
        });
        findViewById(R.id.tv_mode_corner).setOnClickListener(v -> {
            if(isServiceOn()){
                CornerActivity.start(this);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}