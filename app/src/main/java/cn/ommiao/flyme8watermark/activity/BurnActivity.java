package cn.ommiao.flyme8watermark.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import cn.ommiao.flyme8watermark.R;
import cn.ommiao.flyme8watermark.WatermarkService;
import cn.ommiao.flyme8watermark.manager.BurnManager;
import cn.ommiao.flyme8watermark.widget.LongPressImageView;

public class BurnActivity extends BaseModeActivity implements LongPressImageView.PressHoldListener {

    private static final String SP_KEY_BURN_ALPHA = "burn_alpha";
    private static final String SP_KEY_BURN_T_X = "burn_t_x";
    private static final String SP_KEY_BURN_T_Y = "burn_t_y";

    private float alpha;

    private int translationX, translationY;

    public static void start(Context context) {
        Intent starter = new Intent(context, BurnActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initViews() {
        super.initViews();
        alpha = sp.getFloat(SP_KEY_BURN_ALPHA, 0.5f);
        translationX = sp.getInt(SP_KEY_BURN_T_X, 0);
        translationY = sp.getInt(SP_KEY_BURN_T_Y, 0);
        coverManager().setBurnViewAlpha(alpha);
        coverManager().setTranslationX(translationX);
        coverManager().setTranslationY(translationY);

        Handler handler = new Handler();

        LongPressImageView ivUp = findViewById(R.id.iv_up);
        ivUp.setOnClickListener(v -> {
            up();
        });
        ivUp.setHandler(handler);
        ivUp.setPressHoldListener(this);

        LongPressImageView ivDown = findViewById(R.id.iv_down);
        ivDown.setOnClickListener(v -> {
            down();
        });
        ivDown.setHandler(handler);
        ivDown.setPressHoldListener(this);


        LongPressImageView ivLeft = findViewById(R.id.iv_left);
        ivLeft.setOnClickListener(v -> {
            left();
        });
        ivLeft.setHandler(handler);
        ivLeft.setPressHoldListener(this);


        LongPressImageView ivRight = findViewById(R.id.iv_right);
        ivRight.setOnClickListener(v -> {
            right();
        });
        ivRight.setHandler(handler);
        ivRight.setPressHoldListener(this);

        SeekBar seekBar = findViewById(R.id.seek_bar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            seekBar.setProgress((int) (alpha * 100), true);
        } else {
            seekBar.setProgress((int) (alpha * 100));
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alpha = progress / 100f;
                coverManager().setBurnViewAlpha(alpha);
                sp.edit().putFloat(SP_KEY_BURN_ALPHA, alpha).apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void right() {
        translationX++;
        coverManager().setTranslationX(translationX);
        sp.edit().putInt(SP_KEY_BURN_T_X, translationX).apply();
    }

    private void left() {
        translationX--;
        coverManager().setTranslationX(translationX);
        sp.edit().putInt(SP_KEY_BURN_T_X, translationX).apply();
    }

    private void down() {
        translationY++;
        coverManager().setTranslationY(translationY);
        sp.edit().putInt(SP_KEY_BURN_T_Y, translationY).apply();
    }

    private void up() {
        translationY--;
        coverManager().setTranslationY(translationY);
        sp.edit().putInt(SP_KEY_BURN_T_Y, translationY).apply();
    }

    @Override
    protected BurnManager coverManager() {
        return WatermarkService.getBurnManager();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_burn;
    }

    @Override
    public void stillPress(int viewId) {
        switch (viewId){
            case R.id.iv_up:
                up();
                break;
            case R.id.iv_down:
                down();
                break;
            case R.id.iv_left:
                left();
                break;
            case R.id.iv_right:
                right();
                break;
        }
    }
}
