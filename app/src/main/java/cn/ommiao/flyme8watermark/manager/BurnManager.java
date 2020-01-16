package cn.ommiao.flyme8watermark.manager;

import android.content.Context;
import android.widget.ImageView;

import cn.ommiao.flyme8watermark.R;

public class BurnManager extends BaseCoverManager {

    private ImageView iv;

    public BurnManager(Context context) {
        super(context);
        iv = getFloatView().findViewById(R.id.iv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_burn;
    }

    public int getBurnViewWidth(){
        return iv.getWidth();
    }

    public int getBurnViewHeight(){
        return iv.getHeight();
    }

    public void setBurnViewAlpha(float alpha){
        iv.setAlpha(alpha);
    }

    public void setTranslationX(int translationX){
        iv.setTranslationX(translationX);
    }

    public void setTranslationY(int translationY){
        iv.setTranslationY(translationY);
    }

}
