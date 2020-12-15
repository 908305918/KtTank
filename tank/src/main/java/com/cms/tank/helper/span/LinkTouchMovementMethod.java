package com.cms.tank.helper.span;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by bin on 2018/3/28.
 */

public class LinkTouchMovementMethod extends LinkMovementMethod {
    private CustomClickableSpan mClickableSpan;

    @Override
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mClickableSpan = getClickSpan(textView, spannable, event);
            if (mClickableSpan != null) {
                mClickableSpan.setPressed(true);
                Selection.setSelection(spannable, spannable.getSpanStart(mClickableSpan),
                        spannable.getSpanEnd(mClickableSpan));
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            CustomClickableSpan clickSpan = getClickSpan(textView, spannable, event);
            if (mClickableSpan != null && clickSpan != mClickableSpan) {
                mClickableSpan.setPressed(false);
                mClickableSpan = null;
                Selection.removeSelection(spannable);
            }
        } else {
            if (mClickableSpan != null) {
                mClickableSpan.setPressed(false);
                super.onTouchEvent(textView, spannable, event);
            }
            mClickableSpan = null;
            Selection.removeSelection(spannable);
        }
        return true;
    }

    private CustomClickableSpan getClickSpan(TextView textView, Spannable spannable, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        CustomClickableSpan[] link = spannable.getSpans(off, off, CustomClickableSpan.class);
        CustomClickableSpan clickSpan = null;
        if (link.length > 0) {
            clickSpan = link[0];
        }
        return clickSpan;
    }

    public static MovementMethod getInstance() {
        if (sInstance == null)
            sInstance = new LinkTouchMovementMethod();

        return sInstance;
    }

    private static LinkTouchMovementMethod sInstance;
}
