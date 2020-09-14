package com.cms.tank.simple

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import com.cms.tank.base.KtBaseActivity
import com.cms.tank.extensions.toast
import com.cms.tank.simple.databinding.ActivityMainBinding

class MainActivity : KtBaseActivity<ActivityMainBinding>() {

    override fun obtainViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    val text = "对北半球而言，新智彗星是继1997年的海尔-波普彗星后最亮的彗星。" +
            "在整个2020年7月内，彗星都将维持人眼可见亮度。7月上旬以前，" +
            "在黎明前1至2小时内皆能于东北方低仰角处观测到。至7月中旬，彗星移至西北方向，" +
            "适合观测的时段转为日落后1至2小时内。7月下旬开始，彗星亮度将快速降低，" +
            "最迟在8月下旬便会完全退回人眼不可见的亮度。"

    override fun initViews() {
        mBinding.root.alpha = 0.5f
        mBinding.moreTex.setAllTextAndMaxLine(text, 3)
    }
}
