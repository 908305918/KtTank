package com.cms.tank.helper.span;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;

/**
 * Created by bin on 2018/3/29.
 */

public class SpanHelper {
    SpannableStringBuilder ssb = new SpannableStringBuilder();

    public SpanHelper append(CharSequence text){
        ssb.append(text);
        return this;
    }

    public SpanHelper setSpan(CharSequence text, final View.OnClickListener l){
        int index = ssb.length();
        ssb.append(text);
        ssb.setSpan(new CustomClickableSpan() {
            @Override
            public void onClick(View widget) {
                if(l!=null){
                    l.onClick(widget);
                }
            }
        },index,index+text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableStringBuilder builder(){
        return ssb;
    }
}
