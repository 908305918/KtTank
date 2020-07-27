package com.cms.tank.simple

import com.cms.tank.base.KtBaseActivity
import com.cms.tank.simple.databinding.ActivityMainBinding

class MainActivity : KtBaseActivity<ActivityMainBinding>() {

    override fun obtainViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        mBinding.root.alpha = 0.5f
    }
}
