package com.cms.tank.view

import android.content.Context
import android.graphics.Color
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class MoreTextView : AppCompatTextView {
    private var mText: String? = null
    private var mMaxLine = 0

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    fun setAllTextAndMaxLine(text: String?, line: Int) {
        this.movementMethod = LinkMovementMethod.getInstance()
        mText = text
        mMaxLine = line
        highlightColor = -0x10000
        postDelayed({ switchDisplayText(false) }, 500)
    }

    private fun switchDisplayText(isAll: Boolean) {
        val lastCharIndex =
            getLastCharIndexForLimitTextView(this, mText, measuredWidth, mMaxLine)
        if (lastCharIndex < 0) {
            text = mText
            return
        }
        text = if (isAll) {
            getAllDisplayText(mText)
        } else {
            getLimitDisplayText(mText, lastCharIndex)
        }
    }

    private fun getAllDisplayText(text: String?): CharSequence {
        val ssb = SpannableStringBuilder()
        ssb.append(text).append("\t\t\t")
        val index = ssb.length
        ssb.append("收起")
        ssb.setSpan(object : CustomClickableSpan() {
            override fun onClick(widget: View) {
                switchDisplayText(false)
            }
        }, index, ssb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
    }

    private fun getLimitDisplayText(text: String?, lastCharIndex: Int): CharSequence {
        val str = text!!.substring(0, lastCharIndex - 6)
        val ssb = SpannableStringBuilder()
        ssb.append(str).append("···\t\t\t")
        val index = ssb.length
        ssb.append("更多 >")
        ssb.setSpan(object : CustomClickableSpan() {
            override fun onClick(widget: View) {
                switchDisplayText(true)
            }
        }, index, ssb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
    }

    private fun getLastCharIndexForLimitTextView(
        textView: TextView,
        content: String?,
        width: Int,
        maxLine: Int
    ): Int {
        val textPaint = textView.paint
        val staticLayout = StaticLayout(
            content,
            textPaint,
            width,
            Layout.Alignment.ALIGN_NORMAL,
            1F,
            0F,
            false
        )
        return if (staticLayout.lineCount > maxLine) staticLayout.getLineStart(maxLine) //exceed
        else -1 //not exceed the max line
    }

    private fun dp2px(dp: Float): Int {
        return (resources.displayMetrics.density * dp).toInt()
    }

    abstract class CustomClickableSpan : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = Color.parseColor("#333333")
            ds.isAntiAlias = true
            ds.isUnderlineText = false
            ds.bgColor = 0x00000000
            ds.linkColor = -0x10000
        }
    }
}