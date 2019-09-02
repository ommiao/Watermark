package cn.ommiao.flyme8watermark;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.ommiao.flyme8watermark.widget.Flyme8Watermark;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etWatermark = findViewById(R.id.et_watermark);
        ImageView ivEnter = findViewById(R.id.iv_enter);
        ivEnter.setOnClickListener(view -> {
            String text = etWatermark.getText().toString();
            if(text.length() > 0){
                SharedPreferences sp = MainActivity.this.getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Flyme8Watermark.FIELD_TEXT_WATERMARK, text);
                editor.apply();
            } else {
                Toast.makeText(this, getString(R.string.tips_blank_text), Toast.LENGTH_SHORT).show();
                return;
            }
            if(isServiceOn()){
                WatermarkManager.getInstance(this, false).refresh();
            } else {
                Toast.makeText(this, getString(R.string.tips_open_service), Toast.LENGTH_SHORT).show();
                openAccessibilityPage();
            }
        });
    }

    private void openAccessibilityPage(){
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isServiceOn() {
        int accessibilityEnabled = 0;
        final String service = getPackageName() + "/" + WatermarkService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(
                    getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
