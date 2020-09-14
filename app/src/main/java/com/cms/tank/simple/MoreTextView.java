package com.cms.tank.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ReplacementSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

public class MoreTextView extends AppCompatTextView {
    private String mText;
    private int mMaxLine;

    public MoreTextView(Context context) {
        super(context);
    }

    public MoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAllTextAndMaxLine(String text, int line) {
        this.setMovementMethod(LinkMovementMethod.getInstance());
        mText = text;
        mMaxLine = line;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                switchDisplayText(false);
            }
        }, 500);

    }


    private void switchDisplayText(boolean isAll) {
        int lastCharIndex = getLastCharIndexForLimitTextView(this, mText, getMeasuredWidth(), mMaxLine);
        if (lastCharIndex < 0) {
            setText(mText);
            return;
        }
        if (isAll) {
            setText(getAllDisplayText(mText));
        } else {
            setText(getLimitDisplayText(mText, lastCharIndex));
        }
    }


    private CharSequence getAllDisplayText(String text) {
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(text).append("\t\t\t");
        int index = ssb.length();
        ssb.append("收起");
        ssb.setSpan(new CustomClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                switchDisplayText(false);
            }
        }, index, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    private CharSequence getLimitDisplayText(String text, int lastCharIndex) {
        String str = text.substring(0, lastCharIndex - 6);
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(str).append("···\t\t\t");
        int index = ssb.length();
        ssb.append("更多 >");
        ssb.setSpan(new CustomClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                switchDisplayText(true);
            }
        }, index, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ssb.setSpan(new ForegroundColorSpan(0xFFFF0000), 0, ssb.length(),
//                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ssb;
    }


    private int getLastCharIndexForLimitTextView(TextView textView, String content, int width, int maxLine) {
        TextPaint textPaint = textView.getPaint();
        StaticLayout staticLayout = new StaticLayout(content, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        if (staticLayout.getLineCount() > maxLine)
            return staticLayout.getLineStart(maxLine); //exceed
        else return -1; //not exceed the max line
    }


    private int dp2px(float dp) {
        return (int) (getResources().getDisplayMetrics().density * dp);
    }

    public static abstract class CustomClickableSpan extends ClickableSpan {

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#333333"));
            ds.setAntiAlias(true);
            ds.setUnderlineText(false);
            ds.bgColor = 0x00000000;
            ds.linkColor= 0xFFFF0000;
        }
    }

    public static class RoundBackgroundColorSpan extends ReplacementSpan {
        private int bgColor;
        private int textColor;

        public RoundBackgroundColorSpan(int bgColor, int textColor) {
            super();
            this.bgColor = bgColor;
            this.textColor = textColor;
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            //设置宽度为文字宽度加16dp
            return ((int) paint.measureText(text, start, end) + 16);
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            int originalColor = paint.getColor();
            paint.setColor(this.bgColor);
            //画圆角矩形背景
            canvas.drawRoundRect(new RectF(x,
                            top + 3,
                            x + ((int) paint.measureText(text, start, end) + 16),
                            bottom - 1),

                    4,
                    4,
                    paint);
            paint.setColor(this.textColor);
            //画文字,两边各增加8dp
            canvas.drawText(text, start, end, x + 8, y, paint);
            //将paint复原
            paint.setColor(originalColor);
        }
    }


}
