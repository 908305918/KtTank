package com.cms.tank.helper.span;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

/**
 * Created by bin on 2018/3/28.
 */

public abstract class CustomClickableSpan extends ClickableSpan {
    private boolean mPressed = false;

    /**
     * 重写父类 updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
     */
    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(Color.parseColor("#4682B4"));
        ds.bgColor = mPressed ? Color.GREEN : 0x00000000;
    }

    public void setPressed(boolean pressed) {
        mPressed = pressed;
    }
}
