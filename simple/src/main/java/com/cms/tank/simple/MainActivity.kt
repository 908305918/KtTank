package com.cms.tank.simple

import android.os.Bundle
import android.util.Log
import com.cms.tank.base.KtBaseActivity
import com.cms.tank.property.viewBinding
import com.cms.tank.simple.databinding.ActivityMainBinding

class MainActivity : KtBaseActivity() {

    private val mBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_main)
        mBinding.btnTest.setOnClickListener {
            showLoading("加载中")
        }

    }

}






