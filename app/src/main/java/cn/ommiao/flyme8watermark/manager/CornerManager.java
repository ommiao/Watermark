package cn.ommiao.flyme8watermark.manager;

import android.content.Context;

import cn.ommiao.flyme8watermark.R;

public class CornerManager extends BaseCoverManager {

    public CornerManager(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_corner;
    }

}
