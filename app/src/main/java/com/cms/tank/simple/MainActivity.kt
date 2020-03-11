package com.cms.tank.simple

import com.cms.tank.base.BaseActivity
import com.cms.tank.simple.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        mBinding.root.alpha = 0.5f
    }
}
