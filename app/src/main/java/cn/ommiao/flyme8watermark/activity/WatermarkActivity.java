package cn.ommiao.flyme8watermark.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.ommiao.flyme8watermark.R;
import cn.ommiao.flyme8watermark.WatermarkService;
import cn.ommiao.flyme8watermark.manager.WatermarkManager;
import cn.ommiao.flyme8watermark.widget.Flyme8Watermark;

public class WatermarkActivity extends BaseModeActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, WatermarkActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initViews() {
        super.initViews();
        final EditText etWatermark = findViewById(R.id.et_watermark);
        etWatermark.setText(sp.getString(Flyme8Watermark.FIELD_TEXT_WATERMARK, "Flyme8内测版"));
        ImageView ivEnter = findViewById(R.id.iv_enter);
        ivEnter.setOnClickListener(view -> {
            String text = etWatermark.getText().toString();
            if(text.length() > 0){
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Flyme8Watermark.FIELD_TEXT_WATERMARK, text);
                editor.apply();
            } else {
                Toast.makeText(this, getString(R.string.tips_blank_text), Toast.LENGTH_SHORT).show();
                return;
            }
            if(isServiceOn()){
                WatermarkService.getWatermarkManager().refresh();
            } else {
                Toast.makeText(this, getString(R.string.tips_open_service), Toast.LENGTH_SHORT).show();
                openAccessibilityPage();
            }
        });
    }

    @Override
    protected WatermarkManager coverManager() {
        return WatermarkService.getWatermarkManager();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_watermark;
    }

}
