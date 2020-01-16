package cn.ommiao.flyme8watermark.manager;

import android.content.Context;

import cn.ommiao.flyme8watermark.R;
import cn.ommiao.flyme8watermark.widget.Flyme8Watermark;

public class WatermarkManager extends BaseCoverManager {

    private Flyme8Watermark watermark;

    public WatermarkManager(Context context) {
        super(context);
        watermark = getFloatView().findViewById(R.id.watermark);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_watermark;
    }

    public void refresh(){
        watermark.refresh();
    }

}
